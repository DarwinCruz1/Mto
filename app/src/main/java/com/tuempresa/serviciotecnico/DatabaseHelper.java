package com.tuempresa.serviciotecnico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://<127.0.0.1>:3306/<BDGLOBAL>";
    private static final String USER = "root";
    private static final String PASS = "123456";

    private Connection conn = null;

    // Constructor para establecer la conexión
    public DatabaseHelper() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para cerrar la conexión
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para insertar un informe
    public boolean insertReport(String customerName, String description, String technician) {
        String query = "INSERT INTO reports (CustomerName, Description, Technician) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerName);
            stmt.setString(2, description);
            stmt.setString(3, technician);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener informes
    public ResultSet getReports() {
        String query = "SELECT * FROM reports";
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
