/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Authenticator;
import model.DbHelper;
import model.User;

/**
 *
 * @author lorenzo
 */
public class LoginServlet extends HttpServlet {

     private DbHelper helper;

    @Override
    public void init() throws ServletException
    {
        this.helper = (DbHelper) super.getServletContext().getAttribute("dbmanager");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        RequestDispatcher rd = null;

        
        Authenticator authenticator = new Authenticator();
        User result = authenticator.authenticate(username, password, helper);
        if (result != null) {
            rd = request.getRequestDispatcher("/Home.jsp");            
            request.setAttribute("user", result);   
        } else {
            request.setAttribute("failedLogin", true);
            rd = request.getRequestDispatcher("/LoginPage.jsp");
        }
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    

}
