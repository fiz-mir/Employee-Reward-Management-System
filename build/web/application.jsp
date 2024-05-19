<%@page import="Bean.EmployeeBean"%>
<%@page import="DAO.EmployeeDAO"%>
<%@page import="DAO.LoginDAO"%>
<%@page import="util.connectDB"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String adminID = (String)session.getAttribute("session_adminID");
    String adminrole = (String)session.getAttribute("session_adminRole");
    if (adminID == null && adminrole == null) { //if null (user not login) redirect to login page
    request.setAttribute("msgs", "You have not login");
    out.println( "<script> location.href='./index.jsp'; </script>");
    }
%> 

 <%
            int employee_id = Integer.parseInt(request.getParameter("Employee_id"));
            EmployeeBean empBean;
            EmployeeDAO dao = new EmployeeDAO();
            empBean = dao.getEmployee(employee_id);
 %>
 
 <%
    //establish a connection to database
    Connection conn = connectDB.createConnection();
    Statement stmt = conn.createStatement(); //create the statement
    String sqlemp;

    sqlemp = "SELECT * FROM CATEGORY_REWARD";
    
    ResultSet rs = stmt.executeQuery(sqlemp);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>ORION</title>
        <meta name="description" content="EMPLOYEE REWARD MANAGEMENT SYSTEM">
        <meta name="keywords" content="EMPLOYEE REWARD MANAGEMENT SYSTEM">
        <meta name="author" content="Orion">
        <link rel="shortcut icon" type="image/ico" href="src/img/ORION-LOGO.png">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="src/css/application.css">
    </head>
    <body class="bg-light" oncontextmenu="return false;">
        <div class="header"></div>
        <div class="container"><br>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb justify-content-end">
                    <li class="breadcrumb-item"><a class="back1" href="index.jsp" title="Back">Back</a></li>
                </ol>
            </nav>
            <h2><u>APPLICATION FORM</u></h2><br>
            <p class="lead"><b><mark>PART A : Employee Details</mark></b></p>
            <form class="row g-3" name="myForm2" id="myForm2" action="ApplicationController" method="POST" autocomplete="off"  required>
                <div class="col-md-6">
                    <label for="Employee_id" class="form-label">ID:</label>
                    <input type="text" class="form-control" name="Employee_id" id="Employee_id" maxlength="5" pattern=".{5}" title="ID" value="<%= empBean.getEmpID()%>" readonly>
                </div>
                <div class="col-md-6">
                    <label for="Employee_name" class="form-label">Name:</label>
                    <input type="text" class="form-control" name="Employee_name" id="Employee_name" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Name" value="<%= empBean.getEmpName()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employee_email" class="form-label">Email:</label>
                    <input type="email" class="form-control" name="Employee_email" id="Employee_email" pattern=".*\S.*" title="Email" value="<%= empBean.getEmpEmail()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employeerole_id" class="form-label">Role:</label>
                    <input type="text" class="form-control" name="Employeerole_id" id="Employeerole_id" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Role" value="<%= empBean.getEmpRole()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employeestatus_id" class="form-label">Status:</label>
                    <input type="text" class="form-control" name="Employeestatus_id" id="Employeestatus_id" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Status" value="<%= empBean.getEmpStatus()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employeelevel_id" class="form-label">Level:</label>
                    <input type="text" class="form-control" name="Employeelevel_id" id="Employeelevel_id" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Level" value="<%= empBean.getEmpLevel()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employeetype_id" class="form-label">Type:</label>
                    <input type="text" class="form-control" name="Employeetype_id" id="Employeetype_id" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Type" value="<%= empBean.getEmptype()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Employeedepartment_id" class="form-label">Department:</label>
                    <input type="text" class="form-control" name="Employeedepartment_id" id="Employeedepartment_id" pattern=".*\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Department" value="<%= empBean.getEmpDepartment()%>" readonly required>
                </div><br><br><br><br><br>

                <p class="lead"><b><mark>PART B : Evaluation Form</mark></b></p>
                <div class="col-md-6">
                    <label for="Job_knowledge" class="form-label">Job Knowledge:<span class="notice">*</span></label>
                    <select name="Job_knowledge" id="Job_knowledge" title="Job Knowledge" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Attitude_environment" class="form-label">Attitude Environment:<span class="notice">*</span></label>
                    <select name="Attitude_environment" id="Attitude_environment" title="Attitude Environment" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Quality_work" class="form-label">Quality Work:<span class="notice">*</span></label>
                    <select name="Quality_work" id="Quality_work" title="Quality Work" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Productivity_task" class="form-label">Productivity Task:<span class="notice">*</span></label>
                    <select name="Productivity_task" id="Productivity_task" title="Productivity Task" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Punctual_attendance" class="form-label">Punctual Attendance:<span class="notice">*</span></label>
                    <select name="Punctual_attendance" id="Punctual_attendance" title="Punctual Attendance" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Interpersonal_skill" class="form-label">Interpersonal Skill:<span class="notice">*</span></label>
                    <select name="Interpersonal_skill" id="Interpersonal_skill" title="Interpersonal Skill" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Initiative_innovation" class="form-label">Initiative Innovation:<span class="notice">*</span></label>
                    <select name="Initiative_innovation" id="Initiative_innovation" title="Initiative Innovation" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Customer_service" class="form-label">Customer Service:<span class="notice">*</span></label>
                    <select name="Customer_service" id="Customer_service" title="Customer Service" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div> 
                <div class="col-md-6">
                    <label for="Leadership_teamwork" class="form-label">Leadership Teamwork:<span class="notice">*</span></label>
                    <select name="Leadership_teamwork" id="Leadership_teamwork" title="Leadership Teamwork" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Adaptability_flexibility" class="form-label">Adaptability Flexibility:<span class="notice">*</span></label>
                    <select name="Adaptability_flexibility" id="Adaptability_flexibility" title="Adaptability Flexibility" class="form-select" onChange="calculatePrice()" required>
                        <option value="" selected></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </div>                                
                <div class="col-md-6">
                    <label for="Categoryreward_id" class="form-label">Category Reward:<span class="notice">*</span></label>
                    <select name="Categoryreward_id" id="Categoryreward_id" title="Category Reward" class="form-select" required>
                        <option value="" selected></option>
                          <% while (rs.next()) { %>                   
                            <option value="<%= rs.getInt("categoryreward_id") %>"><%= rs.getString("categoryreward_name") %></option>
                          <% } %> 
                     
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="Totalmark" class="form-label">Total Mark:<span class="notice">*</span></label>
                    <input type="text" class="form-control" name="Totalmark" id="Totalmark" onkeypress="isInputNumber(event)" maxlength="100" title="Total Mark" readonly required>
                </div>
                <div class="col-md-12"></div>
                <div class="col-md-12"></div>
                <div class="col-md-6">
                    <label for="Applicationform_id" class="form-label">Application ID:</label>
                    <input type="text" class="form-control" name="Applicationform_id" id="Applicationform_id" onkeypress="isInputNumber(event)" maxlength="5" title="Application ID" value=""  readonly required>
                </div>
                
                <div class="col-md-6">
                    <label for="Administrator_id" class="form-label">Administrator ID:</label>
                    <input type="text" class="form-control" name="Administrator_id" id="Administrator_id" onkeypress="isInputNumber(event)" maxlength="5" title="Administrator ID" value="<%= adminID %>"  readonly required>
                </div><br><br><br><br>
               
                <div class="d-grid gap-2 col-6 mx-auto">
                    <input type="hidden" name="operation" value="C">
                    <button type="submit" name="submit" class="btn custom-btn" title="Submit The Application Form">Submit</button>
                </div>
            </form><br><br><br>
            
        </div>
        <script>
            class InputValidator {
                isInputNumber(evt) {
                    var ch = String.fromCharCode(evt.which);
                    if (!/[0-9]/.test(ch)) {
                        evt.preventDefault();
                    }
                }
            }
            const inputValidator = new InputValidator();
            function isInputNumber(evt) {
                inputValidator.isInputNumber(evt);
            }
            
            class FormCalculator {
                constructor() {
                    this.selects = document.getElementsByTagName("select");
                    this.totalInput = document.getElementById("Totalmark");
                }
                calculateTotal() {
                    let total = 0;
                    for (let i = 0; i < this.selects.length; i++) {
                        const select = this.selects[i];
                        const value = parseInt(select.value);
                        if (!isNaN(value)) {
                            total += value;
                        }
                    }
                    this.totalInput.value = total;
                }
            }
            const calculator = new FormCalculator();
            function calculatePrice() {
                calculator.calculateTotal();
            }
            
            class RandomNumberGenerator {
                constructor(length) {
                    this.length = length;
                }
                generateRandomNumber() {
                    var randomNumber = "";
                    for (var i = 0; i < this.length; i++) {
                        randomNumber += Math.floor(Math.random() * 10);
                    }
                    return randomNumber;
                }
            }
            var generator = new RandomNumberGenerator(5);
            var randomNum = generator.generateRandomNumber();
            document.getElementById("Applicationform_id").value = randomNum;
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
