package Services;

import DAO.ReviewRepository;
import Model.Review;

import java.util.List;

public class ReviewServices {
    ReviewRepository rr;
    public ReviewServices() {
        rr = new ReviewRepository();
    }

    public List<Review> getAllReviews() {return rr.getAllReviews();}

    public void addReview(String movietitle, double rating, String review) {
        Review r = new Review(movietitle,rating,review);
        rr.addReview(r);
    }

    public void deleteReview(String movietitle) {
        rr.deleteReview(movietitle);
    }
}
