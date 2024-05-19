<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ORION</title>
    <meta name="description" content="EMPLOYEE REWARD MANAGEMENT SYSTEM">
    <meta name="keywords" content="EMPLOYEE REWARD MANAGEMENT SYSTEM">
    <meta name="author" content="Orion">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
    <link rel="shortcut icon" type="image/ico" href="src/img/ORION-LOGO.png">
    <link rel="stylesheet" href="src/css/login_style.css">
    
</head>
<body>
    <form name="signin" id="signin" action="LoginValidationFormServlet" autocomplete="off" method="post" class="card">
        <div class="logo">
            <img src="src/img/ORION-LOGO.png" alt="ORION" width="auto" height="auto">
        </div>
        <div class="title">
            <p>WELCOME TO <span>ORION</span></p>
            <p>EMPLOYEE REWARD MANAGEMENT SYSTEM</p>
        </div>
        <div class="input-container">
            <div class="inputBox">
                <input type="text" class="form-control" name="adminid" id="adminid" onkeypress="isInputNumber(event)" maxlength="5" title="Make sure the administrator id entered is complete and correct" placeholder="ADMINISTRATOR ID" required>
            </div>
            <div class="inputBox">
                <input type="password" class="form-control" name="adminpassword" id="adminpassword"  maxlength="20" title="Make sure the administrator password entered is complete and correct" pattern=".*\S.*" placeholder="PASSWORD" required>
            </div>
        </div>
        <div class="loginBtn">
            <button type="Submit" name="submit" id="submit" title="LOGIN" value="Submit"><b>LOGIN</b></button>
        </div>
    </form>
     <div class="pt-1">
        <span style="color:red;text-align:center">
            <div style="text-align:center;"><%= (request.getAttribute("msgs") == null) ? "" : request.getAttribute("msgs") %></div>
        </span>
    </div>
    <script type="text/javascript">
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
</body>
</html>
