<%-- 
    Document   : login
    Created on : Jan 19, 2014, 9:47:03 PM
    Author     : les
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link href="css/bootstrap-responsive.css" type="text/css" rel="stylesheet" />-->
        <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <% if (request.getParameter("error") != null)
        {
            out.println("<p><font color='red'>Combinazione nome utente/password errati</font></p>");
        }
            %>
        <form class="form-signin" role="form" method="post" action="CyberController?oper=doLogin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="Password" required="">

        <button class="btn btn-lg btn-primary btn-block" type="submit" >Sign in</button>
      </form>

    </div>
        
    </body>
</html>
