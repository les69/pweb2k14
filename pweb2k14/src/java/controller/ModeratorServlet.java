/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import helpers.ServletHelperClass;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;
import model.Group;
import model.GroupToShow;
import model.Post;
import model.User;

/**
 *
 * @author Lorenzo
 */
public class ModeratorServlet extends HttpServlet
{
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User usr)
            throws ServletException, IOException
    {
        //re-makes get to prevent users from changing ismoderator value in the session
        if(!helper.getUser(usr.getUsername()).isIsmoderator())
            response.sendRedirect("../NotSupported.jsp");
                   
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
            throws ServletException, IOException
    {
        User usr = ServletHelperClass.getUserFromSession(request);
        processRequest(request, response, usr);
        String group = request.getParameter("g");
        if(group != null)
        {
            Post closure = new Post();
            closure.setIdGroup(Integer.parseInt(group));
            closure.setIdUser(usr.getId());
            closure.setMessage("Group activity ended by moderator " + usr.getUsername());
            helper.addPost(closure);
            helper.changeGroupActivity(Integer.parseInt(group), false);
        }
        
        List<GroupToShow> gruppi = helper.getGroupsForAdmin();
        request.getSession().setAttribute("allGroups", gruppi);
        response.sendRedirect("User/moderate.jsp");
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
            throws ServletException, IOException
    {
        User usr = ServletHelperClass.getUserFromSession(request);
        processRequest(request, response, usr);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
