package dk.ek.portfoliowebsite.repositories;

import dk.ek.portfoliowebsite.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public List<Message> findConversation(int user1, int user2) {

        List<Message> messages = new ArrayList<>();

        String sql = """
            SELECT *
            FROM messages
            WHERE (sender_id = ? AND receiver_id = ?)
               OR (sender_id = ? AND receiver_id = ?)
            ORDER BY created_at
            """;

        try (
                Connection conn = DriverManager.getConnection(dbUrl, username, password);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, user1);
            stmt.setInt(2, user2);
            stmt.setInt(3, user2);
            stmt.setInt(4, user1);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                messages.add(new Message(
                        rs.getInt("id"),
                        rs.getInt("sender_id"),
                        rs.getInt("receiver_id"),
                        rs.getString("content"),
                        rs.getString("created_at")
                ));

            }

        } catch(SQLException e){
            e.printStackTrace();
        }

        return messages;
    }

    public void save(Message message){

        String sql = """
            INSERT INTO messages(sender_id,receiver_id,content)
            VALUES(?,?,?)
            """;

        try(
                Connection conn = DriverManager.getConnection(dbUrl,username,password);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ){

            stmt.setInt(1,message.getSenderId());
            stmt.setInt(2,message.getReceiverId());
            stmt.setString(3,message.getContent());

            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
