package DAO;

import Services.ConnectionUtil;

import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;

public class UserRepository {
    static Connection conn = ConnectionUtil.getConnection();
    public UserRepository() {

    }
    public void addUser(String username, String password) {
        int id = UserRepository.getNewUserId();
       try {
           PreparedStatement ps = conn.prepareStatement("insert into users (id, username, password) values (?, ?, ?)");
           ps.setInt(1,id);
           ps.setString(2,username);
           ps.setString(3,password);
           ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNewUserId() {
        int userid = ThreadLocalRandom.current().nextInt(1,10000);
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select id from users");
            while (rs.next()) {
                if (userid == rs.getInt("id")) {
                    userid = UserRepository.getNewUserId();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userid;
    }
}
