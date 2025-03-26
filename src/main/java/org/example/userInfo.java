package org.example;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userInfo {
    private static final String CREATE_USER = "INSERT INTO users (username, email, pass) VALUES (?, ?, ?)";
    private static final String GET_USER = "SELECT pass FROM users WHERE USERNAME = ?";

    public boolean createAccount(String username,String email,String password){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        try(Connection connection = ConnectDataBase.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);

            int Rows_affected = statement.executeUpdate();

            return Rows_affected > 0;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean Authenticator(String username, String password){
        try (Connection connection = ConnectDataBase.getConnection();
             PreparedStatement Statement = connection.prepareStatement(GET_USER)) {

            Statement.setString(1, username);

            ResultSet resultSet = Statement.executeQuery();

            if (resultSet.next()) {
                String storedhashedpassword = resultSet.getString("pass");

                return BCrypt.checkpw(password, storedhashedpassword);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
