<%-- 
    Document   : passwordRecover
    Created on : 21-gen-2014, 14.48.18
    Author     : mb
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="container">
            <form class="form-signin" role="form" method="post" action="PassResetController">
                <c:if test="${not empty param.error}">
                    <div class="alert alert-danger"><strong>Oh snap! </strong><c:out value="${param.error}" />.</div>
                </c:if>         
                <c:if test="${not empty param.success}">
                    <div class="alert alert-success"><strong>Success! </strong><c:out value="${param.success}" />.</div>
                </c:if>  
                <h2 class="form-signin-heading">Forgot your password?</h2>
                <p class="text-info center-block">Insert username</p>
                <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
                <p class="text-info center-block">An email to the related address will be sent with a new password</p>
                <button class="btn btn-lg btn-primary btn-block" type="submit" >Confirm</button>
            </form>
        </div>
    </body>
</html>
