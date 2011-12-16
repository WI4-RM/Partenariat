<%-- 
    Document   : redirection
    Created on : 11 déc. 2011, 22:38:01
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%
            String url = (String)request.getAttribute("url");
        %>
        <meta http-equiv='refresh' content='0;url="<%= url%>"'/>
    </head>
    <body>
        <p> If the redirection doesn't work, please click <a href='" url "'>here</a>.</p>
    </body>
</html>
