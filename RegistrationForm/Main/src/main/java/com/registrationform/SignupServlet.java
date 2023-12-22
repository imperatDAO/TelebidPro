package com.registrationform;

import com.registrationform.util.GenerateCaptcha;
import com.registrationform.util.Pair;
import com.registrationform.util.SendMail;
import com.registrationform.util.ValidateSignUp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/signup"/*,"/signup.jsp"*/})
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int nums[] = GenerateCaptcha.generateNums();

        HttpSession session = req.getSession();
        int num1 = nums[0], num2 = nums[1];
        session.setAttribute("num1",num1);
        session.setAttribute("num2",num2);

        //resp.addCookie(new Cookie("sqlError","test22"));

        resp.sendRedirect("signup.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        Pair p = ValidateSignUp.validate(req);
        if(p.getStatus()==0){
            SendMail.send(req.getParameter("email"));
            HttpSession session = req.getSession();
            session.setAttribute("umail",req.getParameter("email"));
            try {
                resp.sendRedirect("user-info");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            resp.addCookie(new Cookie("error",p.getCode()));
            try {
                resp.sendRedirect("signup.jsp");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
