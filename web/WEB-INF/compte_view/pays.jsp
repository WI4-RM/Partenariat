<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,javax.ejb.EJB,session.FichierUploadeFacade,entity.FichierUploade"%>
<div class="divBody">
    <%
    String msg = (String)request.getAttribute("messageErreur");
    if (msg != null){
        %>
    <p style="color: red"><%= msg%></p>
        <%
    }
    String nomPays = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    ArrayList<entity.Rubrique> listeRub = (ArrayList<entity.Rubrique>)getServletContext().getAttribute("rubriques");
    ArrayList<String> listeTitresRub = (ArrayList<String>)getServletContext().getAttribute("titresRub");
    %>
    <table width="100%" bgcolor="#b9c5d2" cellpadding="">
        <tr>
            <td><h1 id="<%= nomPays%>"><%= nomPays%></h1></td>
            <%
            //if (request.getAttribute("connecte").equals("true")){
                %>
            <td align="right"><span class="alignementDroite"><a href="historique?idPays=<%= idPays%>">Historique</a></span></td>
                <%
            //}
            %>
        </tr>
    </table>
        <ul>
            <li><a href="javascript:goToSection('map')">Carte</a></li>
            <%

            for (int titreIndex = 0; titreIndex < listeTitresRub.size() ; titreIndex++){
                String titre = listeTitresRub.get(titreIndex);
                %>
                <li><a href="javascript:goToSection('<%= titre%>')"><%= titre%></a></li>
                <%
            }
            %>
        </ul>
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
        <table width="100%" bgcolor="#cae3ff">
            <div id="<%= nomRub%>">
                <tr>
                    <td><h2 id="<%= nomRub%>"><%= nomRub%></h2></td>
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
            </div>
        </table>
        <div id='<%= idDivTexte%>'>
            <p id="<%= idParaRub%>"> <%= texteRub%></p>
        </div>
        <h2>Fichiers uploadés :</h2>
        <%
        FichierUploadeFacade fichierUploadeFacade = (FichierUploadeFacade)getServletContext().getAttribute("fichierUploadeFacade");
        List<FichierUploade> listeFichiers = fichierUploadeFacade.findByIdrubrique(idRub);
        for (int j = 0; j < listeFichiers.size(); j++) {
            FichierUploade fichier = listeFichiers.get(j);
            String nomFichier = fichier.getNom();
            int tailleFichier = fichier.getTaille();
            String nomProfil = fichier.getProfilIdprofil().getNom();
            String prenomProfil = fichier.getProfilIdprofil().getPrenom();
            %>
            <p><a href="<%= nomFichier%>"><%= nomFichier%></a> (<%= tailleFichier%>), mis en ligne par <a href=""><%= prenomProfil%><%= nomProfil%></a></p>
            <%
        }
        
        String idDivNouveauFichier = "idNouveauFichier" + nomRub;
        %>
        <div id='<%= idDivNouveauFichier%>'>
            <input type="button" onclick="javascript:nouveauFichier('<%= idDivNouveauFichier%>','<%= idRub%>')" value="Uploader un fichier">
        </div>
            <a href="javascript:goToSection('<%= nomPays%>')">Retourner en haut de la page</a>
        <%
    }
    %>
    <%
    //if (request.getAttribute("connecte").equals("true")){
        %>
        <br/>
        <div id="idNouvelleRubrique">
            <input type="button" onclick="javascript:nouvelleCategorie('<%= idPays%>')" value="Ajouter une categorie">
        </div>
         <%
    //}
    %>
    <%-- TODO Liste des fichiers uploadés--%>
</div>