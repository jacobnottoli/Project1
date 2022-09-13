package DAO;

import Model.Review;
import Services.ConnectionUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
Connection conn = ConnectionUtil.getConnection();
    public ReviewRepository() {

    }

    public List<Review> getAllReviews() {
        List<Review> ReviewList = new ArrayList<>();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("select * from reviews");
            while (rs.next()) {
                Review r = new Review(rs.getString("movietitle"), rs.getDouble("rating"),rs.getString("review"));
                ReviewList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ReviewList;
    }
}
