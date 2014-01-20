<%-- 
    Document   : Home
    Created on : Jan 20, 2014, 5:37:42 PM
    Author     : lorenzo
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged homepage</title>
    </head>
    <body>
        <h1><% out.println("Welcome to your home, " + ((User)request.getAttribute("user")).getUsername() + "!"); %></h1>
    </body>
</html>
