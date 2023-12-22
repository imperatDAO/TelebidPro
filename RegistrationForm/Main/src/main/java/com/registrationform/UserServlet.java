package com.registrationform;

import com.registrationform.db.DataBaseConnection;
import com.registrationform.userdao.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user-info")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email =session.getAttribute("umail").toString();
        User usr = DataBaseConnection.extract(email);
        session.setAttribute("ufname",usr.getFirstName());
        session.setAttribute("umiddle",usr.getMiddleName());
        session.setAttribute("ulast",usr.getLastName());
        session.setAttribute("uname",usr.getUserName());

        if (usr.getVerified()){
            session.setAttribute("check","checked");
        }
        req.getRequestDispatcher("main.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String oldMail = session.getAttribute("umail").toString();

        String newMail = req.getParameter("email");
        String newFname = req.getParameter("firstName");
        String newMiddle = req.getParameter("middleName");
        String newLast = req.getParameter("lastName");
        String newUsername = req.getParameter("username");

        if(DataBaseConnection.update(oldMail, newMail,newFname,newMiddle,newLast,newUsername) == 0){
            User usr = new User(newMail,newFname,newMiddle,newLast,newUsername,false);
            session.setAttribute("umail",usr.getEmail());
            session.setAttribute("ufname",usr.getFirstName());
            session.setAttribute("umiddle",usr.getMiddleName());
            session.setAttribute("ulast",usr.getLastName());
            session.setAttribute("uname",usr.getUserName());
        }
        resp.sendRedirect("main.jsp");
    }
}
