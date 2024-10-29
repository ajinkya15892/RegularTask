import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GitReportGen {

    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException {
        try {
            FileReader reader = new FileReader("D:\\GitRepoTask\\PrivateRepo3.json");
            JSONParser parser = new JSONParser();
            String pathFileExcel = "D:\\GitRepoTask\\PrivateRepo3.xlsx";
            FileOutputStream out = new FileOutputStream(new File(pathFileExcel));

            // Creating a workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            // spreadsheet object
            XSSFSheet spreadsheet = workbook.createSheet("PublicRepo");

            // Use JSONObject for simple JSON and JSONArray for array of JSON.
            ArrayList<JSONObject> parse = (ArrayList<JSONObject>) parser.parse(reader);
            Iterator<JSONObject> iterator = parse.iterator();
            int rowid = 0;
            // creating a row object
            XSSFRow row;
            ArrayList<String> keyList = new ArrayList<String>();
            keyList.add("name");
            keyList.add("size");
            keyList.add("created_at");
            keyList.add("updated_at");
            keyList.add("pushed_at");
            keyList.add("visibility");

            System.out.println("######################");
            System.out.println("\n");
            while (iterator.hasNext()) {
                JSONObject jsonObject = iterator.next();

                int count = 0;
                int cellid = 0;
                row = spreadsheet.createRow(rowid++);
                for (Iterator<String> iterator2 = keyList.iterator(); iterator2.hasNext();) {

                    String customKey = iterator2.next();
                    System.out.println(count + ":" + customKey + ":" + jsonObject.get(customKey));
                    Cell cell = row.createCell(cellid++);
                    if (customKey == "size") {
                        cell.setCellValue(jsonObject.get(customKey).toString());
                    } else {
                        cell.setCellValue(jsonObject.get(customKey).toString());
                    }
                    count++;
                }
            }
            workbook.write(out);
            out.close();
        } finally {

        }
    }

}
