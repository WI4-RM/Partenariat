<%-- 
    Document   : listePays
    Created on : 31 déc. 2011, 15:05:15
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<div class="divBody">
    <h1>Liste des pays :</h1>
    <%
        List<entity.Pays> listePays = (List<entity.Pays>)getServletContext().getAttribute("tousPays");
        if (listePays != null){
            for (int i = 0; i < listePays.size(); i++){
                entity.Pays p = listePays.get(i);
                String nom = p.getNom();
                int id = p.getIdpays();
                %>
                <a href="pays?idPays=<%= id%>&nom=<%= nom%>"><%= nom%></a>
                <br/>
                <%
            }
        }
    %>
</div>
