package com.registrationform;

import com.registrationform.util.SendMail;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/resend-email")
public class ResendServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        String mail = request.getSession().getAttribute("umail").toString();
        SendMail.send(mail);
        try {
            response.addCookie(new Cookie("msgResent","Verification email resent! Please check your email."));
            response.sendRedirect("/user-info");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
