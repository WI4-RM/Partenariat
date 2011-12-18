<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pays</title>
    </head>
    <body>
        <h1>Hello world !</h1>
        <%
            String poutou = (String)request.getAttribute("poutou");
            String poutou2 = (String)session.getAttribute("poutou");
        %>
        <p><%= poutou%></p>
        <%= poutou2%>
    </body>
</html>
