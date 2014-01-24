/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import helpers.ServletHelperClass;
import java.io.IOException;
import java.io.PrintWriter;
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
public class SendInviteServlet extends HttpServlet {
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
        Integer id_group = Integer.parseInt(request.getParameter("g"));
        String users = request.getParameter("usrNames");
        Group g = helper.getGroup(id_group);
        if(AddInvites(users.split(";"), g))
            response.sendRedirect("MyGroup/editGroupForm.jsp?succ=Invite(s) have been sent successfully");
        else
            response.sendRedirect("MyGroup/editGroupForm.jsp?err=one or more of the invites could not be sent");

    }
    
    private boolean AddInvites(String[] users, Group group)
    {
        boolean success = true;
        User tmpUs = null;
        for(String user : users )
        {
            tmpUs = helper.getUser(user.replaceAll(" ", ""));
            if(tmpUs != null)
                helper.addInvite(group, tmpUs);
            else
                success = false;
        }
        return success;
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
