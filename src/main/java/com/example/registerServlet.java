package com.example;
import com.example.dao.UserDao;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name= req.getParameter("username");
        String email= req.getParameter("email");
        String pwd= req.getParameter("pass");

        PrintWriter out= res.getWriter();

        UserDao register= new UserDao();
        try{
            if(register.registerUser(name, email, pwd)>0 ){
                System.out.println("user added");
                HttpSession session= req.getSession();
                session.setAttribute("uname", name);
                res.sendRedirect("welcome.jsp");
            }else{
                System.out.println("user failed!");
                res.sendRedirect("register.jsp");
                //TODO: create error.jsp page for duplicate user register, invalid credentials login for flash message?
            }

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Exception caught: "+ e.getClass().getName());
            res.sendRedirect("register.jsp"); //when Duplicate entry for gmail is created
            out.println("Pl register with unique email"); //not working??????????
        }

    }
}
