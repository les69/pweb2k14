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
            forward(request,response,"/pweb2k14/NotFound.jsp");
        
        SetUpDBConnection();
        
        switch(operation)
        {
            case "getEditGroup": forward(request, response, "/EditGroupServlet"); break;
            case "getNewGroup": forward(request, response, "/NewGroupServlet"); break;
            case "doModerate": 
            case "getModerator": forward(request, response, "/ModeratorServlet"); break;
            case "getInvites": forward(request, response, "/AccRecInviteServlet"); break;
            case "getAccount": forward(request, response, "/EditUserServlet"); break;
            case "getlogin": forward(request,response,"/index.jsp"); break;
            case "getlogout": forward(request,response,"/Logout"); break;
            case "getHome": response.sendRedirect("/pweb2k14/User/home.jsp");  break;
            case "getGroups": forward(request, response, "/ListGroups"); break;
            case "getMyGroups":forward(request, response, "/ListMyGroup"); break;
            case "getPublicGroups":forward(request, response, "/ListPublicGroups"); break;
            case "getShowPost":forward(request, response, "/Group/PostServlet"); break;
            case "createPost": response.sendRedirect("/pweb2k14/Group/newPost.jsp?g=" + request.getParameter("g"));break;
            case "getDownload": forward(request, response, "/Group/Download"); break;
            case "getpost": forward(request,response,"/NotSupported.jsp"); break;
            case "error": response.sendRedirect("/pweb2k14/error.jsp");break;
            default: forward(request,response,"/NotFound.jsp"); break;
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
            forward(request,response,"/pweb2k14/index.jsp");
        
        SetUpDBConnection();
        
        switch(operation)
        {            
            case "doSendInvite": forward(request, response, "/SendInviteServlet"); break;
            case "doEditGroup": forward(request, response, "/EditGroupServlet"); break;
            case "doNewGroup" : forward(request, response, "/NewGroupServlet"); break;
            case "accRefInvite": forward(request, response, "/AccRecInviteServlet"); break;
            case "editAccount": forward(request,response,"/EditUserServlet"); break;
            case "doLogin": forward(request,response,"/Login"); break;
            case "addUser": forward(request, response, "/NewUserServlet"); break;
            case "getpost": forward(request,response,"/NotSupported.jsp"); break;
            case "newPost": forward(request, response, "/Group/NewPost");break;
            default: forward(request,response,"/pweb2k14/NotFound.jsp"); break;
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
    
    private void forward(HttpServletRequest request, HttpServletResponse response, String page) 
       throws ServletException, IOException
    {
        ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(page);
        rd.forward(request,response);
  }

}
