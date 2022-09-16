//establishing connectivity to html
let reviewButton = document.getElementById("reviewButton");
let addReviewButton = document.getElementById("addReviewButton");
let deleteReviewButton = document.getElementById("deleteReviewButton");
let titleInput = document.getElementById("titleInput");
let ratingInput = document.getElementById("ratingInput");
let reviewInput = document.getElementById("reviewInput");
let deleteReviewInput = document.getElementById("deleteReviewInput");

//establishing place to paste info
let content = document.getElementById("content");

//establish button functionality
reviewButton.addEventListener("click", getAllReviews);
addReviewButton.addEventListener("click", apiAddReview);
if (deleteReviewButton) {
    deleteReviewButton.addEventListener("click", deleteReview);
}

console.log(location.hash.substring(4));

//function to grab userid
function getUserId() {
    return location.hash.substring(4);
}

//function to retrieve reviews
async function getAllReviews() {
let response = await fetch("http://localhost:9001/reviews/" + location.hash.substring(4));
response = await response.json();
loadAllReviews(response);
}

//function to put reviews on page
function loadAllReviews(response) {
    content.innerText = "";
    let reviewList = document.createElement("ul");
    for (let i = 0; i < response.length; i++) {
        let movieTitle = document.createElement("li");
        let rating = document.createElement("p");
        let review = document.createElement("p");
        movieTitle.innerText = "Movie Title: " + response[i].movietitle;
        reviewList.appendChild(movieTitle);
        rating.innerText = "Rating: " + response[i].rating;
        reviewList.appendChild(rating);
        review.innerText = "Review: " + response[i].review;
        reviewList.appendChild(review);
    }
    content.appendChild(reviewList);
}

//function to send review to database
async function apiAddReview() {
    let inputReview = {
        movietitle:titleInput.value,
        rating:ratingInput.value,
        review:reviewInput.value
    };
    let response = await fetch("http://localhost:9001/reviews/" + location.hash.substring(4), {
        method:'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(inputReview)
    });
}

//function to delete a review from database
async function deleteReview() {
    let response = await fetch ("http://localhost:9001/reviews/delete/" + location.hash.substring(4), {
        method:'DELETE',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
          },
          body:deleteReviewInput.value
    });
}