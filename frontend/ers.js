const url = 'http://localhost:8080/project1';

document.getElementById("loginbtn").addEventListener("click",login);

async function login(){
    let inputtedName = document.getElementById("username").value;
    let inputtedPass = document.getElementById("password").value;
    let userInfo = {
        username : inputtedName,
        password : inputtedPass
    };
};