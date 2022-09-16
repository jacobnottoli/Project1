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

    public void deleteReview(String movietitle, int userid) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete reviews where movietitle = ? and userid = ?");
            ps.setString(1,movietitle);
            ps.setInt(2,userid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addReview(Review r) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into reviews (movietitle, rating, review, userid) values (?, ?, ?, ?)");
            ps.setString(1,r.getMovietitle());
            ps.setDouble(2,r.getRating());
            ps.setString(3,r.getReview());
            ps.setInt(4, r.getUserid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Review> getAllReviews(int userid) {
        List<Review> ReviewList = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from reviews where userid = ?");
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Review r = new Review(rs.getString("movietitle"), rs.getDouble("rating"),rs.getString("review"),userid);
                ReviewList.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ReviewList;
    }
}
