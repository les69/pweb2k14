<%-- 
    Document   : index
    Created on : Jan 17, 2014, 10:26:52 PM
    Author     : les
--%>

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
            String username = (String) request.getSession().getAttribute("username");
            String dateLogin = (String) request.getSession().getAttribute("last-login");
        
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
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
           
          </ul>
          <ul class="nav navbar-nav navbar-right">
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= username %> <b class="caret"></b></a>
              <ul class="dropdown-menu">
              
                <li class="dropdown-header">Account</li>
                <li class="divider"></li>
                <li><a href="#">Change Password</a></li>
                <li><a href="#"></a></li>
                <li><a href="/pweb2k14/Logout">Log out</a></li>

              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
        
        <h1>Welcome back!</h1>  Last login at <%= dateLogin %>
        </div>
    </body>
</html>
