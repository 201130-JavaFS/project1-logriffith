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

    console.log(loginResponse);

    if(loginResponse.status === 200){
        console.log("In if-statement");
        let user = await loginResponse.json();//get json response and store in JS object
        document.getElementById("login-row").innerHTML = "";//puts no content into this element

        //delete this later?
        console.log(loginResponse);
        console.log(user);
       // userRole = user.role;

        let logout = document.getElementById("header");
        let logoutButton = document.createElement("button");
        logoutButton.id = "logoutbtn";
        logoutButton.className = "btn btn-primary";
        logout.appendChild(logoutButton);
        document.getElementById("logoutbtn").innerText = "Log Out";
        document.getElementById("logoutbtn").addEventListener("click",logoutFunc);

        let name = document.getElementById("user");
        let identifier = document.createElement("h5");
        identifier.id = "identifyuser";
        name.appendChild(identifier);

        let adjustPadding = document.getElementById("login-row");
        adjustPadding.id = "data";
        document.getElementById("identifyuser").innerText = user.role + ": " + user.firstName + " " + user.lastName;
        console.log("Request Succeeded!")

        //let data = document.getElementById("data");
        let newReimb = document.getElementById("table1");
        newReimb.className = "table table-hover table-bordered";
        newReimb.id = "newreimbtable";

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

        document.getElementById("pendhead").innerText = "Pending Reimbursement Requests:"
        document.getElementById("penduserid").innerText = "EmployeeId";
        document.getElementById("pendamount").innerText = "Amount";
        document.getElementById("penddescription").innerText ="Description";
        document.getElementById("pendtype").innerText = "Type";
        document.getElementById("pendsubmit").innerText = "Submitted On";

        document.getElementById("allhead").innerText = "All Reimbursements:";
        document.getElementById("userid").innerText = "EmployeeId";
        document.getElementById("amount").innerText = "Amount";
        document.getElementById("description").innerText ="Description";
        document.getElementById("type").innerText = "Type";
        document.getElementById("status").innerText = "Status";
        document.getElementById("submitted").innerText = "Submitted On";
        document.getElementById("resolved").innerText = "Resolved On";


            // let newRow = document.createElement("tr");
            // let cell = document.createElement("td");
            // let insertId = document.createElement("input");
            // insertId.type = "text";
            // insertId.id = "newreimbid";


            // let pendHeading = document.createElement("h4");
            // pendHeading.id = "pendhead";
            // pendHeading.className = "heading";
            // data.appendChild(pendHeading);
            
            // let requests = document.createElement("button");
            // requests.id = "pendingrequests";
            // requests.className = "btn btn-danger";
            // data.appendChild(requests);
            // document.getElementById("pendingrequests").innerText = "Pending Requests";

            // let pendTable = document.createElement("table");
            // pendTable.className = "table table-hover table-bordered";
            // pendTable.id = "pendtable";

            // let pendLabel = document.createElement("thead");
            // pendLabel.id = "pendlabel";

            // let pendRow0 = document.createElement("tr");
            // pendRow0.id = "pendrow0";

            // let label1 = document.createElement("th");
            // label1.id = "firstname";
            // pendRow0.appendChild(lable1);
            // document.getElementById("firstname").innerText = "First Name";

            // let label2 = document.createElement("th");
            // label2.id = "lastname";
            // pendRow0.appendChild(lable2);
            // document.getElementById("lastname").innerText = "Last Name";

            // let label3 = document.createElement("th");
            // label3.className = "amount";
            // pendRow0.appendChild(label3);
            // document.getElementsByClassName("amount").innerText = "Amount";

            // let label4 = document.createElement("th");
            // label4.className = "description";
            // pendRow0.appendChild(label4);
            // document.getElementsByClassName("description").innerText = "Description";

            // let label5 = document.createElement("th");
            // label5.className = "type";
            // pendRow0.appendChild(label5);
            // document.getElementsByClassName("type").innerText = "Type";

            // let label6 = document.createElement("th");
            // label6.id = "status";
            // pendRow0.appendChild(label6);
            // document.getElementsByClassName("status").innerText = "Status";

            // pendLabel.appendChild(pendRow0);
            // pendTable.appendChild(pendLabel);
            // data.appendChild(pendTable);
    }else{
        console.log("Request Failed :(")
        let error = document.getElementById("login-row");
        let message = document.createElement("h6");
        message.id = "message";
        error.appendChild(message);
        document.getElementById("message").innerText = "Your Password or Username is Incorrect";
    }
};

async function logoutFunc(){
    let logoutResponse = await fetch(url + "",{credentials:"include"});
    if(logoutResponse.status === 200){
        console.log("Logged Out Successfully");
    }else{
        console.log("Logout Failed")
    }
};