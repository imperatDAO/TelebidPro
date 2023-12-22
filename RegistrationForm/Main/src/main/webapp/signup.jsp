<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Form</title>
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

        h2 {
        top: -40px;
            text-align: center;
                            margin-bottom: 20px; /* Adjust as needed */
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            position: relative;
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

        .required {

            color: red;
        }

        .info {
            position: absolute;
            top: -40px;
            left: 0;
            width: 100%;
            background-color: #f2f2f2;
            padding: 8px;
            text-align: center;
            border-radius: 8px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        button:not(#backBtn) {
            width:100%;
            padding: 10px;
            box-sizing: border-box;
            background-color: #4caf50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        #backBtn {
                    position: absolute;
                    top: 10px;
                    left: 10px;
                    padding: 8px;
                    font-size: 14px;
                    cursor: pointer;
                }

        button:not(#backBtn):hover {
            background-color: #45a049;
        }
    </style>
</head>
<body >

    <a href="/back-action"><button id="backBtn">Back</button></a>

    <form id="signupForm" action="/signup" method="post">
        <div class="info">Fields marked with <span class="required">*</span> are required.</div>
        <label for="email">Email Address<span class="required">*</span>:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password<span class="required">*</span>:</label>
        <input type="password" id="password" name="password" pattern="^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+{}\[\]:;<>,.?~\\-]).{8,64}$" required
            title="Password must be 8-64 characters long, contain at least 1 capital letter, 1 number, and 1 special symbol">

        <label for="repeatPassword">Repeat Password<span class="required">*</span>:</label>
        <input type="password" id="repeatPassword" name="repeatPassword" required>

        <label for="firstName">First Name<span class="required">*</span>:</label>
        <input type="text" id="firstName" name="firstName" pattern="^[A-Z][a-z]*$" required
            title="First name must start with a capital letter and contain only lowercase letters">

        <label for="middleName">Middle Name:</label>
        <input type="text" id="middleName" name="middleName" pattern="^[A-Z][a-z]*$">

        <label for="lastName">Last Name<span class="required">*</span>:</label>
        <input type="text" id="lastName" name="lastName" pattern="^[A-Z][a-z]*$" required
            title="Last name must start with a capital letter and contain only lowercase letters">

        <label for="username">Username<span class="required">*</span>:</label>
        <input type="text" id="username" name="username" pattern="^[a-zA-Z0-9]{8,64}$" required
            title="Username must be between 8 and 64 characters">

        <label for="captcha">What is ${num1} + ${num2} equal to ? <span class="required">*</span></label>
        <input type="text" id="captcha" name="captcha" required>

        <button type="submit">Sign Up</button>
    </form>
<script>
    function validatePassword() {
        var password = document.getElementById('password').value;
        var repeatPassword = document.getElementById('repeatPassword').value;

        if (password !== repeatPassword) {
            alert('Passwords do not match. Please enter matching passwords.');
            return false;
        }

        return true;
    }


    document.getElementById('signupForm').onsubmit = validatePassword;
    const cookiesValue = document.cookie.split(";").find((row) => row.startsWith("error="))?.split("=")[1];
    if(cookiesValue === undefined){
        console.log("");
    }else{
        alert(cookiesValue);
        document.cookie = "sqlError=;expires = Thu, 01 Jan 1970 00:00:00 GMT";
        console.log(document.cookie);
    }
</script>
</body>
</html>