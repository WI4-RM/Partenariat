<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<div class="divBody">
    <%
    String msg = (String)request.getAttribute("messageErreur");
    if (msg != null){
        %>
        <p style="color: red"><%= msg%></p>
        <%
    }
    String nom = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    ArrayList<entity.Rubrique> listeRub = (ArrayList<entity.Rubrique>)getServletContext().getAttribute("rubriques");
    %>
    <table width="100%" bgcolor="#b9c5d2" cellpadding="">
        <tr>
            <td><h1><%= nom%></h1></td>
            <%
            //if (request.getAttribute("connecte").equals("true")){
                %>
                <td align="right"><span class="alignementDroite"><a href="historique?idPays=<%= idPays%>">Historique</a></span></td>
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
        String texteRub = curRub.getTexte();
        int idRub = curRub.getIdrubrique();
        String idDivTexte = "idDiv" + nomRub;
        String idParaRub = "paragrapheRub" + nomRub;
        %>
        <table width="100%" bgcolor="#cae3ff"><div id="<%= nomRub%>">
            <tr>
                <td><h2><%= nomRub%></h2></td>
                <%
                //if (request.getAttribute("connecte").equals("true")){
                    %>
                    <td align="right">
                        <span class="alignementDroite"><a href='javascript:modifierRubrique("<%= idPays%>","<%= idRub%>","<%= idParaRub%>", "<%= idDivTexte%>")'>Modifier</a>
                        <br/><a href="modifierPays?action=supprimerRubrique&idPays=<%= idPays%>&idRubrique=<%= curRub.getIdrubrique()%>">Supprimer</a></span>
                    </td>
                    <%
                //}
                %>
            </tr>
        </div></table>
                <div id='<%= idDivTexte%>'><p id="<%= idParaRub%>"> <%= texteRub%></p></div>
        <%
    }
    %>
    
    <%
    //if (request.getAttribute("connecte").equals("true")){
        %>
        <div id="idNouvelleRubrique"><input type="button" onclick="javascript:nouvelleCategorie('<%= idPays%>')" value="Ajouter une categorie"></div>
         <%
    //}
    %>
    <%-- TODO Liste des fichiers uploadés--%>
</div>