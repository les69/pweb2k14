<%-- 
    Document   : index
    Created on : Jan 23, 2014, 10:26:52 PM
    Author     : les
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <title>Post Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}" />
        <c:if test="${empty user}">
            <%
                User u = new User();
                u.setAnonymous();
                pageContext.setAttribute("user", u);
            %>
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
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <c:out value="${user.username}" /><b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <c:if test="${user.username != 'Anonymous'}" >
                                        <li class="dropdown-header">Account</li>
                                        <li class="divider"></li>
                                        <li><a href="/pweb2k14/CyberController?oper=getAccount">User settings</a></li>
                                        <li><a href="/pweb2k14/CyberController?oper=getlogout">Log out</a></li>
                                    </c:if>
                                </ul>
                            </li>
                        </ul>
                    </div><!--/.nav-collapse -->
                </div>
                <div class="col-lg-12" style="background-color: #fff;">
                    <h2>Some Posts</h2>  
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <c:set var="group" value="${sessionScope.group}" />
                            <h3 class="panel-title"><c:out value="${group.name}" /></h3>
                        </div>
                        <div class="panel-body">
                            <c:if test="${!empty sessionScope.postList}">
                                <c:forEach items="${sessionScope.postList}" var="post">
                                    <div class="media">
                                        <a class="pull-left" href="#">
                                            <img class="media-object" data-src="holder.js/64x64" alt="64x64" style="width: 64px; height: 64px;" src="../uploads/Avatars/${post.avatar}">
                                        </a>
                                        <div class="media-body">
                                            <h4 class="media-heading"><c:out value="${post.username}" />     <p style="font-size: 14px;font-style: italic;"><c:out value="${post.datePost}" /></p> </h4>
                                            <c:out value="${post.message}" escapeXml="false" />
                                        </div>
                                    </div>
                                </c:forEach>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-2" style="margin-bottom: 10px;">
                        <c:if test="${group.active && user.username != 'Anonymous' && sessionScope.readonly == false}" >
                            <button class="btn  btn-primary " type="submit" onclick="location.href = '../CyberController?oper=createPost'" >New Post</button>
                        </c:if>
                    </div>
                    <div class="col-lg-2">
                        <c:if test="${group.active && user.ismoderator}" >
                            <button class="btn  btn-danger " type="submit" onclick="location.href = '../CyberController?oper=doModerate&g=<c:out value="${group.id}" />'" >Close</button>
                        </c:if>  
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
