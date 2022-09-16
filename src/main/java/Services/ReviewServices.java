package Services;

import DAO.ReviewRepository;
import Model.Review;

import java.util.List;

public class ReviewServices {
    ReviewRepository rr;
    public ReviewServices() {
        rr = new ReviewRepository();
    }

    public List<Review> getAllReviews(int userid) {return rr.getAllReviews(userid);}

    public void addReview(String movietitle, double rating, String review, int userid) {
        Review r = new Review(movietitle,rating,review,userid);
        rr.addReview(r);
    }

    public void deleteReview(String movietitle, int userid) {
        rr.deleteReview(movietitle, userid);
    }
}
