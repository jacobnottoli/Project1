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

    public int addUser(String username, String password) {
        if (username != "" && password != "") {
            try {
                Statement s = conn.createStatement();
                ResultSet rs = s.executeQuery("select username from users");
                boolean existingUsername = false;
                while (rs.next()) {
                    if (username.equals(rs.getString("username"))) {
                        existingUsername = true;
                        return 0;
                    }
                }
                if (existingUsername == false) {
                    ur.addUser(username,password);
                    return 1;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 2;
    }

    public int loginUser(String username, String password) {
        return ur.loginUser(username, password);
    }
}
