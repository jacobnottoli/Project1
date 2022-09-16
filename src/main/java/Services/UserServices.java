package Services;

import DAO.UserRepository;
import Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserServices {

    Connection conn = ConnectionUtil.getConnection();
    UserRepository ur = new UserRepository();

    public void addUser(String username, String password) {
        if (username != "" && password != "") {
            try {
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("select username from users");
                boolean existingUsername = false;
                while (rs.next()) {
                    if (username.equals(rs.getString("username"))) {
                        existingUsername = true;
                    }
                }
                if (existingUsername == false) {
                    ur.addUser(username,password);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public int loginUser(String username, String password) {
        return ur.loginUser(username, password);
    }
}
