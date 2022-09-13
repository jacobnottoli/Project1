import Model.Review;
import Services.ReviewServices;
import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;

import java.util.List;

public class ReviewAPI {
    public static void main(String[] args) {
        ReviewServices rs = new ReviewServices();
        Javalin app = Javalin.create(JavalinConfig::enableCorsForAllOrigins).start(9001);

        app.get("/reviews", ctx -> {ctx.json(rs.getAllReviews());});
    }
}
