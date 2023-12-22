package com.registrationform.util;


import com.registrationform.db.DataBaseConnection;
import com.registrationform.userdao.User;

import javax.servlet.http.HttpServletRequest;


public class ValidateSignUp {
    public static Pair validate(HttpServletRequest req){
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        if(DataBaseConnection.checkExistMail(email)){
            return new Pair(-1,"Email already in use!");
        }
        if (DataBaseConnection.checkExistUsername(username)){
            return new Pair(-1,"Username already in use! Please use other.");
        }
        if(!checkCaptcha(req)){
            return new Pair(-1,"Wrong captcha");
        }
        User user = new User(email,req.getParameter("firstName"),req.getParameter("middleName"),req.getParameter("lastName"),username,false);
        if(DataBaseConnection.insertNewUser(user,req.getParameter("password"))){
            return new Pair(0,"Success");
        }
        return new Pair(-1,"Error, unsuccessful account creation! Please try again!");
    }
    public static boolean checkCaptcha(HttpServletRequest req){
        int num1 = (int)req.getSession().getAttribute("num1");
        int num2 = (int)req.getSession().getAttribute("num2");
        int userEntry = Integer.parseInt(req.getParameter("captcha"));
        if(num1 + num2 == userEntry){
            return true;
        }
        return false;
    }
}
