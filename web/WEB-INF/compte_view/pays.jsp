<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<div class="divBody">
    <%
    String nom = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    ArrayList<entity.Rubrique> listeRub = (ArrayList<entity.Rubrique>)getServletContext().getAttribute("rubriques");
    %>
    <table width="100%" bgcolor="#b9c5d2">
        <tr>
            <td><h1><%= nom%></h1></td>
            <%
            //if (request.getAttribute("connecte").equals("true")){
                %>
                <td align="right"><span class="alignementDroite"><a href="modifierPays?action=modifierPays&idPays=<%= idPays%>">Modifier</a></span></td>
                <%-- TODO <td><a href="modifierPays?action=supprimerPays&idPays=<%= idPays%>">Supprimer</a></td>--%>
                <%
            //}
            %>
        </tr>
    </table>
    <div id="map"></div>
    <br/>
    <%
    for (int i = 0; i < listeRub.size(); i++){
        entity.Rubrique curRub = listeRub.get(i);
        String nomRub = curRub.getNom();
        %>
        <table width="100%" bgcolor="#cae3ff"><div id="<%= nomRub%>">
            <tr>
                <td><h2><%= nomRub%></h2></td>
                <%
                //if (request.getAttribute("connecte").equals("true")){
                    %>
                    <td align="right">
                        <span class="alignementDroite"><a href="modifierPays?action=modifierRubrique&idPays=<%= idPays%>&idRubrique=<%= curRub.getIdrubrique()%>">Modifier</a></span>
                    </td>
                    <%-- TODO <td><a href="modifierPays?action=supprimerRubrique&nomPays=<%= nom%>&idRubrique=<%= curRub.getIdrubrique()%>">Supprimer</a></td>--%>
                    <%
                //}
                %>
            </tr>
        </div></table>
            <p><%= curRub.getTexte()%></p>
        <%
    }
    %>
    
    <%
    //if (request.getAttribute("connecte").equals("true")){
        %>
        <div id="idNouvelleRubrique"><a href="javascript:nouvelleCategorie(<%= idPays%>)">Ajouter une categorie</a></div>
        <%
    //}
    %>
    <%-- TODO Liste des fichiers uploadés--%>
</div>