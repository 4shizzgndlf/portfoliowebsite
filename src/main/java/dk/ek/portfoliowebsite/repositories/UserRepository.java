package dk.ek.portfoliowebsite.repositories;

import dk.ek.portfoliowebsite.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public Map<Integer, User> findAll(){
        Map<Integer, User> users = new HashMap<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Integer i = 0;
                User u = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("created_at")
                );
                users.put(i + 1, u);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
