package com.example;
import com.example.dao.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out= res.getWriter();
        out.println("hello from login.java");

        String name= req.getParameter("username");
        String pwd= req.getParameter("pwd");

        UserDao dao= null;
        dao = new UserDao();

        //verify credentials using database
        try {
        if(dao.verifyCredentials(name,pwd)){
            //flash msg - verified!
            //first set session parameters:
            HttpSession session= req.getSession();
            session.setAttribute("uname", name);
            //redirect to welcome page where students info is shown:
            res.sendRedirect("welcome.jsp");
        }else {
            //TODO: flash message when login credentials are wrong!
            //otherwise stay on login page and flash error msg in screen:
            res.sendRedirect("login.jsp");
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
