package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.DataFetcher;
import model.Decoder;
import model.JDBC;
import model.JsonExtractor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        DataFetcher df = new DataFetcher();
        Decoder decode = new Decoder();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonExtractor>>(){}.getType();

        //Query to find mail_id and put it in the String vector
        JDBC query = new JDBC();
        query.arrayInsertion();

        String apiResponse;

        //Create an Excel file for putting data in it
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MailData");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("mail_id");
        header.createCell(1).setCellValue("subject");
        int rowIndex = 1;

        //Fetch data using the vector entries

        for (int i = 0; i < JDBC.mail_id.size(); i++) {
            df.setMail_id(JDBC.mail_id.get(i));
            apiResponse=df.getData();
            //Data extraction
            List<JsonExtractor> jse = gson.fromJson(apiResponse,listType);

            //Data printing and filling Excel sheet
            for (JsonExtractor jsonExtractor: jse){
                System.out.println(jsonExtractor.getMail_body_base64());
                System.out.println(decode.decoder(jsonExtractor.getMail_body_base64()));

                //Fill in the Excel sheet
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(JDBC.mail_id.get(i));  // mail_id
                row.createCell(1).setCellValue(decode.decoder(jsonExtractor.getMail_body_base64()));
            }

            // save the Excel file
            try (FileOutputStream fileOut = new FileOutputStream("MailData.xlsx")) {
                workbook.write(fileOut);
                workbook.close();
                System.out.println("Excel file 'MailData.xlsx' written successfully.");
            }
        }









    }

}
