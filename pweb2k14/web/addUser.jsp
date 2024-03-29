<%-- 
    Document   : addUser
    Created on : Jan 21, 2014, 11:29:56 AM
    Author     : lorenzo
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
        <title>Create your account</title>
    </head>
    <body>
        <div class="container">
            <form class="form-signin" role="form" method="post" enctype="multipart/form-data" action="CyberController?oper=addUser">
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger"><strong>Oh snap! </strong><c:out value="${param.error}" />.</div>
                </c:if>  
                <h2 class="form-signin-heading">Please fill in the following form</h2>
                <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
                <input type="password" name="password" class="form-control" placeholder="Password" required="">
                <input type="email" name="email" class="form-control" placeholder="Email" required="">
                <br /> 
                <p class="text-left">Please pick an image that will represent you</p>        
                <input type="file" name="avatar" class="form-control" required="">
                <br />
                <button class="btn btn-lg btn-primary btn-block" type="submit" >Create account</button>
            </form>        
        </div>
    </body>
</html>
