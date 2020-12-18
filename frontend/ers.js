const url = 'http://localhost:8080/project-1/';

document.getElementById("loginbtn").addEventListener("click",login);

async function login(){
    console.log("In Login Function")
    let inputtedUsername = document.getElementById("username").value;
    let inputtedPassword = document.getElementById("password").value;

    //put the inputted username and password from client into a JS object
    let userInfo = {
        username : inputtedUsername,
        password : inputtedPassword
    };

    console.log(userInfo);
    let loginResponse = await fetch(url + "login", {
        method : "POST",
        body : JSON.stringify(userInfo),//adds the credentials to the request body
        credentials : "include"
        //Credentials:include will ensure that the cookie is captured, future fetch requests
        //will also require this value in order to sent the cookie back.
    });

    if(loginResponse.status == 200){
        console.log("In if-statement");
        let user = await loginResponse.json();//get json response and store in JS object
        document.getElementById("login-row").innerHTML = "";//puts no content into this element

        //delete this later?
        console.log(loginResponse);
        console.log(user);

        let logout = document.getElementById("header");
        let logoutButton = document.createElement("button");
        logoutButton.id = "logoutbtn";
        logoutButton.className = "btn btn-primary";
        logout.appendChild(logoutButton);

        let name = document.getElementById("user");
        let identifier = document.createElement("h5");
        identifier.id = "identifyuser";
        name.appendChild(identifier);

        document.getElementById("logoutbtn").innerText = "Log Out";
        let adjustPadding = document.getElementById("login-row");
        adjustPadding.id = "data";
        document.getElementById("identifyuser").innerText = user.role + ": " + user.firstName + " " + user.lastName;
        console.log("Request Succeeded!")
        //document.getElementById("logoutbtn").addEventListener("click",logout);
    }else{
        console.log("Request Failed :(")
        let error = document.getElementById("login-row");
        let message = document.createElement("h6");
        message.id = "message";
        error.appendChild(message);
        document.getElementById("message").innerText = "Your Password or Username is Incorrect";
    }
};

//async function logout(){

//};