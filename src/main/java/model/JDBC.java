package model;

import java.sql.*;
import java.util.Vector;

public class JDBC {

    String sql = "select mail_id from mail order by mail_id desc limit 10";
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
