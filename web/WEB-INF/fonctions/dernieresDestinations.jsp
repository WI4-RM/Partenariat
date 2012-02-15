<%--
    Document   : dernieresDestinations
    Created on : 31 déc. 2011, 15:05:15
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*, java.lang.Math"%>
<%
    List<entity.Destination> listeDestinations = (List<entity.Destination>)getServletContext().getAttribute("dernieresDestinations");
    if (listeDestinations != null && listeDestinations.size() > 0){
        %>
        <li>Dernières destinations ajoutées</li>
        <ul>
        <%
        int max = Math.min(listeDestinations.size(), 3);
        for (int i = 0; i < max; i++){
            entity.Destination d = listeDestinations.get(i);
            String ville = "ville";//d.getVille();
            int id = 0;
            %>
            <li><a href="pays?idPays=<%= id%>&nom=<%= ville%>"><%= ville%></a></li>
            <%
        }
        %>
        </ul>
        <%
    }
    %>