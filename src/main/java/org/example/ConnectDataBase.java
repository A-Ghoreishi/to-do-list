package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDataBase {

        private static final String url = "jdbc:postgresql://localhost:5432/To-Do List";
        private static final String user = "postgres";
        private static final String password = "9330670020";

        public static Connection getConnection() throws SQLException{
            return DriverManager.getConnection(url,user, password);
        }
}
