<%-- 
    Document   : pays
    Created on : 9 déc. 2011, 14:16:02
    Author     : lolo
--%>

<%@page import="controller.ControllerServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.SimpleDateFormat,entity.Rubrique,entity.Pays,entity.FichierUploade,entity.Ville,entity.Destination"%>
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
    Pays pays = (Pays)request.getAttribute("pays"); //Le pays est envoyé par le servlet controleur
    String nomPays = pays.getNom();
    int idPays = pays.getIdpays();
    ArrayList<Rubrique> listeRub = (ArrayList<Rubrique>)getServletContext().getAttribute("rubriques");
    ArrayList<String> listeTitresRub = (ArrayList<String>)getServletContext().getAttribute("titresRub");
    List<FichierUploade> listeFichiers = (List<FichierUploade>)getServletContext().getAttribute("fichiers");
    List<Ville> listeVilles = (List<Ville>)getServletContext().getAttribute("villes");
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
        if (listeVilles.size() > 0 && ControllerServlet.isConnected(request)){
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
    for (int i = 0; i < listeRub.size(); i++){  //On parcourt la liste des rubriques
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
                if (ControllerServlet.isConnected(request)){
                    %>
                <td align="right">
                    <span class="alignementDroite">
                        <a href='javascript:modifierRubrique("<%= idPays%>","<%= idRub%>","<%= idParaRub%>", "<%= idDivTexte%>","<%= nomRub%>")'>
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
    if (ControllerServlet.isConnected(request)){
        %>
    <br/>
    <div id="idNouvelleRubrique">
        <input type="button" onclick="javascript:nouvelleCategorie('<%= idPays%>')" value="Ajouter une categorie">
    </div>
         <%
    }
    
    //Affichage des villes
    if (ControllerServlet.isConnected(request) && listeVilles.size() > 0){ //Si il y a des villes
        %>
        <table bgcolor="#cae3ff" width="100%"><tr><td><h2 id="villes">Villes</h2></td></tr></table> <!-- titre2 pour les villes -->
        <%
        for (int j = 0; j < listeVilles.size(); j++) {  //Parcours de la liste des villes
            Ville ville = listeVilles.get(j);
            String nomVille = ville.getVille();
            int idVille = ville.getIdVille();
            String idDiv = "idDivNouvelleDestination" + nomVille;   //L'id de la div qui contiendra le formulaire
                                                                    //d'ajout d'une nouvelle destination
            List<Destination> listeDest = ville.getDestinationList();   //La liste de toutes les destinations (stage, semestres... de la ville)
            ArrayList<Destination> stage = new ArrayList<Destination>();    //On crée un liste pour le stage
            ArrayList<Destination> semestre = new ArrayList<Destination>(); //Une pour les départs à l'étranger
            ArrayList<Destination> tourisme = new ArrayList<Destination>(); //Une pour le tourisme
            for (int k = 0; k < listeDest.size(); k++){ //Pour chaque destination, on l'ajoute dans la bonne liste
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
    <table width="100%">    <!-- On crée un tableau pour le nom de la ville, et le lien pour rajouter une destination -->
        <tr>
            <td><blockquote><blockquote><h3><%= nomVille%></h3></blockquote></blockquote></td>
            <td><a href="javascript:addDestination('<%= idDiv%>', '<%= idVille%>')">J'ai visité aussi !</a></td>
        </tr>
    </table>
    <div id="<%= idDiv%>"></div>    <!-- div d'ajout d'une nouvelle destination, rempli par le javascript -->
    <ul>
            <%
            if (stage.size() > 0){  //Si il y a eu des stages de faits dans cette ville
            %>
        <li><a href="javascript:visibilite('stages<%= nomVille%>');">Stages</a></li>
            <div id="stages<%= nomVille%>" style="display: none;">
                    <%
                    for (int k = 0; k < stage.size(); k++){
                        Destination dest = stage.get(k);
                        String nom = dest.getProfil().getNom();
                        String prenom = dest.getProfil().getPrenom();
                        String org = dest.getOrganisme();
                        String com = dest.getCommentaire();
                    %>
                <blockquote><li type="circle"><a href=""><%= prenom%> <%= nom%></a> a fait un stage chez <%= org%></li></blockquote>
                <p>"<%= com%>"</p>
                <%
                }
                %>
            </div>
                <%
            }
            if (semestre.size() > 0){
            %>
        <li><a href="javascript:visibilite('semestres<%= nomVille%>');">Départs universitaires</a></li>
        <div id="semestres<%= nomVille%>" style="display: none;">
                <%
                for (int k = 0; k < semestre.size(); k++){
                    Destination dest = stage.get(k);
                    String nom = dest.getProfil().getNom();
                    String prenom = dest.getProfil().getPrenom();
                    String org = dest.getOrganisme();
                    String com = dest.getCommentaire();
                %>
            <blockquote><li type="circle"><a href=""><%= prenom%> <%= nom%></a> est parti à l'université <%= org%></li></blockquote>
            <p>"<%= com%>"</p>
                <%
            }
            %>
        </div>
            <%
            }
            if (tourisme.size() > 0){
            %>
        <li><a href="javascript:visibilite('tourisme<%= nomVille%>');">Tourisme</a></li>
        <div id="tourisme<%= nomVille%>" style="display: none;">
                <%
                for (int k = 0; k < tourisme.size(); k++){
                    Destination dest = stage.get(k);
                    String nom = dest.getProfil().getNom();
                    String prenom = dest.getProfil().getPrenom();
                    String org = dest.getOrganisme();
                    String com = dest.getCommentaire();
                %>
        <blockquote><li type="circle"><a href=""><%= prenom%> <%= nom%></a> a visité <%= org%></li></blockquote>
        <p>"<%= com%>"</p>
                <%
                }
            %>
        </div>
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
        <table bgcolor="#cae3ff" width="100%"><tr><td><h2 id="fichiersUpload">Fichiers uploadés :</h2></td></tr></table>
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
            <p><a href="downloadFile?nomFichier=<%= nomFichier%>"><%= nomFichier%></a> (<%= tailleFichier%> <%= ordre%>)
            <%
            if (ControllerServlet.isConnected(request)){
            %>
                mis en ligne par <a href=""><%= prenomProfil%> <%= nomProfil%></a> le <%= date%></p>
            <%
            }
            else {
            %>
                </p>
            <%
            }
        }
    }
    if (ControllerServlet.isConnected(request)){
        request.getSession().setAttribute("idPays", String.valueOf(idPays));
    %>
    <div id='idDivNouveauFichier'>
        <input type="button" onclick="javascript:nouveauFichier('idDivNouveauFichier','<%= idPays%>')" value="Uploader un fichier">
    </div>
    <%
    }
    %>
        <span class="alignementDroite"><a href="javascript:goToSection('<%= nomPays%>')">Retourner en haut de la page</a></span>
        
</div>