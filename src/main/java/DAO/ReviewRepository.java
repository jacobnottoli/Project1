package DAO;

import Model.Review;
import Services.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
Connection conn = ConnectionUtil.getConnection();
    public ReviewRepository() {

    }

    public void deleteReview(String movietitle) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete reviews where movietitle = ?");
            ps.setString(1,movietitle);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addReview(Review r) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into reviews (movietitle, rating, review) values (?, ?, ?)");
            ps.setString(1,r.getMovietitle());
            ps.setDouble(2,r.getRating());
            ps.setString(3,r.getReview());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
