/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import helpers.ServletHelperClass;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
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
        
        String relativeWebPath = "/WEB-INF/Avatars";
        String absoluteFilePath = getServletContext().getRealPath(relativeWebPath) + File.separator;
        checkPath(absoluteFilePath);
        
        String username = request.getParameter("username");
        
        User newUser = helper.getUser(username);
        
        if(newUser == null){
            String password = request.getParameter("password"); 
            String email = request.getParameter("email"); 
            Part filePart = request.getPart("avatar");              
            InputStream filecontent = filePart.getInputStream();
            
            String fileName = getFileName(filePart);
            String fileExtension = fileName.substring(fileName.lastIndexOf('.'));
            
            String hash = ServletHelperClass.encryptPassword(username+fileName)+fileExtension;
            
            File avatarFile = new File(absoluteFilePath + "/" + hash);
            OutputStream fos = null;
            
            //Uncomment line below if we want to preserve old avatars
            
            //if (!avatarFile.exists())
                try {
                    fos = new FileOutputStream(avatarFile);
                    int read = 0;
                    final byte[] bytes = new byte[1024];
                    while ((read = filecontent.read(bytes)) != -1) {
                        fos.write(bytes, 0, read);
                    }

                } catch (IOException e) {
                    String s = e.getMessage();
                    response.sendRedirect("/NotSupported.jsp");
                } finally {
                    if (fos != null) {
                        fos.close();
                    }
                    filecontent.close();
                }
                
            newUser = new User();
            newUser.setUsername(username);
            newUser.setAvatar(hash);
            newUser.setEmail(email);
            newUser.setPassword(password);
            
            
            helper.addUser(newUser);
            
            response.sendRedirect("login.jsp?success=Account created successfully! you can now login");
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
    
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
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
