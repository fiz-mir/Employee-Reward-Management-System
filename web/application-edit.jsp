<%@page import="Bean.EmployeeBean"%>
<%@page import="Bean.ApplicationBean"%>
<%@page import="DAO.EmployeeDAO"%>
<%@page import="DAO.ApplicationDAO"%>
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
    //To get data employee
            int employee_id = Integer.parseInt(request.getParameter("Employee_id"));
            EmployeeBean empBean;
            EmployeeDAO empDAO = new EmployeeDAO();
            empBean = empDAO.getEmployee(employee_id);
 %>
 
  <%
    //To get data application
            int employeeid = Integer.parseInt(request.getParameter("Employee_id"));
            ApplicationBean appBean;
            ApplicationDAO appDao = new ApplicationDAO();
            appBean = appDao.getApplication(employeeid);
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
        <link rel="stylesheet" href="src/css/application-edit.css">
    </head>
    <body class="bg-light" oncontextmenu="return false;">
        <div class="header"></div>
        <div class="container"><br>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb justify-content-end">
                    <li class="breadcrumb-item"><a class="back1" href="list-of-application.jsp" title="Back">Back</a></li>
                </ol>
            </nav>
            
            <h2><u>APPLICATION FORM</u></h2><br>
            <p class="lead"><b><mark>PART A : Employee Details</mark></b></p>
            <form class="row g-3" name="myForm3" id="myForm3" action="ApplicationController" method="POST" autocomplete="off"  required>
                <div class="col-md-6">
                    <label for="Employee_id" class="form-label">ID:</label>
                    <input type="text" class="form-control" name="Employee_id" id="Employee_id" onkeypress="isInputNumber(event)" maxlength="5" pattern=".{5}" title="ID" value="<%= empBean.getEmpID()%>" readonly required>
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
                    <input type="number" class="form-control" name="Job_knowledge" id="Job_knowledge" title="Job Knowledge" min="0" max="10" value="<%= appBean.getJob_Knowledge()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Attitude_environment" class="form-label">Attitude Environment:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Attitude_environment" id="Attitude_environment" title="Attitude Environment" min="0" max="10" value="<%= appBean.getAttitude_Environment()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Quality_work" class="form-label">Quality Work:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Quality_work" id="Quality_work" title="Quality Work" min="0" max="10" value="<%= appBean.getQuality_Work()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Productivity_task" class="form-label">Productivity Task:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Productivity_task" id="Productivity_task" title="Productivity Task" min="0" max="10" value="<%= appBean.getProductivity_Task()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Punctual_attendance" class="form-label">Punctual Attendance:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Punctual_attendance" id="Punctual_attendance" title="Punctual Attendance" min="0" max="10" value="<%= appBean.getPunctual_Attendance()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Interpersonal_skill" class="form-label">Interpersonal Skill:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Interpersonal_skill" id="Interpersonal_skill" title="Interpersonal Skill" min="0" max="10" value="<%= appBean.getInterpersonal_Skill()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Initiative_innovation" class="form-label">Initiative Innovation:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Initiative_innovation" id="Initiative_innovation" title="Initiative Innovation" min="0" max="10" value="<%= appBean.getInitiative_Innovation()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Customer_service" class="form-label">Customer Service:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Customer_service" id="Customer_service" title="Customer Service" min="0" max="10" value="<%= appBean.getCustomer_Service()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div> 
                <div class="col-md-6">
                    <label for="Leadership_teamwork" class="form-label">Leadership Teamwork:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Leadership_teamwork" id="Leadership_teamwork" title="Leadership Teamwork" min="0" max="10" value="<%= appBean.getLeadership_Teamwork()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div>
                <div class="col-md-6">
                    <label for="Adaptability_flexibility" class="form-label">Adaptability Flexibility:<span class="notice">*</span></label>
                    <input type="number" class="form-control" name="Adaptability_flexibility" id="Adaptability_flexibility" title="Adaptability Flexibility" min="0" max="10" value="<%= appBean.getAdaptability_Flexibility()%>" onkeypress="isInputNumber(event)" oninput="validateInput(this)" required>
                </div> 
                <div class="col-md-6">
                    <label for="Categoryreward_id" class="form-label">Category Reward:<span class="notice">*</span></label>
                    <select name="Categoryreward_id" id="Categoryreward_id" title="Category Reward" class="form-select" required>
                        <option value=""></option>
                        <option value="1"<%if(appBean.getCategoryreward_id() == 1){%> selected <%}%>>Staff Discounts</option>
                        <option value="2"<%if(appBean.getCategoryreward_id() == 2){%> selected <%}%>>Increase Salary</option>
                        <option value="3"<%if(appBean.getCategoryreward_id() == 3){%> selected <%}%>>Bonus Pay</option>
                        <option value="4"<%if(appBean.getCategoryreward_id() == 4){%> selected <%}%>>Free Health Care</option>
                        <option value="5"<%if(appBean.getCategoryreward_id() == 5){%> selected <%}%>>Paid Vacation</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="TotalmarkC" class="form-label">Total Mark (Current):<span class="notice">*</span></label>
                    <input type="text" class="form-control" name="TotalmarkC" id="TotalmarkC" onkeypress="isInputNumber(event)" maxlength="100" title="Total Mark (Current)" value="<%= appBean.getTotal_Mark()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Totalmark" class="form-label">Total Mark (Updated):<span class="notice">*</span></label>
                    <input type="text" class="form-control" name="Totalmark" id="Totalmark" maxlength="100" title="Total Mark (Updated)" readonly required>
                </div>
                <div class="col-md-12"></div>
                <div class="col-md-12"></div>
                <div class="col-md-6">
                    <label for="Applicationform_id" class="form-label">Application ID:</label>
                    <input type="text" class="form-control" name="Applicationform_id" id="Applicationform_id" onkeypress="isInputNumber(event)" maxlength="5" title="Application ID" value="<%= appBean.getApplicationform_id()%>" readonly required>
                </div>
                <div class="col-md-6">
                    <label for="Administrator_id" class="form-label">Administrator ID:</label>
                    <input type="text" class="form-control" name="Administrator_id" id="Administrator_id" onkeypress="isInputNumber(event)" maxlength="5" title="Administrator ID" value="<%= adminID %>" readonly required>
                </div><br><br><br><br>
                <div class="d-grid gap-2 col-6 mx-auto">
                    <button type="submit" name="submit" id="submit" class="btn custom-btn" title="Update This Application">Edit | Save</button>
                    <button onclick="confirmDeletion()" type="submit" name="delete" id="delete" class="btn btn-danger" title="Delete This Application">Delete</button>
                    <input type="hidden" id="operation" name="operation" value="">
                </div>
            </form><br><br><br>
            <span style="color:red;text-align:center">
        <% String message = (String) request.getAttribute("msgs");
            if (message != null && !message.isEmpty()) { %>
                <script>alert('<%= message %>');</script>
        <% } %>
    </span>
        </div>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
 
            function validateInput(input) {
                var value = parseInt(input.value);
                var min = parseInt(input.min);
                var max = parseInt(input.max);
                if (isNaN(value) || value < min) {
                    input.value = min;
                } 
                else if (value > max) {
                    input.value = max;
                } 
                else {
                    input.value = value.toString();
                }
            }
            
            window.addEventListener('DOMContentLoaded', (event) => {
                var Job_knowledge = document.getElementById("Job_knowledge");
                var Attitude_environment = document.getElementById("Attitude_environment");
                var Quality_work = document.getElementById("Quality_work");
                var Productivity_task = document.getElementById("Productivity_task");
                var Punctual_attendance = document.getElementById("Punctual_attendance");
                var Interpersonal_skill = document.getElementById("Interpersonal_skill");
                var Initiative_innovation = document.getElementById("Initiative_innovation");
                var Customer_service = document.getElementById("Customer_service");
                var Leadership_teamwork = document.getElementById("Leadership_teamwork");
                var Adaptability_flexibility = document.getElementById("Adaptability_flexibility");
                var totalScoreInput = document.getElementById("Totalmark");
                Job_knowledge.addEventListener("input", calculateTotalScore);
                Attitude_environment.addEventListener("input", calculateTotalScore);
                Quality_work.addEventListener("input", calculateTotalScore);
                Productivity_task.addEventListener("input", calculateTotalScore);
                Punctual_attendance.addEventListener("input", calculateTotalScore);
                Interpersonal_skill.addEventListener("input", calculateTotalScore);
                Initiative_innovation.addEventListener("input", calculateTotalScore);
                Customer_service.addEventListener("input", calculateTotalScore);
                Leadership_teamwork.addEventListener("input", calculateTotalScore);
                Adaptability_flexibility.addEventListener("input", calculateTotalScore);
                function calculateTotalScore() {
                    var Job_knowledgeIN = parseInt(Job_knowledge.value);
                    var Attitude_environmentIN = parseInt(Attitude_environment.value);
                    var Quality_workIN = parseInt(Quality_work.value);
                    var Productivity_taskIN = parseInt(Productivity_task.value);
                    var Punctual_attendanceIN = parseInt(Punctual_attendance.value);
                    var Interpersonal_skillIN = parseInt(Interpersonal_skill.value);
                    var Initiative_innovationIN = parseInt(Initiative_innovation.value);
                    var Customer_serviceIN = parseInt(Customer_service.value);
                    var Leadership_teamworkIN = parseInt(Leadership_teamwork.value);
                    var Adaptability_flexibilityIN = parseInt(Adaptability_flexibility.value);
                    var totalScore = Job_knowledgeIN + Attitude_environmentIN + Quality_workIN + Productivity_taskIN + Punctual_attendanceIN + Interpersonal_skillIN + Initiative_innovationIN + Customer_serviceIN + Leadership_teamworkIN + Adaptability_flexibilityIN;
                    totalScoreInput.value = totalScore;
                }
                
            });
            
                    document.getElementById('submit').addEventListener('click', function() {
                    document.getElementById('operation').value = 'U';
                    document.getElementById('myForm3').submit();
                });

                    
                
                    const confirmDeletion = () => {
                    const response = confirm("Are you sure you want to delete the application?");

                    if (response) {
                        document.getElementById('operation').value = 'D';
                        document.getElementById('myForm3').submit();
                    } else {
                        alert("Deletion was cancelled");
                    }
                }
                
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
