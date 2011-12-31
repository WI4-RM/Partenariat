<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<div class="divBody">
    <%
    String nom = (String)request.getAttribute("nom");
    ArrayList<entity.Rubrique> listeRub = (ArrayList<entity.Rubrique>)getServletContext().getAttribute("rubriques");
    %>
    <h1><%= nom%></h1>
    <%
    for (int i = 0; i < listeRub.size(); i++){
        entity.Rubrique curRub = listeRub.get(i);
        %>
        <h2><%= curRub.getNom()%></h2>
        <p><%= curRub.getTexte()%></p>
        <%
    }
    %>
</div>