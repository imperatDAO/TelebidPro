package com.registrationform;

import com.registrationform.db.DataBaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = {"/login","/back-action"})

public class LoginServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");

        String urlPattern = request.getServletPath();


        HttpSession session = request.getSession();
        if(urlPattern.equals("/login")){
            if(session.getAttribute("umail") != null) {
                response.sendRedirect("user-info");
            }else {
                response.sendRedirect("login.jsp");
            }
        }else {
            session.invalidate();
            response.sendRedirect("index.jsp");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        String emailAuth = DataBaseConnection.login(email,password);

        if(!Objects.equals(emailAuth, "-1")){
            HttpSession session = request.getSession();
            session.setAttribute("umail",emailAuth);
            Cookie cookie = new Cookie(session.getId(),email);
            cookie.setComment("logged-in");
            response.addCookie(cookie);

            response.sendRedirect("user-info");
            //request.getRequestDispatcher("/user-info").forward(request,response);
        }else {
            HttpSession session =request.getSession();
            //session.setAttribute("error", "Incorrect email/password! Please try again.");
            //request.setAttribute("error", "Incorrect email/password! Please try again.");
            session.setAttribute("error", "Incorrect email/password! Please try again.");

            //request.getRequestDispatcher("/login.jsp").forward(request, response);
            response.sendRedirect("login");


        }
    }
}
