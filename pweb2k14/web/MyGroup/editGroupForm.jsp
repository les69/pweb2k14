<%-- 
    Document   : index
    Created on : Jan 22, 2014, 10:26:52 PM
    Author     : les
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <title>Group form</title>
    </head>
    <body>
        <jsp:useBean id="user" class="model.User" scope="session" />
        <jsp:useBean id="grp" class="model.Group" scope="session" />
        <%
            user = (User) request.getSession().getAttribute("username");
            if (user == null) {
                response.sendRedirect("/pweb2k14/login.jsp");
            }
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
                    <a class="navbar-brand" href="/pweb2k14/CyberController?oper=getHome">Web Programming v2.0</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="/pweb2k14/CyberController?oper=getMyGroups">My Groups</a></li>
                        <li><a href="/pweb2k14/CyberController?oper=getGroups">Groups</a></li>
                        <li><a href="/pweb2k14/CyberController?oper=getInvites">Invites</a></li>


                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <%= user.getUsername()%> <b class="caret"></b></a>
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
                    <h2>Please fill in the following form</h2>  
                    <div>
                        <form class="form-group" role="form" method="post" action="../CyberController?oper=doShowMyGroup">
                            <h2 >Edit group</h2>
                            <input type="text" name="grpName" class="form-control" value="" placeholder="Group name" required="" autofocus="">
                            <p class="form-control"><input type="checkbox" checked="" name="pubSelector" > Make this group public</p>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Confirm changes</button>
                        </form>
                        <form class="form-group" role="form" method="post" action="../CyberController?oper=doSendInvite">
                            <h2 ></h2>
                            <input type="text" name="grpName" class="form-control" placeholder="Group name" required="" autofocus="">
                            <p class="form-control"><input type="checkbox" name="pubSelector" > Make this group public</p>
                            <button class="btn btn-lg btn-primary btn-block" type="submit">Confirm changes</button>
                        </form>
                    </div>

                </div>

            </div>

        </div>

    </body>
</html>
