/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helpers.FileManager;
import model.DbHelper;
import model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.ServletHelperClass;
import model.Group;
import model.Post;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;

/**
 *
 * @author Lorenzo
 */
public class NewPostServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
   
            User usr = ServletHelperClass.getUserFromSession(request);
            String relativeWebPath = "/uploads/";

            checkPath(relativeWebPath);
            String absoluteFilePath = getServletContext().getRealPath(relativeWebPath) + File.separator;

            try {
                String group = (String) request.getParameter("group");

                Group g = helper.getGroup(Integer.parseInt(group));
                if (g == null) {
                    response.sendRedirect("/NotAllowed.jsp");
                    return;
                }
                absoluteFilePath += g.getName();
                checkPath(absoluteFilePath);

                Part part = request.getPart("uploadFile");
                if (part.getSize() > 0) {
                    String fileName = FileManager.getFileName(part);

                    if (helper.isAGroupFile(g, fileName) != null) {
                        //TODO redirect to error page
                       // out.println("<h1>This file already exists</h1><br/><h6>Your post was not submitted.</h6>");
                       // out.println("<a href=\"PostServlet?group=" + g.getId() + "\">Come back to post list</a>");
                        response.sendRedirect("/pweb2k14/Group/listPosts.jsp");
                        return;
                    }
                    String hash = FileManager.SaveFile(part, g.getName(), usr.getUsername(), absoluteFilePath, response, helper);
                    model.FileDB file = new model.FileDB();
                    file.setHashed_name(hash);
                    file.setType(fileName.substring(fileName.lastIndexOf('.')));
                    file.setOriginal_name(fileName);
                    file.setId_user(usr.getId());
                    file.setId_group(g.getId());
                    helper.addFile(file);
                }
                String text = (String) request.getParameter("message");

                String message = ServletHelperClass.parseText(g, text, helper);
                Post p = new Post();
                p.setIdGroup(g.getId());
                p.setIdUser(usr.getId());
                p.setMessage(message);
                helper.addPost(p);

                //request.getRequestDispatcher("/CyberController?oper=getShowPost&g="+g.getId()).forward(request, response);
                request.getSession().setAttribute("postList", helper.getPostFromGroup(g.getId()));
                response.sendRedirect("/pweb2k14/Group/listPosts.jsp");

            } catch (IOException ioex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                        "Error while saving file to disk", ioex);

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

    private void checkPath(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

}
