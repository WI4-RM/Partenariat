<%--
    Document : listeProfils
    Created on : 18 janv. 2012, 23:25:10
    Author : Pauline
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<div class="divBody">
    <h1>Liste des Profils :</h1>
    <%
        List<entity.Profil> listeProfils = (List<entity.Profil>)getServletContext().getAttribute("tousProfils");
        if (listeProfils != null){
            for (int i = 0; i < listeProfils.size(); i++){
                entity.Profil p = listeProfils.get(i);
                String nom = p.getNom();
                int id = p.getIdprofil();
                %>
                <a href="xProfile?id=<%= id%>"><%= nom%></a>
                <br/>
                <%
            }
        }
    %>
</div>