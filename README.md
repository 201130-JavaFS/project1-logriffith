# Employee Reimbursment System (ERS)

## Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

**State-chart Diagram (Reimbursement Statuses)** 
![](./imgs/state-chart.jpg)

**Reimbursement Types**

Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

**Logical Model**
![](./imgs/logical.jpg)

**Physical Model**
![](./imgs/physical.jpg)

**Use Case Diagram**
![](./imgs/use-case.jpg)

**Activity Diagram**
![](./imgs/activity.jpg)

## Technical Requirements

The back-end system shall use JDBC to connect to a Postgres database. The application shall deploy onto a Tomcat Server. The middle tier shall use Servlet technology for dynamic Web application development. The front-end view shall use HTML/CSS/JavaScript to make an application that can call server-side components in a generally RESTful manner. Passwords shall be encrypted in Java and securely stored in the database. The middle tier shall follow proper layered architecture, have reasonable (~70%) test coverage of the service layer, and implement log4j for appropriate logging. Webpages shall be styled to be functional and readable. 

## Technologies Used
* Java SE Development Kit 8
* Git
* Spring Tool Suite 4 - version 4.8.1 
* Apache Maven - version 3.6.3
* Apache Tomcat 9
* DBeaver - version 7.3.0
* Visual Studio Code
* Postman

## Features
* User/Employee can login
* After loggin in, user can make a new reimbursement request
* Regular employees can view all of their pending requests, as well as all of their previous requests
* Managers can view all pending requests, as well as all requests for the company
* User's name and role in the company is displayed in the single page application (SPA)
* User can log out, which invalidates the session

**To-do List:**
* Add feature for manager to approve or deny requests that are not their own
* Improve the functionality of the front end
* Make the client view more appealing

## Getting Started
**Download and Install:**
* Java SE Development Kit 8
* Spring Tool Suite 4 - version 4.8.1 
* Apache Maven - version 3.6.3
* Apache Tomcat 9
* Latest version of PostgresSQL
* DBeaver - version 7.3.0
* [Click here](https://github.com/201130-JavaFS/Environment-Installation-Guide) for help with this process

**Setup:**
* Clone the repository on your local system
* Set the JRE in Spring Tool Suite to JKD 1.8
* In DBeaver, execute the command "CREATE DATABASE ers;" in a SQL Editor
* Then, execute the PostgresSQL scripts in the sql folder in the repository (ERS_Tables.sql first)
* Assign database username and password to the appropriate references in com.ers.utils.DbConnection (environment variable - optional)
* Assign 127 to the encryption key reference
* Import local repository into Spring Tool Suite
* Right-click on project and under "Maven" select "Update Project"
* Alter the file path in the log4j2.properties file to suit your needs

## Usage
In Spring Tool Suite, right-click on the project and under "Run-As" select "Run on Server." Next, select the path for Tomcat 9 on the system (only needed the first time the application runs) and click finish. In the "frontend" folder in the local repository, double click on the ers.html file to open the application in a browser. Finally, login using the usernames and passwords listed in ERS_Insert.sql.
