<%-- 
    Document   : error
    Created on : Jan 29, 2014, 11:22:20 AM
    Author     : les
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Ops! There was an error!</h1>
        <c:set var="error" value="${sessionScope.message}" />
        <c:if test="${not empty error}">
            <h4><c:out value="${error}" /></h4>
        </c:if>
        <br>
        <img src="image/error.jpg" />
    </body>
</html>
