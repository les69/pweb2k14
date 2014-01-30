/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import helpers.AvatarManager;
import model.DbHelper;
import model.User;

/**
 *
 * @author lorenzo
 */
public class EditUserServlet extends HttpServlet {
    private DbHelper helper;

    @Override
    public void init() throws ServletException {
        this.helper = (DbHelper) super.getServletContext().getAttribute("dbmanager");
    }

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
        
        //TODO: add useless things like post count to response
        
        response.sendRedirect("User/userinfo.jsp");
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
        String relativeWebPath = "/Avatars";
        String absoluteFilePath = getServletContext().getRealPath(relativeWebPath) + File.separator;
        
        
        User newUser = (User) request.getSession().getAttribute("user");
        String password = request.getParameter("oldpass"); 

            if(helper.authenticate(newUser.getUsername(), password)!= null)
            {
                Part filePart = request.getPart("avatar");              
                if(filePart.getSize() >0)  {
                    
                    String hash = AvatarManager.SaveAvatar(filePart, newUser.getUsername(), absoluteFilePath, response);
                    newUser.setAvatar(hash);
                }
                
                password = request.getParameter("newpass");
                
                if(password != null && !password.equalsIgnoreCase(""))
                    newUser.setPassword(password);
            
                if(helper.editUser(newUser))
                    request.getSession().setAttribute("user", newUser);
                else
                    response.sendRedirect("User/userinfo.jsp?error=invalid data provided");
                response.sendRedirect("User/userinfo.jsp");
            }
            else
            {
                response.sendRedirect("User/userinfo.jsp?error=wrong password");
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
    }

}
