/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helpers.ServletHelperClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;
import model.Invite;
import model.InviteToShow;
import model.User;

/**
 *
 * @author lorenzo
 */
public class AccRecInviteServlet extends HttpServlet {

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
        
        User usr = ServletHelperClass.getUserFromSession(request);
        if(usr == null || helper ==null)
            response.sendRedirect("/index.jsp");
        else
        {
            List<Invite> inv = helper.getUserInvites(usr);
            List<InviteToShow> invites = new ArrayList<>();
            for (Invite i : inv) //the line below is ugly to the eye.
            {
                invites.add(InviteToShow.ITSfromInvite(i, helper.getGroup(i.getIdGroup()).getName()));
            }
            request.getSession().setAttribute("invites", invites);
            response.sendRedirect("/pweb2k14/User/invites.jsp");
        }
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
        User usr = ServletHelperClass.getUserFromSession(request);
        boolean operation = request.getParameter("action").equals("Accept");
        processInvites(request.getParameterMap(), usr, operation);
        if(usr == null || helper ==null)
            response.sendRedirect("/index.jsp");
        else
        {
            List<Invite> inv = helper.getUserInvites(usr);
            List<InviteToShow> invites = new ArrayList<>();
            for (Invite i : inv) //the line below is ugly to the eye.
            {
                invites.add(InviteToShow.ITSfromInvite(i, helper.getGroup(i.getIdGroup()).getName()));
            }
            request.getSession().setAttribute("invites", invites);
            response.sendRedirect("/pweb2k14/User/invites.jsp");
        }
    }

    private void processInvites(Map params, User usr, boolean accept) {
        Iterator i = params.keySet().iterator();
        try {
            while (i.hasNext()) {

                String key = (String) i.next();

                //REQUIRED BECAUSE OF OTHER ELEMENTS IN REQUEST
                if (tryParseInt(key)) {
                    if (accept) {
                        helper.acceptInvite(helper.getGroup(Integer.parseInt(key)), usr);
                    } else {
                        helper.removeInvite(helper.getGroup(Integer.parseInt(key)), usr);
                    }
                }
            }
        } catch (NumberFormatException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,
                    "Error while accepting invite", ex);
        }
    }

    // because C# FTW!
    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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
