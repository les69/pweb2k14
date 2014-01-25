/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.DbHelper;
import model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import helpers.ServletHelperClass;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import model.Group;
import model.Post;
import java.io.File;
import java.rmi.ServerException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lorenzo
 */
public class NewPostServlet extends HttpServlet
{

    private DbHelper helper;

    @Override
    public void init() throws ServletException
    {
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

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
        processRequest(request, response);

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
        response.setContentType("text/html;charset=UTF-8");
        try
        {
            PrintWriter out = response.getWriter();

            User usr = helper.getUser(ServletHelperClass.getUsername(request, false));
            String relativeWebPath = "../uploads";
            Integer maxSize = Integer.parseInt((String) getServletConfig().getInitParameter("maxLengthSize"));

            checkPath(relativeWebPath);
            String absoluteFilePath = getServletContext().getRealPath(relativeWebPath) + File.separator;

            try
            {
                MultipartRequest multi = new MultipartRequest(request, absoluteFilePath, maxSize, "utf-8", new DefaultFileRenamePolicy());

                String group = (String) multi.getParameter("group");

                Group g = helper.getGroup(Integer.parseInt(group));
                if (g == null)
                {
                    throw new ServerException("Bad Error: group is null");
                }
                absoluteFilePath += g.getName();
                checkPath(absoluteFilePath);

                Enumeration file_list = multi.getFileNames();

                while (file_list.hasMoreElements())
                {
                    String name = (String) file_list.nextElement();
                    String filename = multi.getFilesystemName(name);
                    String originalname = multi.getOriginalFileName(name);
                    String type = multi.getContentType(name);
                    File f = multi.getFile(name);

                    if (f == null)
                    {
                        continue;
                    }
                    if (helper.isAGroupFile(g, originalname) != null)
                    {
                        out.println("<h1>This file already exists</h1><br/><h6>Your post was not submitted.</h6>");
                        out.println("<a href=\"PostServlet?group=" + g.getId() + "\">Come back to post list</a>");
                        f.delete();
                        return;
                    }
                    String hash = ServletHelperClass.encryptPassword(originalname + g.getName() + usr.getUsername());
                    File renameFile = new File(absoluteFilePath + "/" + hash);
                    if (!renameFile.exists())
                    {
                        f.renameTo(renameFile);
                    }
                    model.FileDB file = new model.FileDB();
                    file.setHashed_name(hash);
                    file.setType(type);
                    file.setOriginal_name(originalname);
                    file.setId_user(usr.getId());
                    file.setId_group(g.getId());
                    helper.addFile(file);
                }

                Enumeration params = multi.getParameterNames();

                String text = (String) multi.getParameter("text");

                String message = ServletHelperClass.parseText(g, text, helper);
                Post p = new Post();
                p.setIdGroup(g.getId());
                p.setIdUser(usr.getId());
                p.setMessage(message);
                helper.addPost(p);

                //response.sendRedirect("PostServlet?g="+g.getId());
                request.getRequestDispatcher("/Group/PostServlet?group=" + g.getId()).forward(request, response);

            }
            catch (IOException ioex)
            {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, 
                        "Error while saving file to disk", ioex);

            }

        }
        catch (IOException | NumberFormatException | ServletException ex)
        {
            Logger.getLogger(getClass().getName()).severe(ex.toString());

        }
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

    private void checkPath(String path)
    {
        File dir = new File(path);

        if (!dir.exists())
        {
            dir.mkdir();
        }
    }

}
