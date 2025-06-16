package model;

import java.sql.*;
import java.util.Vector;

public class JDBC {

    String sql = "select mail_id from mail";
    String url = "jdbc:mysql://192.168.99.32:3306/crm_manager_1?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    String user = "db_reader_root";
    String password = "rootroot";
    Connection connection = DriverManager.getConnection(url,user,password);

    public static Vector<Integer> mail_id = new Vector<>();

    public JDBC() throws Exception {
    }


    public void arrayInsertion() throws SQLException {
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()){
            int x = resultSet.getInt("mail_id");
            mail_id.add(x);
        }
    }

}
