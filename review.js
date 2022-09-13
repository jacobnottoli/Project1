let reviewButton = document.getElementById("reviewButton");
let content = document.getElementById("content");

reviewButton.addEventListener("click", getAllReviews);

async function getAllReviews() {
let response = await fetch("http://localhost:9001/reviews");
response = await response.json();
loadAllReviews(response);
}

function loadAllReviews(response) {
    content.innerText = "";
    let reviewList = document.createElement("ul");
    for (let i = 0; i < response.length; i++) {
        let movieTitle = document.createElement("li");
        let rating = document.createElement("p");
        let review = document.createElement("p");
        movieTitle.innerText = response[i].movietitle;
        reviewList.appendChild(movieTitle);
        rating.innerText = response[i].rating;
        reviewList.appendChild(rating);
        review.innerText = response[i].review;
        reviewList.appendChild(review);
    }
    content.appendChild(reviewList);
}