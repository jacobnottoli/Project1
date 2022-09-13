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
}
