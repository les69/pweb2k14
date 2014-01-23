<%-- 
    Document   : index
    Created on : Jan 17, 2014, 10:26:52 PM
    Author     : les
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <link href="../datatables/css/jquery.dataTables.css" type="text/css" rel="stylesheet" />

        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        <script src="../datatables/js/jquery.dataTables.min.js"></script>

        <title>Moderate</title>
    </head>
    <body>
        <jsp:useBean id="username" class="model.User" scope="session" />
        
        <%
            if (username == null) {
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

                        <c:if test="${username.ismoderator}">
                            <li><a href="/pweb2k14/CyberController?oper=getModerator">Moderate</a></li>
                            </c:if>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <c:out value="${username.username}" /> <b class="caret"></b></a>
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
                    <h2>Welcome back!</h2>  Last login at <%= username.getFormatDate()%>
                    <div class="container">
                        <table id="tableGroups" class="dataTable table-striped">
                            <thead><tr><th >Group name</th><th >Participants</th><th >Post number</th><th>Is a public group</th><th>Delete</th></tr></thead>
                            <tbody>
                                <c:forEach var="gruppo" items="${allGroups}">
                                    
                                        <tr>
                                            <td><a href=""><c:out value="${gruppo.name}" /></a></td>
                                            
                                            <td><c:out value="${gruppo.participantCount}" /></td>
                                            <td><c:out value="${gruppo.postCount}" /></td>
                                            <c:if test="${gruppo.public == true}">
                                                <td>Yes</td>
                                            </c:if>
                                            <c:if test="${gruppo.public == false }">
                                                <td>No</td>
                                            </c:if>
                                            <c:if test="${gruppo.active == true}">
                                                <td><a href="../CyberController?oper=doModerate&g=<c:out value="${gruppo.id}" />"><img width="100" src="../image/cybermen.jpg"/></td>
                                            </c:if>
                                            <c:if test="${gruppo.active == false}">
                                                    <td>THIS GROUP HAS BEEN DELETED.<br>DELETE. DELETE. DELEETEE!</td>
                                            </c:if>
                                        </tr>
                                    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $('#tableGroups').dataTable({"bPaginate": false});

    });
</script>
</html>
