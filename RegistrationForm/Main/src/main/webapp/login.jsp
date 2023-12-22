<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored = "false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin: 50px;
        }
        form {
            display: inline-block;
            text-align: left;
        }
        label {
            display: block;
            margin-bottom: 8px;
            color: #333;
        }
        input {
            padding: 8px;
            margin-bottom: 16px;
            width: 200px;
        }
        button {
            padding: 10px 20px;
            font-size: 16px;
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
        span {
            color: red;
        }
    </style>
</head>
<body>

    <a href="/back-action"><button id="backBtn">Back</button></a>
    <h2>Login</h2>
    <form method="post" action="login">
        <label for="email">Email Address:</label>
        <input type="email" id="email" name="email" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <br>
        <span>${error}</span>
        <span>${umail}</span>
        <br>
        <button type="submit">Login</button>
    </form>
</body>
</html>