<%-- 
    Document   : accounts
    Created on : 25 janv. 2012, 19:52:20
    Author     : fingon
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 3.2 Final//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comptes</title>
    </head>
    <body>
        <div>
            <table>
                <tr>
                <td>
                    email
                </td>
                <td>administrateur
                </td>
                

                </tr>
                <%
                    List<entity.Compte> listeComptes = (List<entity.Compte>) request.getSession(false).getAttribute("comptes");
                    if (listeComptes != null) {
                        for (int i = 0; i < listeComptes.size(); i++) {
                            entity.Compte c = listeComptes.get(i);
                            String nom = c.getEmail();
                            boolean isAdmin = c.getIsAdministrator();
                %>
                <tr>
                <td>
                    <%=nom%>
                </td>
                <td><%= isAdmin%> </td>

                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
