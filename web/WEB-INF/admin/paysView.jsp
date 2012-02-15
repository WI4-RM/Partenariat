<%-- 
    Document   : paysView
    Created on : 28 janv. 2012, 23:56:20
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
                    Pays
                </td>
                <td>
                    action
                </td>
                

                </tr>
                <%
                    List<entity.Pays> listePays = (List<entity.Pays>) request.getSession(false).getAttribute("paysList");
                    if (listePays != null) {
                        for (int i = 0; i < listePays.size(); i++) {
                            entity.Pays p = listePays.get(i);
                            String nom = p.getNom();
                            int id = p.getIdpays();
                %>
                <tr>
                <td>
                    <%=nom%>
                </td>
                <td><a href="delPays?idDelPays=<%=id%>">supprimer </a> </td>

                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>
