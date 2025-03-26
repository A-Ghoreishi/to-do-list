package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {
    public static void main(String[] args){
        String url = "jdbc:postgresql://localhost:5432/To-Do List";
        String user = "postgres";
        String password = "9330670020";

        try(Connection conn = DriverManager.getConnection(url, user, password)){
            System.out.println("Connected to PostgreSQL succesfully!");
        } catch(SQLException e){
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

}
