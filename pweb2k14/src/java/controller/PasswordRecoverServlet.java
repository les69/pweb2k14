/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.sun.xml.rpc.processor.util.StringUtils;
import helpers.ServletHelperClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;
import model.User;

/**
 *
 * @author mb
 */
public class PasswordRecoverServlet extends HttpServlet {

    private DbHelper helper;

    @Override
    public void init() throws ServletException {
        this.helper = (DbHelper) super.getServletContext().getAttribute("dbmanager");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        forward(request, response, "/PasswordRecover.jsp");
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
        User user = helper.getUser(username);
        if (user == null) {
            forward(request, response, "/PasswordRecover.jsp?error=No user found");
        } else {
            String email = user.getEmail();
            String newpassword = generateRandomPassword();
            helper.setUserPassword(username,newpassword);
            ServletHelperClass.sendMail(email, "Password reset on pweb", 
                    "The password for the "+username+" account on pweb has been reset."
            + "The new password is: "+newpassword
            );
            forward(request, response, "/PasswordRecover.jsp?success=An email with a new password sent to " + email);
        }
        // processRequest(request, response);
    }

    //from StackOverflow.... is it right to put auxiliary functions here?
    private static final Random RANDOM = new SecureRandom();
    public static final int PASSWORD_LENGTH = 8;

    public static String generateRandomPassword() {
        String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";
        String pw = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (RANDOM.nextDouble() * letters.length());
            pw += letters.substring(index, index + 1);
        }
        return pw;
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

    private void forward(HttpServletRequest request, HttpServletResponse response, String page)
            throws ServletException, IOException {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(page);
        rd.forward(request, response);
    }
}
