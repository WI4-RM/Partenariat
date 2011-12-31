<%--
    Document   : dernieresDestinations
    Created on : 31 déc. 2011, 15:05:15
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.lang.Math"%>
<%
    List<entity.Pays> listePays = (List<entity.Pays>)getServletContext().getAttribute("derniersPays");
    if (listePays != null && listePays.size() > 0){
        %>
        <li>Dernières destinations ajoutées</li>
        <ul>
        <%
        int max = Math.min(listePays.size(), 3);
        for (int i = 0; i < max; i++){
            entity.Pays p = listePays.get(i);
            String nom = p.getNom();
            int id = p.getIdpays();
            %>
            <li><a href="pays?idPays=<%= id%>&nom=<%= nom%>"><%= nom%></a></li>
            <%
        }
        %>
        </ul>
        <%
    }
    %>