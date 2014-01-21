<%-- 
    Document   : userinfo
    Created on : Jan 21, 2014, 4:55:43 PM
    Author     : lorenzo
--%>

<%@page import="model.User"%>
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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User user = (User) request.getSession().getAttribute("username");
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><%= user.getUsername()%> <b class="caret"></b></a>
                            <ul class="dropdown-menu">

                                <li class="dropdown-header">Account</li>
                                <li class="divider"></li>
                                <li><a href="/pweb2k14/CyberController?oper=getAccount">User settings</a></li>
                                <li><a href="/pweb2k14/CyberController?oper=getlogout">Log out</a></li>

                            </ul>
                        </li>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>

            <div class="row">

                <div class="col-lg-12" style="background-color: #fff;">
                    <h2>Welcome to you settings!</h2>  
                    <%  String error = request.getParameter("error");
                    if (error != null)
                   {
                       out.println("<div class=\"alert alert-danger\"><strong>Oh snap! </strong>"+error+".</div>");
                   }
                    %>
                    <p>Here you can change your settings such as password and avatar</p>

                    <h3>Settings for user <% out.println(user.getUsername());%></h3>
                    <form  action="../CyberController?oper=editAccount" enctype="multipart/form-data" method="post" role="form" class="form-group">
                        <p class="text-left form-control-static">
                            Your avatar:<br>

                            <img width="240" src="<% out.println("/pweb2k14/Avatars/" + user.getAvatar()); %>" alt="Your avatar" />
                            <input class="form-control" type="file" name="avatar">
                            <br>
                            email: <% out.println(user.getEmail());%>
                            <br>
                            New password<br>
                            <input class="form-control" type="password" name="newpass"> 
                        </p>

                        <p class="text-danger form-control-static"> 
                            Please provide your old password in order to apply changes<br>
                            <input class="form-control" type="password" name="oldpass" required=""> 
                        </p>
                        <p>
                            <button type="submit" class="btn btn-primary" name="Submit" >Submit changes</button>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
