<%@page import="Bean.AdminBean"%>
<%@page import="DAO.AdminDAO"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
    String adminID = (String)session.getAttribute("session_adminID");
    String adminrole = (String)session.getAttribute("session_adminRole");
    if (adminID == null && adminrole == null) { //if null (user not login) redirect to login page
    request.setAttribute("msgs", "You have not login");
    out.println( "<script> location.href='./index.jsp'; </script>");
    }
%> 

<%
    // To get data employee
    int employeeId = 0; // Initialize with a default value
    AdminBean adBean = null;
    String employeeIdParam = request.getParameter("Employee_id");
    if (employeeIdParam != null && employeeIdParam.matches("\\d+")) {
        employeeId = Integer.parseInt(employeeIdParam);       
        AdminDAO adDAO = new AdminDAO();
        adBean = adDAO.getAdmin(employeeId);
    }
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
        <link rel="stylesheet" href="src/css/application-add.css">
    </head>
    <body class="bg-light" oncontextmenu="return false;">
        <div class="header"></div>
        <div class="container"><br>
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb justify-content-end">
                    <li class="breadcrumb-item"><a class="back1" href="list-of-admin.jsp" title="Back">Back</a></li>
                </ol>
            </nav>
            
            <h2><u>ADD ADMINISTRATOR DETAILS</u></h2><br>
            <form class="row g-3" name="myForm4" id="myForm4" action="SearchEmployeeController" autocomplete="off" method="POST" required>
                <div class="col-md-6">
                    <label for="Employee_id" class="form-label">Employee ID:<span class="notice">*</span></label>
                    <input type="text" class="form-control" name="Employee_id" id="Employee_id" onkeypress="isInputNumber(event)" maxlength="5" pattern=".{5}" title="Employee ID" required>
                </div>
                <div class="d-grid gap-2 d-md-block">
                    <button type="submit" name="search" id="search" class="btn custom-btn" title="Search Employee ID">Search</button>
                </div>
            </form>
    </span>
            <hr class="my-4"><br>
            
            <p class="lead"><b><mark>Employee Details</mark></b></p>
            <form class="row g-3" name="myForm5" id="myForm5" action="AddAdminController" method="POST" autocomplete="off" required>
            <div class="col-md-6">
                <label for="Employee_id" class="form-label">ID:</label>
                <input type="text" class="form-control" name="employee_id" id="Employee_id" onkeypress="isInputNumber(event)" maxlength="5" pattern=".{5}" title="ID" value="${empty Employee ? '' : Employee.empID}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employee_name" class="form-label">Name:</label>
                <input type="text" class="form-control" name="Employee_name" id="Employee_name" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Name" value="${empty Employee ? '' : Employee.empName}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employee_email" class="form-label">Email:</label>
                <input type="email" class="form-control" name="Employee_email" id="Employee_email" pattern=".*\\S.*" title="Email" value="${empty Employee ? '' : Employee.empEmail}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employeerole_id" class="form-label">Role:</label>
                <input type="text" class="form-control" name="Employeerole_id" id="Employeerole_id" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Role" value="${empty Employee ? '' : Employee.empRole}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employeestatus_id" class="form-label">Status:</label>
                <input type="text" class="form-control" name="Employeestatus_id" id="Employeestatus_id" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Status" value="${empty Employee ? '' : Employee.empStatus}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employeelevel_id" class="form-label">Level:</label>
                <input type="text" class="form-control" name="Employeelevel_id" id="Employeelevel_id" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Level" value="${empty Employee ? '' : Employee.empLevel}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employeetype_id" class="form-label">Type:</label>
                <input type="text" class="form-control" name="Employeetype_id" id="Employeetype_id" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Type" value="${empty Employee ? '' : Employee.emptype}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="Employeedepartment_id" class="form-label">Department:</label>
                <input type="text" class="form-control" name="Employeedepartment_id" id="Employeedepartment_id" pattern=".*\\S.*" onkeyup="this.value = this.value.toUpperCase();" title="Department" value="${empty Employee ? '' : Employee.empDepartment}" readonly required>
            </div>
            <div class="col-md-6">
                <label for="administratorstatus_id" class="form-label">Administrator Status:<span class="notice">*</span></label>
                <select name="administratorstatus_id" id="administratorstatus_id" title="Administrator Status" class="form-select" required>
                    <option value="" ></option>
                    <option value="50" <%if(adBean != null && adBean.getAdministratorstatus_id() == 50){%> <%}%>>Active</option>
                    <option value="51" <%if(adBean != null && adBean.getAdministratorstatus_id() == 51){%> <%}%>>Inactive</option>                
                </select>
            </div>    
            <div class="col-md-12"></div>
            <div class="d-grid gap-2 col-6 mx-auto">
                <button type="submit" name="submit" id="submit" class="btn custom-btn" title="Add Administrator">Submit</button>
                <input type="hidden" id="operation" name="operation" value="I">
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
            
        </script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
