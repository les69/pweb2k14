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
        <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet" />
        <link href="bootstrap/css/pages.css" type="text/css" rel="stylesheet" />
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
           
        <form class="form-signin" role="form" method="post" action="CyberController?oper=doLogin">
         <%  String error=request.getParameter("error");
             if (error != null)
        {
            out.println("<div class=\"alert alert-danger\"><strong>Oh snap! </strong>"+error+".</div>");
        }
            %>
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" name="username" class="form-control" placeholder="Username" required="" autofocus="">
        <input type="password" name="password" class="form-control" placeholder="Password" required="">

        <button class="btn btn-lg btn-primary btn-block" type="submit" >Sign in</button>
      </form>

    </div>
        
    </body>
</html>
