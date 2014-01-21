/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;
import model.User;

/**
 *
 * @author les
 */
public class LoginServlet extends HttpServlet {
    private DbHelper helper;

    @Override
    public void init() throws ServletException {
        this.helper = (DbHelper) super.getServletContext().getAttribute("dbmanager");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        try {
            if (username == null || password == null) {
                response.sendRedirect("login.jsp?error=Missing credenditals");
            } else {
                User user = this.helper.authenticate(username, password);

                if (user == null) {
                    
                    response.sendRedirect("login.jsp?error=Wrong Username/Password combination");

                } else {
                               
                    request.getSession().setAttribute("last-login", user.getLastLogin());
                    request.getSession().setAttribute("username", username);
                    setLastLogin(user, helper);
                    response.sendRedirect("User/home.jsp"); 
                }

            }
            

        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error while printing login page", ex);
        }
     
    }
    private void setLastLogin(User usr, DbHelper helper) {
        
        Date date = new Date();
        helper.setUserLastLogin(usr, new java.sql.Timestamp(date.getTime()));
        
        
      }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
