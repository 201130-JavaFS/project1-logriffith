const url = 'http://localhost:8080/project-1/';
let userRole;


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
        document.getElementById("pendstatus").innerText = "Status";

        document.getElementById("allhead").innerText = "All Reimbursements:";
        document.getElementById("userid").innerText = "EmployeeId";
        document.getElementById("amount").innerText = "Amount";
        document.getElementById("description").innerText = "Description";
        document.getElementById("type").innerText = "Type";
        document.getElementById("status").innerText = "Status";
        document.getElementById("submitted").innerText = "Submitted On";
        document.getElementById("resolved").innerText = "Resolved On";

        let allReimbs = document.getElementById("getall");
        let allReimbsBtn = document.createElement("button");
        allReimbsBtn.id = "allreimbbtn";
        allReimbsBtn.className = "btn btn-danger";
        allReimbs.appendChild(allReimbsBtn);
        document.getElementById("allreimbbtn").innerText = "Get All";
        document.getElementById("allreimbbtn").addEventListener("click", getAll);


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
        console.log("Logged Out Successfully");
    } else {
        console.log("Logout Failed")
    }
};

async function sendReimb() {
    console.log("Making new Reimbursement Request");

    let newAmount = document.getElementById("getamount").value;
    newAmount.className = "form-control";
    let newDescript = document.getElementById("getdescript").value;
    newDescript.className = "form-control";
    let newType = document.getElementById("gettype").value;
    newType.className = "form-control";

    newAmount = Number(newAmount);
    console.log(newAmount);

    let reimbursement = {
        amount: newAmount,
        description: newDescript,
        type: newType
    }

    console.log(reimbursement);

    let response = await fetch(url + "new", {
        method: "POST",
        body: JSON.stringify(reimbursement),
        credentials: "include"
    });

    console.log(response.status);

    if (response.status === 201) {
        document.getElementById("newbtn").innerText = "Request Submitted";
        console.log("Reimbursement recorded");
    } else {
        document.getElementById("newbtn").innerText = "Reimbursement Request Couldn't be Sent";
    }
}

async function getAll() {
    console.log("In get all Reimbursements");
    document.getElementById("allreimb").innerHTML = "";
    if (userRole === "Manager") {
        let allResponse = await fetch(url + "all", { credentials: "include" });
        console.log(allResponse.status);

        if (allResponse.status === 200) {
            let all = await allResponse.json();//get json response and store in JS object
            //data is going to be an array because we are going to get all of the Reimbursements

            for (let r of all) {
                console.log(r);
                let row = document.createElement("tr");

                let cell1 = document.createElement("td");//create the cell
                cell1.innerHTML = r.userId;//fills the cell
                row.appendChild(cell1);//appends the cell

                let cell2 = document.createElement("td");//create the cell
                cell2.innerHTML = r.amount;//fills the cell
                row.appendChild(cell2);//appends the cell

                let cell3 = document.createElement("td");//create the cell
                cell3.innerHTML = r.description;//fills the cell
                row.appendChild(cell3);//appends the cell

                let cell4 = document.createElement("td");//create the cell
                cell4.innerHTML = r.type;//fills the cell
                row.appendChild(cell4);//appends the cell

                let cell5 = document.createElement("td");//create the cell
                cell5.innerHTML = r.submitted;//fills the cell
                row.appendChild(cell5);//appends the cell

                let cell6 = document.createElement("td");//create the cell
                cell6.innerHTML = r.resolved;//fills the cell
                row.appendChild(cell6);//appends the cell

                let cell7 = document.createElement("td");//create the cell
                cell7.innerHTML = r.status;//fills the cell
                row.appendChild(cell7);//appends the cell

                document.getElementById("allreimb").appendChild(row);
            }
        } else {
            console.log("status is not 200");
        }

    } else {
        // let allResponse = await fetch(url + "allforuser", { credentials: "include" });
        // console.log(allResponse.status);

        let allResponse = await fetch(url + "allforuser", {
            method: "POST",
            body: JSON.stringify(""),
            credentials: "include"
        });

        if (allResponse.status === 200) {
            let all = await allResponse.json();//get json response and store in JS object
            //data is going to be an array because we are going to get all of the Reimbursements

            for (let r of all) {
                console.log(r);
                let row = document.createElement("tr");

                let cell1 = document.createElement("td");//create the cell
                cell1.innerHTML = r.userId;//fills the cell
                row.appendChild(cell1);//appends the cell

                let cell2 = document.createElement("td");//create the cell
                cell2.innerHTML = r.amount;//fills the cell
                row.appendChild(cell2);//appends the cell

                let cell3 = document.createElement("td");//create the cell
                cell3.innerHTML = r.description;//fills the cell
                row.appendChild(cell3);//appends the cell

                let cell4 = document.createElement("td");//create the cell
                cell4.innerHTML = r.type;//fills the cell
                row.appendChild(cell4);//appends the cell

                let cell5 = document.createElement("td");//create the cell
                cell5.innerHTML = r.submitted;//fills the cell
                row.appendChild(cell5);//appends the cell

                let cell6 = document.createElement("td");//create the cell
                cell6.innerHTML = r.resolved;//fills the cell
                row.appendChild(cell6);//appends the cell

                let cell7 = document.createElement("td");//create the cell
                cell7.innerHTML = r.status;//fills the cell
                row.appendChild(cell7);//appends the cell

                document.getElementById("allreimb").appendChild(row);
            }

        }
    }
}
// newAmount.className = "form-control";