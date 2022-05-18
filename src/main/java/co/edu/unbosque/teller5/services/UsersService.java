package co.edu.unbosque.teller5.services;

import co.edu.unbosque.teller5.DTO.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersService {

    // Objects for handling connection
    private Connection conn;

    public UsersService() {
        this.conn = conn;
    }

    public void listUsers() {
        // Object for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Usuario> usuarioApps = new ArrayList<Usuario>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing users...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Usuario";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while (rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                // Creating a new UserApp class instance and adding it to the array list
                usuarioApps.add(new Usuario(username, password, role));
            }

            // Printing results
            System.out.println("Username | Password | Role");
            for (Usuario usuario : usuarioApps) {
                System.out.println(usuario.toString());
            }

            // Printing total rows
            System.out.println("Total of users retrieved: " + usuarioApps.size() + "\n");

            // Closing resources
            rs.close();
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}