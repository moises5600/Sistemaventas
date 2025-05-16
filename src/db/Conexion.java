package db;

import java.sql.*;

public class Conexion {
    public static Connection conectar() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistemaventas", "root", ""
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}