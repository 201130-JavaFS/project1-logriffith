const url = 'http://localhost:8080/project-1/';
let userRole;
let employeeId;

document.getElementById("loginbtn").addEventListener("click", login);

async function login() {
    console.log("In Login Function")
    let inputtedUsername = document.getElementById("username").value;
    let inputtedPassword = document.getElementById("password").value;

    //put the inputted username and password from client into a JS object
    let userInfo = {
        username: inputtedUsername,
        password: inputtedPassword
    };

    let loginResponse = await fetch(url + "login", {
        method: "POST",
        body: JSON.stringify(userInfo),//adds the credentials to the request body
        credentials: "include"
        //Credentials:include will ensure that the cookie is captured, future fetch requests
        //will also require this value in order to sent the cookie back.
    });

    console.log(loginResponse);

    if (loginResponse.status === 200) {
        console.log("In if-statement");
        let user = await loginResponse.json();//get json response and store in JS object
        document.getElementById("login-row").innerHTML = "";//puts no content into this element

        console.log(user);
        userRole = user.role;
        employeeId = user.userId;

        let logout = document.getElementById("header");
        let logoutButton = document.createElement("button");
        logoutButton.id = "logoutbtn";
        logoutButton.className = "btn btn-primary";
        logout.appendChild(logoutButton);
        document.getElementById("logoutbtn").innerText = "Log Out";
        document.getElementById("logoutbtn").addEventListener("click", logoutFunc);

        let name = document.getElementById("user");
        let identifier = document.createElement("h5");
        identifier.id = "identifyuser";
        name.appendChild(identifier);

        let adjustPadding = document.getElementById("login-row");
        adjustPadding.id = "data";
        document.getElementById("identifyuser").innerText = userRole + ": " + user.firstName + " " + user.lastName;
        console.log("Request Succeeded!")

        //let data = document.getElementById("data");
        let newReimbTable = document.getElementById("table1");
        newReimbTable.className = "table table-hover table-bordered";
        newReimbTable.id = "newreimbtable";

        let pendReimb = document.getElementById("table2");
        pendReimb.className = "table table-hover table-bordered";
        pendReimb.id = "pendreimbtable";

        let allReimb = document.getElementById("table3");
        allReimb.className = "table table-hover table-bordered";
        allReimb.id = "allreimbtable";

        document.getElementById("newhead").innerText = "Request New Reimbursement:";
        document.getElementById("newamount").innerText = "Amount";
        document.getElementById("newdescription").innerText = "Description";
        document.getElementById("newtype").innerText = "Type";

        let newReimb = document.getElementById("sendreimb");
        let sendNew = document.createElement("button");
        sendNew.id = "newbtn";
        sendNew.className = "btn btn-danger";
        newReimb.appendChild(sendNew);
        document.getElementById("newbtn").innerText = "Sumbit Request";
        document.getElementById("newbtn").addEventListener("click", sendReimb);

        document.getElementById("pendhead").innerText = "Pending Reimbursement Requests:"
        document.getElementById("penduserid").innerText = "EmployeeId";
        document.getElementById("pendamount").innerText = "Amount";
        document.getElementById("penddescription").innerText = "Description";
        document.getElementById("pendtype").innerText = "Type";
        document.getElementById("pendsubmit").innerText = "Submitted On";

        document.getElementById("allhead").innerText = "All Reimbursements:";
        document.getElementById("userid").innerText = "EmployeeId";
        document.getElementById("amount").innerText = "Amount";
        document.getElementById("description").innerText = "Description";
        document.getElementById("type").innerText = "Type";
        document.getElementById("status").innerText = "Status";
        document.getElementById("submitted").innerText = "Submitted On";
        document.getElementById("resolved").innerText = "Resolved On";

    } else {
        console.log("Request Failed :(")
        let error = document.getElementById("login-row");
        let message = document.createElement("h6");
        message.id = "message";
        error.appendChild(message);
        document.getElementById("message").innerText = "Your Password or Username is Incorrect";
    }
};

async function logoutFunc() {
    let logoutResponse = await fetch(url + "logout", { credentials: "include" });
    if (logoutResponse.status === 200) {
        userRole = null;
        userId = null;
        console.log("Logged Out Successfully");
    } else {
        console.log("Logout Failed")
    }
};

async function sendReimb(){
    console.log("Making new Reimbursement Request");

    let newAmount = document.getElementById("getamount").value;
    let newDescript = document.getElementById("getdescript").value;
    let newType = document.getElementById("gettype").value;

    newAmount = Number(newAmount);
    console.log(newAmount);

    let reimbursement = {
        userId : employeeId,
        amount : newAmount,
        description : newDescript,
        type : newType
    }

    console.log(reimbursement);

    let response = await fetch(url + "new",{
        method : "POST",
        body : JSON.stringify(reimbursement),
        credentials : "include"
    });

    console.log(response.status);

    if (response.status === 201){
        document.getElementById("newbtn").innerText = "Reimbursement Request Submitted";
        console.log("Reimbursement recorded");
    }else{
        document.getElementById("newbtn").innerText = "Reimbursement Request Couldn't be Sent";
    }
}