import Model.Review;
import Model.User;
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

        app.get("/reviews/{userid}", ctx -> {
            ctx.json(rs.getAllReviews(Integer.parseInt(ctx.pathParam("userid"))));
        });

        app.post("/reviews/{userid}", ctx -> {
            ObjectMapper om = new ObjectMapper();
            Review newreview = om.readValue(ctx.body(), Review.class);
            rs.addReview(newreview.getMovietitle(),newreview.getRating(),newreview.getReview(),Integer.parseInt(ctx.pathParam("userid")));
        });

        app.delete("/reviews/delete/{userid}", ctx -> {
            rs.deleteReview(ctx.body().toString(),Integer.parseInt(ctx.pathParam("userid")));
        });

        app.post("/login/register", ctx -> {
            ObjectMapper om = new ObjectMapper();
            User newUser = om.readValue(ctx.body(), User.class);
            us.addUser(newUser.getUsername(), newUser.getPassword());
        });

        app.post("login/", ctx -> {
            ObjectMapper om = new ObjectMapper();
            User logUser = om.readValue(ctx.body(), User.class);
            ctx.json(us.loginUser(logUser.getUsername(), logUser.getPassword()));
        });
    }
}
