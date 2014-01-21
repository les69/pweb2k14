<%-- 
    Document   : index
    Created on : Jan 17, 2014, 10:26:52 PM
    Author     : les
--%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>

<%@page import="model.User" %>
<%@page import="model.Group" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../bootstrap/css/bootstrap-theme.css" type="text/css" rel="stylesheet" />
        <link href="../bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="../bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        
        <title>Home Page</title>
    </head>
    <body>
        <% 
            User user = (User) request.getSession().getAttribute("username");
            String dateLogin = (String) request.getSession().getAttribute("last-login");
            HashMap updatedGroups = (HashMap) request.getSession().getAttribute("updatedGroups");
        
        %>
        <div class="container">
        <div class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Web Programming v2.0</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="#">My Groups</a></li>
            <li><a href="#">Groups</a></li>
            <li><a href="#">Invites</a></li>
            
           
          </ul>
          <ul class="nav navbar-nav navbar-right">
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= user.getUsername() %> <b class="caret"></b></a>
              <ul class="dropdown-menu">
              
                <li class="dropdown-header">Account</li>
                <li class="divider"></li>
                <li><a href="/pweb2k14/CyberController?oper=getchangepassword">Change Password</a></li>
                <li><a href="/pweb2k14/CyberController?oper=getchangeavatar">Change Avatar</a></li>
                <li><a href="/pweb2k14/CyberController?oper=getlogout">Log out</a></li>

              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
        
        <div class="row">
            
            <div class="col-lg-12" style="background-color: #fff;">
                <h2>Welcome back!</h2>  Last login at <%= dateLogin %>
            <h4> What's hot?</h4>
            <%
                if(updatedGroups.size() > 0)
                {
                    out.println("<ul class=\"list-group\">");
                    Iterator it = updatedGroups.entrySet().iterator();
                    while(it.hasNext())
                    {
                        Map.Entry entry = (Map.Entry) it.next();
                        out.println("<li class=\"list-group-item\">"+((Group)entry.getKey()).getName()+"<span class=\"badge\">"+entry.getValue()+"</span></li>");
                    }
                }
            %>
        </div>
        </div>
        </div>
        </div>
    </body>
</html>
