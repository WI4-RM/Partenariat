<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.SimpleDateFormat,entity.FichierUploade,entity.Ville,entity.Destination"%>
<div class="divBody">
    <%
    //On affiche les messages d'erreur
    String msg = (String)request.getAttribute("messageErreur");
    if (msg != null){
        %>
    <p style="color: red"><%= msg%></p>
        <%
    }

    //Récupération de tous les paramètres
    String nomPays = (String)request.getAttribute("nom");
    String idPays = (String)request.getAttribute("idPays");
    ArrayList<entity.Rubrique> listeRub = (ArrayList<entity.Rubrique>)getServletContext().getAttribute("rubriques");
    ArrayList<String> listeTitresRub = (ArrayList<String>)getServletContext().getAttribute("titresRub");
    List<FichierUploade> listeFichiers = (List<FichierUploade>)getServletContext().getAttribute("fichiers");
    List<Ville> listeVilles= (List<Ville>)getServletContext().getAttribute("villes");
    %>

    <!-- Titre de la page (ie, le nom du pays) -->
    <table width="100%" bgcolor="#b9c5d2" cellpadding="">
        <tr>
            <td><h1 id="<%= nomPays%>"><%= nomPays%></h1></td>
            <td align="right"><span class="alignementDroite"><a href="historique?idPays=<%= idPays%>">Historique</a></span></td>
        </tr>
    </table>

    <!-- Sommaire -->
    <ul>
        <li><a href="javascript:goToSection('map')">Carte</a></li>
        <%
        for (int titreIndex = 0; titreIndex < listeTitresRub.size() ; titreIndex++){
            String titre = listeTitresRub.get(titreIndex);
            %>
        <li><a href="javascript:goToSection('<%= titre%>')"><%= titre%></a></li>
            <%
        }
        if (listeVilles.size() > 0){
            %>
        <li><a href="javascript:goToSection('villes')">Villes</a></li>
            <%
        }
        if (listeFichiers.size() > 0){
            %>
        <li><a href="javascript:goToSection('fichiersUpload')">Fichiers</a></li>
            <%
        }
        %>
    </ul>

    <!-- Carte du pays -->
    <div id="map"></div>
    <br/>

    <!-- Affichage des rubriques -->
    <%
    for (int i = 0; i < listeRub.size(); i++){  //On parcout la liste des rubriques
        entity.Rubrique curRub = listeRub.get(i);
        String nomRub = curRub.getNom();
        String texteRub = curRub.getTexte();
        int idRub = curRub.getIdrubrique();
        String idDivTexte = "idDiv" + nomRub;
        String idParaRub = "paragrapheRub" + nomRub;
        %>
        <table width="100%" bgcolor="#cae3ff">
            <tr>
                <td><h2 id="<%= nomRub%>"><%= nomRub%></h2></td>
                <%
                if (request.getAttribute("connecte").equals("true")){
                    %>
                <td align="right">
                    <span class="alignementDroite">
                        <a href='javascript:modifierRubrique("<%= idPays%>","<%= idRub%>","<%= idParaRub%>", "<%= idDivTexte%>")'>
                            Modifier
                        </a>
                        <br/>
                        <a href="modifierPays?action=supprimerRubrique&idPays=<%= idPays%>&idRubrique=<%= curRub.getIdrubrique()%>">
                            Supprimer
                        </a>
                    </span>
                </td>
                    <%
                }
                %>
            </tr>
        </table>
        <div id='<%= idDivTexte%>'>
            <p id="<%= idParaRub%>"> <%= texteRub%></p>
        </div>
        <%
    }

    //Affichage de la possibilité d'ajouter une catégorie
    if (request.getAttribute("connecte").equals("true")){
        %>
    <br/>
    <div id="idNouvelleRubrique">
        <input type="button" onclick="javascript:nouvelleCategorie('<%= idPays%>')" value="Ajouter une categorie">
    </div>
         <%
    }
    
    //Affichage des villes
    if (request.getAttribute("connecte").equals("true") && listeVilles.size() > 0){
        %>
    <h2 id="villes">Villes</h2>
        <%
        for (int j = 0; j < listeVilles.size(); j++) {
            Ville ville = listeVilles.get(j);
            String nomVille = ville.getVille();
            int idVille = ville.getIdVille();
            String idDiv = "idDivNouvelleDestination" + nomVille;
            %>
    <table width="100%">
        <tr>
            <td><h3><%= nomVille%></h3></td>
            <%
            if (request.getAttribute("connecte").equals("true")){
            %>
            <td><a href="javascript:addDestination(<%= idDiv%>, <%= idVille%>)">J'ai visité aussi !</a></td>
            <%
        }
        %>
        </tr>
    </table>
    <div id="<%= idDiv%>"></div>
    <ul>
        <%
        List<Destination> listeDest = ville.getDestinationList();
        ArrayList<Destination> stage = new ArrayList<Destination>();
        ArrayList<Destination> semestre = new ArrayList<Destination>();
        ArrayList<Destination> tourisme = new ArrayList<Destination>();
        for (int k = 0; k < listeDest.size(); k++){
            Destination destination = listeDest.get(k);
            String type = destination.getType();
            if (type.equals("stage"))
                stage.add(destination);
            if (type.equals("semestre"))
                semestre.add(destination);
            if (type.equals("tourisme"))
                tourisme.add(destination);
        }
        %>
        <li>Stages</li>
        <%
        for (int k = 0; k < stage.size(); k++){
            Destination dest = stage.get(k);
            String nom = dest.getProfil().getNom();
            String prenom = dest.getProfil().getPrenom();
            String org = dest.getOrganisme();
            String com = dest.getCommentaire();
        %>
        <li type="circle"><a href=""><%= prenom%> <%= nom%></a> a fait un stage chez <%= org%></li>
        <p>"<%= com%>"</p>
        <%
        }
        %>
        <li>Départs universitaires</li>
        <%
        for (int k = 0; k < semestre.size(); k++){
            Destination dest = stage.get(k);
            String nom = dest.getProfil().getNom();
            String prenom = dest.getProfil().getPrenom();
            String org = dest.getOrganisme();
            String com = dest.getCommentaire();
        %>
        <li type="circle"><a href=""><%= prenom%> <%= nom%></a> est parti à l'université <%= org%></li>
        <p>"<%= com%>"</p>
        <%
        }
        %>
        <li>Tourisme</li>
        <%
        for (int k = 0; k < tourisme.size(); k++){
            Destination dest = stage.get(k);
            String nom = dest.getProfil().getNom();
            String prenom = dest.getProfil().getPrenom();
            String org = dest.getOrganisme();
            String com = dest.getCommentaire();
        %>
        <li type="circle"><a href=""><%= prenom%> <%= nom%></a> a visité <%= org%></li>
        <p>"<%= com%>"</p>
        <%
        }
        %>
    </ul>
        <%
        }
    }

    //Affichage des fichiers
    if (listeFichiers.size() > 0){
        %>
    <h2 id="fichiersUpload">Fichiers uploadés :</h2>
        <%
        for (int j = 0; j < listeFichiers.size(); j++) {
            FichierUploade fichier = listeFichiers.get(j);
            String nomFichier = fichier.getNom();
            SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yy" );
            String date = dateFormat.format(fichier.getDate());
            int tailleFichier = fichier.getTaille();
            String ordre = "Octets";
            if (tailleFichier/1024 > 1){
                tailleFichier = tailleFichier/1024;
                ordre = "Kio";
                if (tailleFichier/1024 > 1){
                tailleFichier = tailleFichier/1024;
                ordre = "Mio";
            }
            }
            String nomProfil = fichier.getProfilIdprofil().getNom();
            String prenomProfil = fichier.getProfilIdprofil().getPrenom();
            %>
            <p><a href="downloadFile?nomFichier=<%= nomFichier%>"><%= nomFichier%></a> (<%= tailleFichier%> <%= ordre%>),
                mis en ligne par <a href=""><%= prenomProfil%> <%= nomProfil%></a> le <%= date%></p>
            <%
        }
    }
    if (request.getAttribute("connecte").equals("true")){
        request.getSession().setAttribute("idPays", idPays);
    %>
    <div id='idDivNouveauFichier'>
        <input type="button" onclick="javascript:nouveauFichier('idDivNouveauFichier','<%= idPays%>')" value="Uploader un fichier">
    </div>
    <%
    }
    %>
        <span class="alignementDroite"><a href="javascript:goToSection('<%= nomPays%>')">Retourner en haut de la page</a></span>
        
</div>