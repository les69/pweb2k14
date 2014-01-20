/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DbHelper;

/**
 *
 * @author lorenzo
 */
public class ControllerServlet extends HttpServlet {

    protected void SetUpDBConnection()
    {
        String dburl = getServletContext().getInitParameter("dburl");
        DbHelper helper = new DbHelper(dburl);
        getServletContext().setAttribute("dbmanager", helper);
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
        
        String operation = request.getParameter("oper");
        if(operation == null)
            forward(request,response,"/login.jsp");
        
        SetUpDBConnection();
        
        switch(operation)
        {
            case "getlogin": forward(request,response,"/login.jsp"); break;
            case "getlogout": forward(request,response,"/Logout"); break;
            case "getpost": forward(request,response,"/NotSupported.jsp"); break;
            default: forward(request,response,"/NotSupported.jsp"); break;
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
       String operation = request.getParameter("oper");
        if(operation == null)
            forward(request,response,"/login.jsp");
        
        SetUpDBConnection();
        
        switch(operation)
        {
            case "doLogin": forward(request,response,"/Login"); break;
            case "getpost": forward(request,response,"/NotSupported.jsp"); break;
            default: forward(request,response,"/NotSupported.jsp"); break;
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
    }// </editor-fold>
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
       throws ServletException, IOException
    {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(page);
        rd.forward(request,response);
  }

}
