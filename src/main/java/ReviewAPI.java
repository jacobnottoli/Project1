import Model.Review;
import Services.ReviewServices;
import Services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

import java.util.List;

public class ReviewAPI {
    public static void main(String[] args) {
        ReviewServices rs = new ReviewServices();
        UserServices us = new UserServices();

        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9001);

        app.get("/reviews", ctx -> {ctx.json(rs.getAllReviews());});

        app.post("/reviews", ctx -> {
            ObjectMapper om = new ObjectMapper();
            Review newreview = om.readValue(ctx.body(), Review.class);
            rs.addReview(newreview.getMovietitle(),newreview.getRating(),newreview.getReview());
        });

        app.delete("/reviews/delete/", ctx -> {
            rs.deleteReview(ctx.body().toString());
        });
    }
}
