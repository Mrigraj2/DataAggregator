package model;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExcelInput {
    private final Workbook workbook = new XSSFWorkbook();
    private Sheet sheet;
    private Row header;
    private Row rows;
    private static int rowIndex = 0;

    public ExcelInput(String sheetName){
        this.sheet = this.workbook.createSheet(sheetName);
    }

    public void fillDataInExcel(ResultSetMetaData resultSetMetaData, ResultSet resultSet) throws SQLException {
        int columnCount = resultSetMetaData.getColumnCount();
        this.header = this.sheet.createRow(rowIndex);

        for(int i=1;i<=columnCount;i++){
            header.createCell(i-1).setCellValue(resultSetMetaData.getColumnName(i));
        }
        while (resultSet.next()){
            this.rows= this.sheet.createRow(++rowIndex);
            for (int i=1;i<=columnCount;i++){
                this.rows.createCell(i-1).setCellValue(resultSet.getString(i));
            }
        }
    }

    public Workbook getWorkbook(){
        return this.workbook;
    }
}
