<%-- 
    Document   : connectionPanel
    Created on : 12 janv. 2012, 23:04:58
    Author     : fingon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administration</title>
    </head>
    <body>
                        <form action="connectAdmin" method="post">
                    <tr>
                        <td>Identifiant</td>
                        <td><input type="text" name="login" id="login"/></td>

                    </tr>
                    <tr>
                        <td>Mot de passe</td>
                        <td><input type="password" name="password" id="password" /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="valider" /></td>
                        <td colspan="3"></td>
                    </tr>
                    </form>
    </body>
</html>
