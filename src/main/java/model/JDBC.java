package model;

import java.sql.*;
import java.util.Vector;

public class JDBC {

    String sql = "SELECT \n" +
            "  m.mail_id,\n" +
            "  m.docket_no,\n" +
            "  m.subject, \n" +
            "  t.disposition_name,\n" +
            "  t.sub_disposition_name, \n" +
            "  t.priority_name, \n" +
            "  t.problem_reported,\n" +
            "  t.assigned_to_dept_name,\n" +
            "  t.assigned_to_user_name\n" +
            "FROM mail AS m \n" +
            "INNER JOIN ticket_details_2025_06 AS t \n" +
            "ON m.ticket_id = t.ticket_id \n" +
            "WHERE \n" +
            "  t.disposition_name IS NOT NULL AND t.disposition_name <> '' \n" +
            "  AND t.sub_disposition_name IS NOT NULL AND t.sub_disposition_name <> '' \n" +
            "  AND t.priority_name IS NOT NULL AND t.priority_name <> '' \n" +
            "  AND t.problem_reported IS NOT NULL AND t.problem_reported <> '' \n" +
            "  AND t.assigned_to_dept_name IS NOT NULL AND t.assigned_to_dept_name <> ''\n" +
            "  AND t.assigned_to_user_name IS NOT NULL AND t.assigned_to_user_name <> ''\n" +
            "ORDER BY m.mail_id DESC \n" +
            "LIMIT 200";
    String url = "jdbc:mysql://192.168.99.32:3306/crm_manager_1?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    String user = "db_reader_root";
    String password = "rootroot";
    Connection connection = DriverManager.getConnection(url,user,password);

    public static Vector<String> mail_id = new Vector<>();

    public JDBC() throws Exception {
    }


    public void arrayInsertion() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()){
            String x = String.valueOf(resultSet.getInt("mail_id"));
            mail_id.add(x);
        }
    }

    public ResultSet resultSet() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        return resultSet;
    }

}
