//input connectivity to HTML
let usernameInput = document.getElementById("usernameInput");
let passwordInput = document.getElementById("passwordInput");

//button connectivity to HTML
let registerButton = document.getElementById("registerButton");
let loginButton = document.getElementById("loginButton");

//content connectivity to HTML
let loginContent = document.getElementById("loginContent");

//event listeners
registerButton.addEventListener("click", addUser);
loginButton.addEventListener("click", loginFunc);

//add user function
async function addUser() {
    let userInfo = {username: usernameInput.value, password: passwordInput.value};
    let response = await fetch("http://localhost:9001/login/register", {
        method:'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(userInfo)
    });
    loginFunc;
}

async function loginFunc() {
    let userInfo = {username: usernameInput.value, password: passwordInput.value};
    let response = await fetch("http://localhost:9001/login/", {
        method:'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
          },
        body:JSON.stringify(userInfo)
    });
    response = await response.text();
    doLogin(response);
}

function doLogin(response) {
    if (response == 0) {
        loginContent.innerText = "Login Failed! Incorrect username and password!";
    } else {
        window.location = "reviews.html" + "#id=" + response; 
    }
}