<%-- 
    Document   : userinfo
    Created on : Jan 21, 2014, 4:55:43 PM
    Author     : lorenzo
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>Info Page</title>
    </head>
    <body>
        <jsp:useBean id="user" class="model.User" scope="session" />
        <c:if test="${empty user}">
            <c:redirect url="/login.jsp" />
        </c:if>
        <div id="wrap">
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

                            <c:if test="${user.ismoderator}">
                                <li><a href="/pweb2k14/CyberController?oper=getModerator">Moderate</a></li>
                                </c:if>
                        </ul>
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><c:out value="${user.username}" /> <b class="caret"></b></a>
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


                <div class="col-lg-12" style="background-color: #fff;">
                    <h2>Welcome to you settings!</h2>  
                    <c:if test="${not empty param.error}">
                        <div class="alert alert-danger"><strong>Oh snap! </strong><c:out value="${param.error}" />.</div>
                    </c:if>  
                    <p>Here you can change your settings such as password and avatar</p>

                    <h3>Settings for user <c:out value="${user.username}" /></h3>
                    <form  action="../CyberController?oper=editAccount" enctype="multipart/form-data" method="post" role="form" class="form-group">
                        <p class="text-left form-control-static">
                            Your avatar:<br>

                            <img width="240" src="../uploads/Avatars/${user.avatar}" alt="Your avatar" />
                            <input class="form-control" type="file" name="avatar">
                            <br>
                            email: <c:out value="${user.email}" />
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
