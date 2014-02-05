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

public class NewUserServlet extends HttpServlet {

    private DbHelper helper;

    @Override
    public void init() throws ServletException {
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
        
        String relativeWebPath = "/Avatars";
        String absoluteFilePath = getServletContext().getRealPath(relativeWebPath) + File.separator;
        checkPath(absoluteFilePath);
        
        String username = request.getParameter("username");
        
        User newUser = helper.getUser(username);
        
        if(newUser == null){
            String password = request.getParameter("password"); 
            String email = request.getParameter("email"); 
            Part filePart = request.getPart("avatar");              
            newUser = new User();
            String hash;
            if(filePart.getSubmittedFileName() != "")
                 hash = AvatarManager.SaveAvatar(filePart, username, absoluteFilePath, response);
            else
                 hash = "default.png";
                
            
            newUser.setUsername(username);
            newUser.setAvatar(hash);
            newUser.setEmail(email);
            newUser.setPassword(password);            
            
            helper.addUser(newUser);
            
            response.sendRedirect("index.jsp?success=Account created successfully! you can now login");
        }
        else
        {
            response.sendRedirect("addUser.jsp?error=An user with the same username already exists");
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
    
    
    
    private void checkPath(String path)
    {
        File dir = new File(path);

        if (!dir.exists())
        {
            dir.mkdir();
        }
    }

}
