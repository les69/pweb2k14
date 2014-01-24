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
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="../bootstrap/js/bootstrap.min.js"></script>
        
        <title>Home Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.username}" />

        <c:choose>
            <c:when test="${empty user}">
                <%
                    User usr = new User();
                    usr.setAnonymous();
                    pageContext.setAttribute("user", usr);
                    response.sendRedirect("/pweb2k14/login.jsp"); 

                %>
             </c:when>
             <c:otherwise>
                <!-- Convert to c:if if not used -->
             </c:otherwise>
        </c:choose>
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
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <c:out value="${user.username}" /> <b class="caret"></b></a>
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
                <div class="page-header">
                <h2>Welcome back!</h2>  Last login at <c:out value="${user.formatDate}" />
                </div>
            <h4> What's hot?</h4>
            <%
                HashMap updatedGroups = (HashMap) request.getSession().getAttribute("updatedGroups");
                if(updatedGroups != null && updatedGroups.size() > 0)
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
            <%--  <c:forEach items="groups" var="group">
                <c:out value="${group.key}"/><c:out value="${group.value}" />
</c:forEach> --%>
        </div>
        </div>
        </div>
        </div>
    </body>
</html>
