<%-- 
    Document   : login
    Created on : Jan 19, 2014, 9:47:03 PM
    Author     : les
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
        <title>Please login</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" role="form" method="post" action="CyberController?oper=doLogin">
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger"><strong>Oh snap! </strong><c:out value="${param.error}" />.</div>
                </c:if>         
                <c:if test="${not empty param.success}">
                    <div class="alert alert-success"><strong>Success! </strong><c:out value="${param.success}" />.</div>
                </c:if>       
                <h2 class="form-signin-heading">Please sign in</h2>
                <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
                <input type="password" name="password" class="form-control" placeholder="Password" required="">
                <p class="text-info center-block">Forgot your password? Click <a href='PassResetController'>here</a>!</p>
                <button class="btn btn-lg btn-primary btn-block" type="submit" >Sign in</button>
                <p class="text-info center-block">Don't have an account yet? Click <a href='addUser.jsp'>here</a> for a free account, 
                    or click <a href="CyberController?oper=getPublicGroups">here</a> to access a list of free-to-read threads!</p>
            </form>
        </div>
    </body>
</html>
