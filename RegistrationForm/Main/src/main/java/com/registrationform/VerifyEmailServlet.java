package com.registrationform;

import com.registrationform.db.DataBaseConnection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/VerifyEmail")
public class VerifyEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        String mail = req.getParameter("key1");
        String randomToken = req.getParameter("tokenTry");

        if(randomToken.equals("random123")){
            if(DataBaseConnection.checkExistMail(mail)){
                DataBaseConnection.activateEmail(mail);
                try {
                    resp.addCookie(new Cookie("msgVerify","Email verified successfully!"));
                    resp.sendRedirect("login.jsp");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    resp.addCookie(new Cookie("msgVerify","Email does not exist!"));
                    resp.sendRedirect("login.jsp");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}
