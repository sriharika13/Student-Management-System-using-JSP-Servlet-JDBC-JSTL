package com.example;

import com.example.dao.UserDao;
import com.example.model.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/")
public class ListServlet extends HttpServlet {
    private UserDao userDAO;

    public void init() {
        userDAO = new UserDao();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String action= req.getServletPath();
        try {
            switch(action){
                case "/edit":
                    showEditForm(req, res);
                    break;
                case "/delete":
                    deleteUserData(req, res);
                    break;
                case "/update":
                    updateUser(req, res);
                    break;
            }
        }catch (ServletException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        int  id= Integer.parseInt(req.getParameter("id")); //getParameter gives String by default
        UserModel existingUser= userDAO.selectUser(id);
        RequestDispatcher dispatcher= req.getRequestDispatcher("edit.jsp");
        req.setAttribute("user" , existingUser);
        dispatcher.forward(req, res);
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse res)
            throws SQLException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        UserModel newUserData = new UserModel(id, name, email);
        userDAO.updateUser(newUserData);
        res.sendRedirect("welcome.jsp");
    }

    private void deleteUserData(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
        int  id= Integer.parseInt(req.getParameter("id"));
        userDAO.deleteUser(id);
        res.sendRedirect("welcome.jsp");
    }

}
