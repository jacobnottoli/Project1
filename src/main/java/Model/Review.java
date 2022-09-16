package Model;

public class Review {

    String movietitle;
    double rating;
    String review;

    int userid;

    public Review () {

    }

    public Review (String movietitle, double rating, String review, int userid) {
        this.movietitle = movietitle;
        this.rating = rating;
        this.review = review;
        this.userid = userid;
    }

    @Override
    public String toString() {
        return movietitle + ": " + rating + ": " + review;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
