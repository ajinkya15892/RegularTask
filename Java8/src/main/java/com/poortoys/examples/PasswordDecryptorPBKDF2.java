/*
Latest
 */
package com.poortoys.examples;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Base64.Decoder;

public class PasswordDecryptorPBKDF2 {

    public static String decryptSchemaPassword(String encryptionKey, String password) {
        String decryptedPass = "";
        try {
            final byte[] magic = "Salted__".getBytes(StandardCharsets.US_ASCII);
            String source = new String(password.getBytes(), StandardCharsets.US_ASCII);
            final Decoder decoder = Base64.getDecoder();
            final byte[] inBytes = decoder.decode(source);
            final byte[] shouldBeMagic = Arrays.copyOfRange(inBytes, 0, magic.length);
            if (!Arrays.equals(shouldBeMagic, magic)) {
                System.out.println("Bad magic number");
                return decryptedPass;
            }
            final byte[] salt = Arrays.copyOfRange(inBytes, magic.length, magic.length + 8);

            // Derive AES key using PBKDF2
            byte[] keyValue = deriveKey(encryptionKey, salt, 100000, 256); // 256 bits = 32 bytes
            final byte[] iv = Arrays.copyOfRange(keyValue, 32, 48); // Get IV from derived key

            // Extract only the AES key
            keyValue = Arrays.copyOf(keyValue, 32); // Ensure the key is 256 bits

            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            final SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            final byte[] clear = cipher.doFinal(inBytes, 16, inBytes.length - 16);
            decryptedPass = new String(clear, StandardCharsets.ISO_8859_1);
            System.out.println(decryptedPass);
        } catch (Exception e) {
            System.out.println("Error while decrypting password: " + e);
        }
        return decryptedPass;
    }

    public static byte[] deriveKey(String password, byte[] salt, int iterations, int keyLength) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength + 128); // 128 extra bits for IV
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return keyBytes;
    }

    public static byte[] concat(byte[] byte1, byte[] byte2) {
        return ByteBuffer.allocate(byte1.length + byte2.length)
                .put(byte1)
                .put(byte2)
                .array();
    }

    public static void main(String[] args) {
//        String encryptionKey = "encrypted_password"; // working
//        String encryptedPassword = "encrypted_key"; // working
        /*
        command 1 : echo pU2iA8J32g | openssl enc -aes-256-cbc -base64 -e -k test@123 -pbkdf2 -iter 100000
        command 2 : echo U2FsdGVkX1+u95CrkfCDfZr8E1Yr5+u84VTePx2ItWY= | openssl enc -aes256 -base64 -d -k test@123 -pbkdf2 -iter 100000 | sed 's~ ~~g'
         */
        String encryptionKey = "test@123"; // working
        String encryptedPassword = "U2FsdGVkX1+u95CrkfCDfZr8E1Yr5+u84VTePx2ItWY="; // working

        PasswordDecryptorPBKDF2 passDecrypt = new PasswordDecryptorPBKDF2();
        String decryptedPassword = decryptSchemaPassword(encryptionKey, encryptedPassword);
        System.out.println("Final Decrypted Password: " + decryptedPassword);
        //o.p : pU2iA8J32g
    }
}