const url = 'http://localhost:8080/project-1/';
let userRole;
let pendingStatus = [];

document.getElementById("loginbtn").addEventListener("click", login);

async function login() {
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

    if (loginResponse.status === 200) {
        let user = await loginResponse.json();//get json response and store in JS object
        document.getElementById("login-row").innerHTML = "";//puts no content into this element

        console.log(user);
        userRole = user.role;

        //create logout button
        let logout = document.getElementById("header");
        let logoutButton = document.createElement("button");
        logoutButton.id = "logoutbtn";
        logoutButton.className = "btn btn-primary";
        logout.appendChild(logoutButton);
        document.getElementById("logoutbtn").innerText = "Log Out";
        document.getElementById("logoutbtn").addEventListener("click", logoutFunc);

        //put user identification on page
        let name = document.getElementById("user");
        let identifier = document.createElement("h5");
        identifier.id = "identifyuser";
        name.appendChild(identifier);

        let adjustPadding = document.getElementById("login-row");
        adjustPadding.id = "data";
        document.getElementById("identifyuser").innerText = userRole + ": " + user.firstName + " " + user.lastName;

        //modify tables/develop tables
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

        //create new reimbursement input elements
        let newAmount = document.getElementById("input-amount");
        let inputAmount = document.createElement("input");
        inputAmount.id = 'getamount';
        newAmount.appendChild(inputAmount);
        document.getElementById('dollar').innerHTML = '$';

        let newDescription = document.getElementById('input-description');
        let inputDescription = document.createElement('input');
        inputDescription.id = 'getdescript';
        newDescription.appendChild(inputDescription);

        let newType = document.getElementById("input-type");
        let inputType = document.createElement('select');
        inputType.id = "get-type";

        let option1 = document.createElement("option");
        option1.text = "food";
        inputType.append(option1);

        let option2 = document.createElement("option");
        option2.text = "lodging";
        inputType.append(option2);

        let option3 = document.createElement("option");
        option3.text = "travel";
        inputType.append(option3);

        let option4 = document.createElement("option");
        option4.text = "other";
        inputType.append(option4);

        newType.appendChild(inputType);

        //create new reimbursement button
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

        //create button for getting the pending reimbursements
        let pending = document.getElementById("findpend");
        let pendingBtn = document.createElement("button");
        pendingBtn.id = "pendingbtn";
        pendingBtn.className = "btn btn-danger";
        pending.appendChild(pendingBtn);
        document.getElementById("pendingbtn").innerText = "Find Pending";
        document.getElementById("pendingbtn").addEventListener("click", findPending);

        //create button for resolving requests (for managers only)
        if (userRole === 'Manager') {
            let update = document.getElementById('updatepend');
            let resolve = document.createElement('button');
            resolve.id = 'resolve-btn';
            resolve.className = 'btn btn-danger';
            update.appendChild(resolve);
            document.getElementById('resolve-btn').innerText = 'Resolve Requests';
            document.getElementById('resolve-btn').addEventListener('click', resolveRequests);
        } else {
            console.log("Sorry, you are not a manager.")
        }

        document.getElementById("allhead").innerText = "All Reimbursements:";
        document.getElementById("userid").innerText = "EmployeeId";
        document.getElementById("amount").innerText = "Amount";
        document.getElementById("description").innerText = "Description";
        document.getElementById("type").innerText = "Type";
        document.getElementById("status").innerText = "Status";
        document.getElementById("submitted").innerText = "Submitted On";
        document.getElementById("resolved").innerText = "Resolved On";

        //create button for getting all reimbursements
        let allReimbs = document.getElementById("getall");
        let allReimbsBtn = document.createElement("button");
        allReimbsBtn.id = "allreimbbtn";
        allReimbsBtn.className = "btn btn-danger";
        allReimbs.appendChild(allReimbsBtn);
        document.getElementById("allreimbbtn").innerText = "Find All";
        document.getElementById("allreimbbtn").addEventListener("click", getAll);


    } else {
        console.log("Login Failed :(")
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
        pendingStatus = [];
        console.log("Logged Out Successfully");

        //Clear Page
        let pageContent = document.getElementById("fullcontainer");
        let logoutButton = document.getElementById("header");
        pageContent.innerHTML = "";
        logoutButton.innerHTML = "";

        //Back to Login/Reload the page
        pageContent.innerHTML = "<div class='row' id='login-row'> <h4 class='col-lg-12' id='enter'>Please Enter Your Username and Password:</h4> <input class='col-sm-12 form-control' id='username' type='text' placeholder='USERNAME'> <input class='col-sm-12 form-control' id='password' type='password' placeholder='PASSWORD'> <br> <br> <button id='loginbtn' class='btn btn-danger'>Login</button> </div> <div id='user'></div> <br><br><br><br> <div class='row' id='newreimb-row'><h4 class='col-lg-12' id='newhead'></h4><table id='table1'> <thead> <tr> <th id='newamount'></th> <th id='newdescription'></th> <th id='newtype'></th></tr> </thead> <tbody id='newreimb'> <tr id='newreimbinfo'> <td id='input-amount'> <span id ='dollar'></span> </td> <td id='input-description'> </td> <td id='input-type'> </td> </tr> </tbody> </table> <div id='sendreimb'></div> </div> <br><br> <div class='row' id='pend-row'> <h4 class='col-lg-12' id='pendhead'></h4> <div id='getpending'></div> <table id='table2'> <thead> <tr> <th id='penduserid'></th> <th id='pendamount'></th> <th id='penddescription'></th> <th id='pendtype'></th> <th id='pendsubmit'></th> <th id='pendstatus'></th> </tr> </thead> <tbody id='pendreimb'></tbody> </table> <div id='findpend'></div> <div id='updatepend'></div> </div> <br><br> <div class='row' id='allreimb-row'> <h4 class='col-lg-12' id='allhead'></h4> <table id='table3'> <thead><tr> <th id='userid'></th><th id='amount'></th><th id='description'></th><th id='type'></th> <th id='submitted'></th> <th id='resolved'></th> <th id='status'></th> </tr></thead> <tbody id='allreimb'></tbody> </table> <div id='getall'></div> </div>";
        document.getElementById("loginbtn").addEventListener("click", login);
        
    } else {
        console.log("Logout Failed")
    }
};

async function sendReimb() {
    console.log("Making new Reimbursement Request");

    let newAmount = document.getElementById("getamount").value;
    newAmount.className = "col-sm-4 form-control";
    newAmount.type = "text";
    let newDescript = document.getElementById("getdescript").value;
    newDescript.className = "col-sm-4 form-control";
    newDescript.type = "text";
    let newType = document.getElementById("get-type").value;
    newType.className = "col-sm-4 form-control";
    //newType.type = "text";

    newAmount = Number(newAmount).toFixed(2);
    console.log(newAmount);

    let reimbursement = {
        amount: newAmount,
        description: newDescript,
        type: newType
    }

    console.log("New Reimbursement Request:");
    console.log(reimbursement);

    let response = await fetch(url + "new", {
        method: "POST",
        body: JSON.stringify(reimbursement),
        credentials: "include"
    });

    if (response.status === 201) {
        //document.getElementById("newbtn").innerText = "Request Submitted";
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

        let allResponse = await fetch(url + "all/employee", {
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

async function findPending() {
    document.getElementById("pendreimb").innerHTML = "";
    if (userRole === "Manager") {
        pendingStatus = [];
        let pendResponse = await fetch(url + "pending/manager", { credentials: "include" });

        if (pendResponse.status === 200) {
            let all = await pendResponse.json();//get json response and store in JS object
            //data is going to be an array because we are going to get all of the pending reimbursements
            console.log("All Pending Reimbursement Requests (Other Than Logged in Manager):");

            for (let r of all) {
                console.log(r);

                //need to encapsulate status and statusId in front end; should have done this in the back end in the models at the beginning
                let status = {
                    statusId: r.statusId,
                    statusType: r.status
                };

                pendingStatus.push(status);//add status object to array
                //note at this point all the status types should show as "pending"

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

                //create options for resolving requests (only for managers)
                let cell6 = document.createElement("td");
                let resolve = document.createElement("select");
                resolve.className = "status-options";

                let option1 = document.createElement("option");
                option1.text = r.status;
                resolve.appendChild(option1);

                let option2 = document.createElement("option");
                option2.text = "approve";
                resolve.appendChild(option2);

                let option3 = document.createElement("option");
                option3.text = "deny";
                resolve.appendChild(option3);

                cell6.appendChild(resolve);
                row.appendChild(cell6);

                document.getElementById("pendreimb").appendChild(row);
            }
            console.log("pendingStatus array:");
            console.log(pendingStatus);
        } else {
            console.log("status code is not 200");
        }
        //If the user is not a manager, then they are a regular employee and cannot resolve pending reimbursement requests
    } else {
        // let allResponse = await fetch(url + "allforuser", { credentials: "include" });
        // console.log(allResponse.status);

        let pendResponse = await fetch(url + "pending/employee", {
            method: "POST",
            body: JSON.stringify(""),
            credentials: "include"
        });

        if (pendResponse.status === 200) {
            let all = await pendResponse.json();//get json response and store in JS object
            //data is going to be an array because we are going to get all of the Reimbursements
            console.log("All User Pending Requests:");

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

                let cell7 = document.createElement("td");//create the cell
                cell7.innerHTML = r.status;//fills the cell
                row.appendChild(cell7);//appends the cell

                document.getElementById("pendreimb").appendChild(row);
            }
        }
    }
}

async function resolveRequests() {
    let resolved = [];
    //let statusArray = [];//needed to encapsulate status and statusId in front end; should have done this in the back end at the beginning
    let requests = document.getElementById('pendreimb').rows;//get the body of the table
    let selectTags = document.getElementsByClassName("status-options");//get all of the select tags
    console.log("There are " + requests.length + " pending requests.");

    for (let i = 0; i < requests.length; i++) {
        //let row = requests[i].cells;//get the cells from a row in the table
        let statusName = selectTags[i].options[selectTags[i].selectedIndex].value;//get the selected status from a select tag
        if(statusName === "deny"){
            let status = {
                statusId: pendingStatus[i].statusId,
                statusType: "denied"
            };
            resolved.push(status);
        }else if(statusName === "approve"){
            let status = {
                statusId: pendingStatus[i].statusId,
                statusType: "approved"
            };
            resolved.push(status);
        }
        else{
            console.log("pending request was not resolved.");
        }
    }

        // for(let i = 0; i < requests.length; i++){
        //     let row = requests[i].cells;
        //     console.log("This is a reimbursement:");
        //     for(let j = 0; j < row.length; j++){
        //         if(j === row.length - 1){
        //             let options = document.getElementsByClassName("select");
        //             let status = options[i];
        //             console.log(status.options[status.selectedIndex].value);
        //             console.log("------------------");
        //         }else{
        //         console.log(row[j].innerText);
        //         }
        //     }        
            
    if (resolved.length > 0) {
        let response = await fetch(url + "pending/manager/resolve", {
            method: "PUT",
            body: JSON.stringify(resolved),
            credentials: "include"
        });

        if (response.status === 200) {
            console.log("The resolved requests are:");
            console.log(resolved);
            findPending();
        }else{
            console.log("I'm sorry. You got a " + response.status);
            console.log(response);
        }

    } else {
        console.log("No requests were resolved because the manager did not change the status.")
    }
}