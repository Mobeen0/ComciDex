package com.example.comicdex;

import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.*;

public class dbClass {
    private String url;
    private String username;
    private String password;

    public Connection conn;


    dbClass() throws ClassNotFoundException, SQLException {
        this.url = "jdbc:oracle:thin:@//localhost:1521/XE";
        this.username= "system";
        this.password = "hellomobeen123";
        Class.forName("oracle.jdbc.driver.OracleDriver");


    }

    public void openConnection() throws SQLException{
        conn = DriverManager.getConnection(url,username,password);
    }

    public void closeConnection() throws SQLException{
        conn.close();
    }



}
