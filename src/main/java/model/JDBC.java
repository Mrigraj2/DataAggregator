package model;

import java.sql.*;

public class JDBC {

    private String sql = "";
    private String url = "";
    private String user = "";
    private String password = "";
    private Connection connection;


    public JDBC(String url, String username, String password, String sql) throws SQLException {
        this.url=url;
        this.user=username;
        this.password=password;
        connection = DriverManager.getConnection(this.url,this.user,this.password);

    }

    public ResultSet executeQuery() throws SQLException {
        Statement st = connection.createStatement();
        return st.executeQuery(sql);
    }

}
