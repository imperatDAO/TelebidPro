<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Information Form</title>
    <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                position: relative;
            }

            #logoutButton {
                position: absolute;
                top: 10px;
                left: 10px;
                background-color: #ff0000;
                color: #fff;
                border: none;
                border-radius: 4px;
                padding: 8px 12px; /* Adjust padding as needed */
                cursor: pointer;
            }

            #logoutButton:hover {
                background-color: #cc0000;
            }

            h2 {
                text-align: center;
                margin-bottom: 20px; /* Adjust as needed */
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            label {
                display: block;
                margin-bottom: 8px;
            }

            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 16px;
                box-sizing: border-box;
            }

            #emailVerified {
                pointer-events: none;
            }

            button:not(#logoutButton) {
                width: 100%;
                padding: 10px;
                margin-bottom: 16px;
                box-sizing: border-box;
                background-color: #4caf50;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            .checkbox-container {
                        display: flex;
                        align-items: center;
                        margin-bottom: 16px;
                    }

            button:not(#logoutButton):hover {
                background-color: #45a049;
            }
        </style>
</head>
<body>
    <button id="logoutButton" onclick="logout()">Logout</button>

    <h2>User Information Form</h2>

    <form id="userForm" action="/user-info" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required value=${umail}>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" pattern="^[A-Z][a-z]*$" required value=${ufname}>

        <label for="middleName">Middle Name:</label>
        <input type="text" id="middleName" pattern="^[A-Z][a-z]*$" name="middleName"value=${umiddle}>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" pattern="^[A-Z][a-z]*$" required value=${ulast}>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" pattern="^[a-zA-Z0-9]{8,64}$" required value=${uname}>

    <div class="checkbox-container">
        <label for="emailVerified">Email is Verified:</label>
        <input type="checkbox" id="emailVerified" name="emailVerified" ${check}>
    </div>
        <br><br>
        <input type="submit" value="Submit">
        <button type="button" onclick="resendEmail()">Resend Email</button>
    </form>

    <script>
            function logout() {
                window.location.href = '/logout';
            }
            function resendEmail() {
                window.location.href = '/resend-email';
            }

            function toggleResendButton() {
                    var checkbox = document.getElementById('emailVerified');
                    var resendButton = document.getElementById('resendButton');

                    if (checkbox.checked) {
                        resendButton.style.display = 'none';
                    } else {
                        resendButton.style.display = 'inline-block';
                    }
                }
            const cookiesValue = document.cookie.split(";").find((row) => row.startsWith("msgResent="))?.split("=")[1];
                    if(cookiesValue === undefined){
                        console.log("");
                    }else{
                        alert(cookiesValue);
                        document.cookie = "msgResent=;expires = Thu, 01 Jan 1970 00:00:00 GMT";
                    }

            toggleResendButton();
        </script>

    </body>
</html>