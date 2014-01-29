/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helpers.ServletHelperClass;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;
import model.Group;
import model.User;

/**
 *
 * @author lorenzo
 */
public class ManageGroupServlet extends HttpServlet
{

    private DbHelper helper;

    @Override
    public void init() throws ServletException
    {
        this.helper = (DbHelper) super.getServletContext().getAttribute("dbmanager");
    }

    protected void permOrGTFO(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int idGroup = Integer.parseInt(request.getParameter("g"));
        Group grp = helper.getGroup(idGroup);
        User usr = ServletHelperClass.getUserFromSession(request);
        if (grp.getOwner() != usr.getId()) {
            response.sendRedirect("/pweb2k14/NotAllowed.jsp");
        }
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
            throws ServletException, IOException
    {
        permOrGTFO(request, response);
        int idGroup = Integer.parseInt(request.getParameter("g"));
        Group grp = helper.getGroup(idGroup);

        request.getSession().setAttribute("grp", grp);
        response.sendRedirect("MyGroup/editGroupForm.jsp");

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
        int idGroup = Integer.parseInt(request.getParameter("g"));
        permOrGTFO(request, response);
        String groupName = request.getParameter("grpName");
        boolean pgroup = (request.getParameterValues("pubSelector") != null);
        helper.updateGroup(idGroup, groupName, pgroup);
        request.getSession().removeAttribute("grp");
        request.getSession().removeAttribute("myGroup");
        response.sendRedirect("/pweb2k14/CyberController?oper=getMyGroups");

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
    }

}
