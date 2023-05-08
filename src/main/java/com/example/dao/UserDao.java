package com.example.dao;

import com.example.model.UserModel;

import java.sql.*;

public class UserDao {
    //all tasks with db
    private static final String VERIFY_LOGIN_QUERY="select * from students where name=? and pass=?";
    private static final String REGISTER_QUERY="insert into students(name, pass, email) values(?, ?, ?)";
    private static final String EDITUSER_QUERY= "update students set name=? , email=? where id=?";
    private static final String DELETE_QUERY= "delete from students where id=?";
    private static final String SELECT_USER_BY_ID= "select id, name, email from students where id=?";
    private  String jdbcUrl="jdbc:mysql://localhost:3306/student_db?useSSL=false";
    private  String jdbcUsername="root";
    private String jdbcPwd= "fearless@2023";

    protected Connection getConnection  () {
        Connection con= null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPwd);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    public boolean verifyCredentials(String uname, String password) {
        try{
            Connection con= getConnection();
            PreparedStatement st= con.prepareStatement(VERIFY_LOGIN_QUERY);
            st.setString(1, uname);
            st.setString(2, password);
            ResultSet rs= st.executeQuery();
            if(rs.next()){ //if result set is not empty
                return true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        //also can use DataSource
        return false;
    }


     public int  registerUser(String uname, String mail, String pass) throws SQLException {
         Connection con= getConnection();
        PreparedStatement st= con.prepareStatement(REGISTER_QUERY);
        st.setString(1, uname);
        st.setString(2, pass);
        st.setString(3, mail);
         // incorrect database credentials, insufficient database permissions, database server issues, network issues--SQLException
        return st.executeUpdate();

        //also to check for SQLIntegrityConstraintViolationException
    }

    public boolean updateUser(UserModel user) throws SQLException {
        Connection con= getConnection();
        PreparedStatement st= con.prepareStatement(EDITUSER_QUERY);
        st.setString(1, user.getName());
        st.setString(2, user.getEmail());
        st.setInt(3, user.getId());
        return st.executeUpdate()>0;
    }

    public boolean deleteUser(int id) throws SQLException {
        Connection con= getConnection();
        PreparedStatement st= con.prepareStatement(DELETE_QUERY);
        st.setInt(1, id);
        return st.executeUpdate()>0;
    }

    public UserModel selectUser(int id) {
        UserModel user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                user = new UserModel(id, name, email);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }


    //
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
