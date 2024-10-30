package com.poortoys.examples;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class PasswordDecryptorPKSC5 {

    public static String decryptSchemaPassword(String encryptionKey,String password){
        String decryptedPass = "";
        try{
            final byte[] pass = encryptionKey.getBytes(StandardCharsets.US_ASCII);
            final byte[] magic = "Salted__".getBytes(StandardCharsets.US_ASCII);
            String source = new String(password.getBytes(), StandardCharsets.US_ASCII);
//            source = source.replaceAll("\\s+", "");
            final Decoder decoder = Base64.getDecoder();
            final byte[] inBytes = decoder.decode(source);
            final byte[] shouldBeMagic = Arrays.copyOfRange(inBytes, 0,magic.length);
            if (!Arrays.equals(shouldBeMagic, magic)) {
                System.out.println("Bad magic number");
                return decryptedPass;
            }
            final byte[] salt = Arrays.copyOfRange(inBytes, magic.length, magic.length + 8);
            final byte[] passAndSalt = concat(pass, salt);
            byte[] hash = new byte[0];
            byte[] keyAndIv = new byte[0];
            for (int i = 0; i < 3; i++) {
                final byte[] data = concat(hash, passAndSalt);
                final MessageDigest md = MessageDigest.getInstance("MD5");
                hash = md.digest(data);
                keyAndIv = concat(keyAndIv, hash);
            }
            final byte[] keyValue = Arrays.copyOfRange(keyAndIv, 0, 32);
            final byte[] iv = Arrays.copyOfRange(keyAndIv, 32, 48);
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            final byte[] clear = cipher.doFinal(inBytes, 16, inBytes.length - 16);
            decryptedPass = new String(clear, StandardCharsets.ISO_8859_1);
            System.out.println(decryptedPass);
        }catch(Exception e){
            System.out.println("Error while decrypting password : "+e);
        }
        return decryptedPass;
    }

    public static byte[] concat(byte[] byte1, byte[] byte2) {

        return ByteBuffer.allocate(byte1.length + byte2.length)
                .put(byte1)
                .put(byte2)
                .array();

    }


    public static void main(String[] args) {
        String encryptionKey = "dev_qa4@#_test1234"; // working
        String encryptedPassword = "U2FsdGVkX19onIdInb7sqFb+Zu7jJw5lU7pLL8alYCU="; // working
        //openssl command line
        //echo U2FsdGVkX1+u95CrkfCDfZr8E1Yr5+u84VTePx2ItWY= | openssl enc -k test@123 -aes256 -base64 -d | sed 's~ ~~g'
        //echo ${RANDOM_PASS} | openssl enc -k ${ENCRYPTION_KEY} -aes256 -base64 -e
//        String encryptedPassword = "Yy5Q8CpD2E48m/ktjc/UaI6KcMzQ33kEK+yLsEY9+/0=";

//        String encryptionKey = "devqa3@pass#enc1"; //not working
//        String encryptedPassword = "U2FsdGVkX1/5oESsa83v7OWYTYr3E7I9d9P7pONYSWY="; //not working


        PasswordDecryptorPKSC5 passDecrypt = new PasswordDecryptorPKSC5();
        String decryptedPassword = decryptSchemaPassword(encryptionKey, encryptedPassword);
        System.out.println("Final Decrypted Password: " + decryptedPassword);
    }
}