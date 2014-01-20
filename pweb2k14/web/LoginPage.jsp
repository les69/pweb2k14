<%-- 
    Document   : LoginPage
    Created on : Jan 20, 2014, 3:32:50 PM
    Author     : lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bitte login!</h1>
        <form class="navbar-form pull-down" action="LoginServlet" method="post">
            Username<br/> 
            <input type="text" class="span2" name="username"/> <br/>
            Password<br/> 
            <input type="password"  class="span2"name="password" /> <br/>
            <input type="submit" class="btn" value="Login" />
        </form>
    </body>
</html>
