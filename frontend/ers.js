const url = 'http://localhost:8080/project-1/';

document.getElementById("loginbtn").addEventListener("click",login);

async function login(){
    let inputtedUsername = document.getElementById("username").value;
    let inputtedPassword = document.getElementById("password").value;

    //put the inputted username and password from client into a JS object
    let userInfo = {
        username : inputtedUsername,
        password : inputtedPassword
    };                 
    //Ask Tim about this below. Where does the request come into play here.
    let loginResponse = await fetch(url + "login", {
        method : "POST",
        body : JSON.stringify(userInfo),
        credentials : "include"
        //Credentials:include will ensure that the cookie is captured, future fetch requests
        //will also require this value in order to sent the cookie back.
    });

    if(loginResponse.status == 200){
        let user = await loginResponse.json();//get json response and store in JS object
        document.getElementById("login-row").innerHTML = '';
        console.log(loginResponse);
        console.log(user);

        let logout = document.createElement("div");
        logout.id = "logout-row";
        logout.className = "row";

        let logoutButton = document.createElement("button");
        logoutButton.id = "logoutbtn";
        logoutButton.className = "btn btn-primary";
        logout.appendChild(logoutButton);

        let identifier = document.createElement("p");
        identifier.id = "identifyuser";
        logout.appendChild(identifier);

        document.getElementById("identifyuser").innerText = user.role + ": " + user.firstName + " " + user.lastName;
        document.getElementById("logout-row").appendChild(logout);

        //document.getElementById("logoutbtn").addEventListener("click",logout);
    }
};

//async function logout(){

//};