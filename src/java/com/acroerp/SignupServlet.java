/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acroerp;

import static com.acroerp.LoginServlet.JDBC_DRIVER;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author abhi
 */
public class SignupServlet extends HttpServlet {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/user";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
    Connection dbConnection = null;

    @Override
    public void init() throws ServletException {
        super.init();
        connectDB();
    }

    void connectDB() {
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Signup.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("password_confirmation");

        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        if (!password.equals(confirmPassword)) {
            session.setAttribute("error", "Passwords should match!");
            dispatcher = request.getRequestDispatcher("Signup.jsp");
            dispatcher.forward(request, response);
            return;
        }

        //Statement stmt;
        try {
            if (dbConnection == null) {
                connectDB();
            }
            //stmt = dbConnection.createStatement();

            //String sql;
            //sql = "INSERT INTO user ('email','password','name','phone') VALUES ('"
            //+ email+"','"+password+"','"+name+"','"+phone+"')";
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO user (email,password,name,phone) VALUES (?, ?, ?, ?)");
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, phone);

            int row = stmt.executeUpdate();
            stmt.close();
            if (row > 0) {
                System.out.println("Signup Successful");
                response.sendRedirect("profile");
            } else {
                session.setAttribute("error", "SQL Error!");
                dispatcher = request.getRequestDispatcher("Signup.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            dispatcher = request.getRequestDispatcher("Signup.jsp");
        }
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
