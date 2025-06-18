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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        DataFetcher df = new DataFetcher();
        Decoder decode = new Decoder();

        Gson gson = new Gson();
        Type listType = new TypeToken<List<JsonExtractor>>(){}.getType();

        //Create an Excel file for putting data in it
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("MailData");

        //Query to find mail_id and put it in the String vector
        JDBC query = new JDBC();
        ResultSet rs = query.resultSet();
        ResultSetMetaData resultSetMetaData = rs.getMetaData();

        int count = resultSetMetaData.getColumnCount();
        int rowIndex=0;

        Row header = sheet.createRow(rowIndex);

        // Headers column
        for (int i =1;i<=count;i++){
            header.createCell(i-1).setCellValue(resultSetMetaData.getColumnName(i));
        }
        header.createCell(count).setCellValue("Body");


        //Fill rows from DB output
        while(rs.next()){
            Row rows = sheet.createRow(rowIndex++);
            for(int i=1;i<=count;i++){
                String value = rs.getString(i);
                rows.createCell(i-1).setCellValue(value);
            }
        }

        query.arrayInsertion();

        String apiResponse;


        //Fetch data using the vector entries

//        for (int i = 0; i < JDBC.mail_id.size(); i++) {
//            df.setMail_id(JDBC.mail_id.get(i));
//            apiResponse=df.getData();
//            //Data extraction
//            List<JsonExtractor> jse = gson.fromJson(apiResponse,listType);
//
//            //Data printing and filling Excel sheet
//            for (JsonExtractor jsonExtractor: jse){
//                System.out.println(jsonExtractor.getMail_body_base64());
//                System.out.println(decode.decoder(jsonExtractor.getMail_body_base64()));
//
//                //Fill in the Excel sheet
//                Row row = sheet.createRow(rowIndex++);
//                row.createCell(0).setCellValue(JDBC.mail_id.get(i));  // mail_id
//                row.createCell(1).setCellValue(decode.decoder(jsonExtractor.getMail_body_base64()));
//            }
//
//        }

        // save the Excel file
        try (FileOutputStream fileOut = new FileOutputStream("MailData.xlsx")) {
            workbook.write(fileOut);
            workbook.close();
            System.out.println("Excel file 'MailData.xlsx' written successfully.");
        }









    }

}
