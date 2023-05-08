package com.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class logoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //while logouttiing
        HttpSession session= req.getSession();
        session.removeAttribute("uname");
        session.invalidate();
        res.sendRedirect("login.jsp");
        //all session attributes will be lost, cookies will not be able to retrieve any data from session id
    }
}
