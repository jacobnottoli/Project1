package Services;

import DAO.ReviewRepository;
import Model.Review;

import java.sql.*;
import java.util.List;

public class ReviewServices {

    Connection conn = ConnectionUtil.getConnection();
    ReviewRepository rr;
    public ReviewServices() {
        rr = new ReviewRepository();
    }

    public List<Review> getAllReviews(int userid) {return rr.getAllReviews(userid);}

    public int addReview(String movietitle, double rating, String review, int userid) {
        if (movietitle != "") {
            try {
                PreparedStatement ps = conn.prepareStatement("select movietitle from reviews where userid = ?");
                ps.setInt(1,userid);
                ResultSet rs = ps.executeQuery();
                boolean existingReview= false;
                while (rs.next()) {
                    if (movietitle.equals(rs.getString("movietitle"))) {
                        existingReview = true;
                        return 0;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Review r = new Review(movietitle,rating,review,userid);
            rr.addReview(r);
            return 1;
        }
        return 2;
    }

    public void deleteReview(String movietitle, int userid) {
        rr.deleteReview(movietitle, userid);
    }
}
