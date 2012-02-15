/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import entity.Compte;
import entity.Destination;
import entity.FichierUploade;
import entity.Pays;
import entity.Rubrique;
import entity.Profil;
import entity.Ville;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import partenariat.PaysManager;
import partenariat.RubriqueManager;
import partenariat.Util;

import session.InscriptionManager;
import validator.InputValidator;

/**
*
* @author fingon
*/
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/index","/inscription","/inscriptionValidation","/connect", "", "/deconnect","/index.html", 
        "/pays", "/historique", "/paysAlphabet","/afficherRecherche", "/recherche", "/listePays", "/nouvelleDestination",
        "/dernieresDestinations", "/nouveauPays", "/modifierPays", "/uploadFichier", "/downloadFile","/myProfile","/xProfile","/listeProfils","/rechercheResult","/changeInfoPerso","/addDestination"})
public class ControllerServlet extends HttpServlet {

    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;

    @EJB
    private InscriptionManager inscriptionManager;

    @EJB
    private PaysManager paysManager;

    @EJB
    private RubriqueManager rubriqueManager;

    @EJB
    private session.PaysFacade paysFacade;

    @EJB
    private session.RubriqueFacade rubriqueFacade ;

    @EJB
    private session.DestinationFacade destinationFacade ;

    @EJB
    private session.FichierUploadeFacade fichierUploadeFacade ;

    @EJB
    private session.ProfilFacade profilFacade ;
    
    @EJB
    private session.CompteFacade compteFacade ;
    
    @EJB
    private session.VilleFacade villeFacade ;
    
    @EJB
    private partenariat.FichierUploadeManager fichierUploadeManager;
    
    @EJB
    private partenariat.DestinationManager destinationManager;
    
    @EJB
    private partenariat.VilleManager villeManager;
    
    private String dossierFichiersUploades = "/fichiersUploades";
    
    
    /**
* Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        String url = "";
        
       HttpSession session = request.getSession(false);

        //INUTILE : me sert à tester si je suis connectée ou pas
        if (request.getSession(false) != null && !request.getSession(false).isNew() ){
           // request.getSession().invalidate();
           request.getSession().setAttribute("profil", profilFacade.findAll().get(0));
           session.setAttribute("idProfil", profilFacade.findAll().get(0).getIdprofil());
        }
        else{//
            this.createNewSession(request, "sessionLauria");
            session.setAttribute("idProfil",1) ;
        }

        getServletContext().setAttribute("derniersPays", paysFacade.findAllOrderedById());


        if (userPath.equals("/index.html") || userPath.equals("/index") || userPath.equals("")) {  //Page d'accueil
            url = "/WEB-INF/compte_view/pagePrincipale.jsp";

        }

        else if (userPath.equals("/nouveauPays")){  //Créer un nouveau pays
            if (ControllerServlet.isConnected(request) ){
                String nomPays = request.getParameter("nouveauPays");
                if (!nomPays.equals("")){
                    nomPays = partenariat.Util.InitialeMajuscule(nomPays);
                    List<Pays> pays = paysFacade.findByNom(nomPays);
                    int idPays;

                    if ((pays == null) || (pays.size() == 0) || (pays.get(0) == null)){
                        paysManager.createPays(nomPays, (Integer) session.getAttribute("idProfil"));
                        idPays = paysFacade.findByNom(nomPays).get(0).getIdpays();
                    }
                    else {
                        idPays = pays.get(0).getIdpays();
                    }
                    url = "/pays?idPays=" + idPays;
                }
                else {
                    request.setAttribute("erreurCreationPays","Le nom du pays entré est vide");
                    url = "";
                }
            }
            else {
                url = "";
            }
        }

        else if (userPath.equals("/pays")) {    //Page d'un pays
            int idPays = Integer.parseInt(request.getParameter("idPays"));
            Pays pays = paysFacade.findByIdpays(idPays).get(0);

            List listeRubriques = rubriqueFacade.findByIdPays(idPays);
            ArrayList<String> titresRubriques = new ArrayList<String>();
            ArrayList<entity.Rubrique> rubriquesPubliees = new ArrayList<entity.Rubrique>();
            ArrayList<String> titresRubriquesTriees = new ArrayList<String>();
            ArrayList<entity.Rubrique> rubriquesPublieesTriees = new ArrayList<entity.Rubrique>();
            int tailleListe = listeRubriques.size();
            
            for (int i = 0; i < tailleListe; i++){ //On parcourt la liste de toutes les rubriques qui sont ordonnées par ordre décroissant de date de création
                entity.Rubrique curRub = (Rubrique)listeRubriques.get(i);   //On choisit l'élément rubrique de la liste
                String titre = curRub.getNom(); //On en extrait son titre
                if (!titresRubriques.contains(titre)){  //Si on n'a pas déjà eu une rubrique avec ce titre, ie c'est la plus récente version de cette rubrique
                    titresRubriques.add(titre); //On ajoute le titre de la rubrique à la liste des rubriques déjà traitees
                    if (!curRub.getTexte().equals("--null--")){ //On vérifie que la rubrique n'a pas été supprimée
                        rubriquesPubliees.add(curRub);  //On ajoute la rubrique à la liste de rubriques à afficher
                    }
                }
            }

            for (int i = 1; i <= tailleListe; i++){
                entity.Rubrique curRub = (Rubrique)listeRubriques.get(tailleListe - i); //On parcourt la liste dans l'autre sens, ie on s'intéresse à la première
                                                                                        //fois que la rubrique a été créée
                String titre = curRub.getNom();
                if (!titresRubriquesTriees.contains(titre)){
                    titresRubriquesTriees.add(titre);
                    for (int j = 0; j < rubriquesPubliees.size(); j++){
                        entity.Rubrique tempRub = (Rubrique)rubriquesPubliees.get(j);
                        if (tempRub.getNom().equals(titre)){
                            rubriquesPublieesTriees.add(tempRub);
                            break;
                        }
                    }
                }
            }

            request.setAttribute("pays", pays);

            getServletContext().setAttribute("titresRub", titresRubriquesTriees);
            getServletContext().setAttribute("rubriques", rubriquesPublieesTriees);
            getServletContext().setAttribute("fichiers", fichierUploadeFacade.findByIdpays(idPays));
            getServletContext().setAttribute("villes", villeFacade.findByIdpays(idPays));

            url = "/WEB-INF/compte_view/pays.jsp";
        }
        else if (userPath.equals("/paysAlphabet")) {    //Fenetre de la page ppale qui donne les pays existants classés par initiale
            String lettre = request.getParameter("lettre");
            
            int lg = lettre.length();
            
            if (lg == 1) {
                String lettrePourcent = lettre + '%';
                getServletContext().setAttribute("paysAlphabet", paysFacade.findByFirstLetter(lettrePourcent));
                request.setAttribute("lettre",lettre);
            }
            else
                if (lg > 1){
                    ArrayList<Pays> liste = new ArrayList<Pays>();
                    for (int i = 0; i < lg ; i++){
                        char c = lettre.charAt(i);
                        if (c != '-'){
                            String lettrePourcent = String.valueOf(c) + '%';
                            List listeLettre = paysFacade.findByFirstLetter(lettrePourcent);
                            liste.addAll(listeLettre);
                        }
                    }
                    request.setAttribute("lettre",lettre);
                    getServletContext().setAttribute("paysAlphabet", liste);
                }
            url = "WEB-INF/fonctions/cataloguePays.jsp";
        }
        else if (userPath.equals("/recherche")) {   //TODO
            String type = (String)request.getParameter("type");
            if (type == null){
                ;
            }
            if (type.equals("rapide")){
                ;
            }
            else {//cherche les profils dont le nom ou prenom correspond à la requête                
                    String profil = (String)request.getParameter("profil");
                    System.out.println("ok");
                    if (profil !=null){
                        ArrayList<Integer> idList = new ArrayList<Integer>();
                        List<Profil> profilListNom = new ArrayList<Profil>();                        
                            profilListNom = profilFacade.findByNom(profil);                        
                        List<Profil> profilListPrenom = new ArrayList<Profil>(); 
                            profilListPrenom = profilFacade.findByPrenom(profil);
                        int idProfil=-1;
                        if ((profilListNom.isEmpty())&&(profilListPrenom.isEmpty())){
                           getServletContext().setAttribute("valid","false");
                           url = "/WEB-INF/compte_view/rechercheResult.jsp";
                       }
                        else{
                        if (profilListNom.isEmpty()==false){
                        for (int i=0; i<profilListNom.size();i++){
                            idProfil = profilListNom.get(i).getIdprofil();
                            idList.add(idProfil);
                        }
                        }
                        if (profilListPrenom.isEmpty()==false){
                           for (int i=0; i<profilListPrenom.size();i++){
                            idProfil = profilListPrenom.get(i).getIdprofil();
                            idList.add(idProfil);
                        }
                        }
                            getServletContext().setAttribute("idList", idList);
                            getServletContext().setAttribute("valid","true");
                            System.out.println(idList.size());
                            url = "/rechercheResult";
                       
                    }
            }
                    
                    
                    
            }
        }
        else if (userPath.equals("/afficherRecherche")) {   //Affiche la page de recherche            
            url = "/WEB-INF/compte_view/recherche.jsp";
               }
        
        else if (userPath.equals("/rechercheResult")) {
                     
                List<Integer> idList = (List<Integer>) getServletContext().getAttribute("idList");
                System.out.println(idList.size());
                int id;
                int nbr = idList.size();
                
                ArrayList<String> noms = new ArrayList<String>();
                ArrayList<String> prenoms = new ArrayList<String>();
                
                String name = null;
                String firstName = null;
                
                if (nbr == 0)
                    url = "/WEB-INF/compte_view/rechercheResult.jsp";
                else{
                    for (int i = 0; i< idList.size(); i++){
                    id = idList.get(i);
                    
                    name  = profilFacade.findByIdprofil(id).get(0).getNom();
                    firstName = profilFacade.findByIdprofil(id).get(0).getPrenom();
                   
                    System.out.println(firstName);
                    noms.add(name);
                    prenoms.add(firstName);
                    } 
                    getServletContext().setAttribute("noms", noms);
                    getServletContext().setAttribute("prenoms", prenoms);
                    
                     url = "/WEB-INF/compte_view/rechercheResult.jsp";        
                }     
     }
    
        else if (userPath.equals("/myProfile")) {  //Page profil perso
            
            //int id =(Integer)request.getSession().getAttribute("idProfil");
            
                    int id = 1;
                    String name = profilFacade.findByIdprofil(id).get(0).getNom();
                    String firstName = profilFacade.findByIdprofil(id).get(0).getPrenom();
                    int promo = profilFacade.findByIdprofil(id).get(0).getPromo();
                    String email = compteFacade.findByIdprofil(id).get(0).getEmail();
                    
                    
                    List<Destination> listDest = destinationFacade.findByIdprofil(id);
                    /*
                     if ((listDest==null)||(listDest.size() == 0)||(listDest.get(0) == null)){
                         List<String> listeVille = new ArrayList<String>();
                         List<String> listePays = new ArrayList<String>();
                         for (int i=0; i<listDest.size(); i++){
                            entity.Ville ville = destinationFacade.findByIdprofil(id).get(0).getDestinationidDestination();
                            int idVille = ville.getIdVille();
                            listeVille.add(ville.getVille());
                            listePays.add(ville.getPaysIdpays().getNom());
                            
                         }
                           getServletContext().setAttribute("pays", listePays);
                           getServletContext().setAttribute("ville", listeVille);
                     }
                    
                    */
                    
                    //String ville = destinationFacade.findByProfilIdprofil(id).get(0).getVille();    
                    
                     getServletContext().setAttribute("id", id);
                     getServletContext().setAttribute("nom", name);
                     getServletContext().setAttribute("prenom", firstName);
                     getServletContext().setAttribute("promo", promo);
                     getServletContext().setAttribute("email", email);                     
            
            //getServletContext().setAttribute("pays", pays);
            //getServletContext().setAttribute("ville", ville);
            
            
            url = "/WEB-INF/compte_view/myProfile.jsp";
        }
        
        
         else if (userPath.equals("/changeInfoPerso")) {
             //int id =(Integer)request.getSession().getAttribute("idprofil");
             int id = 1;
             String nom = request.getParameter("nom");
             String prenom = request.getParameter("prenom");
             int promo = Integer.parseInt(request.getParameter("promo"));
             
             profilFacade.changeNom(nom, id);
             profilFacade.changePrenom(prenom, id);
             profilFacade.changePromo(promo, id);
             
             url = "/myProfile";
         }
         
         
         
         else if (userPath.equals("/addDestination")){
             
             //ville=ville&pays=pays&type=Tourisme&jour=12&mois=12&an=1212
             String ville = Util.InitialeMajuscule(request.getParameter("ville"));
             String pays = Util.InitialeMajuscule(request.getParameter("pays"));
             String type = request.getParameter("type");
             String com = request.getParameter("com");
         /*    int and = Integer.parseInt(request.getParameter("and"));
             int moisd = Integer.parseInt(request.getParameter("moisd"));
           //  int jourd = Integer.parseInt(request.getParameter("jourd"));
             int ana = Integer.parseInt(request.getParameter("ana"));
             int moisa = Integer.parseInt(request.getParameter("moisa"));
           //  int joura = Integer.parseInt(request.getParameter("joura"));*/
             String dA =request.getParameter("ana").concat("-").concat(request.getParameter("moisa")).concat("-").concat(request.getParameter("joura"));
             String dD =request.getParameter("and").concat("-").concat(request.getParameter("moisd")).concat("-").concat(request.getParameter("jourd"));
             System.out.println("dA="+dA);
             SimpleDateFormat SDF =new SimpleDateFormat("YYYY-mm-dd");
             Date dateD = new Date();
             Date dateA = new Date();
            try {
                dateD = (Date) SDF.parse(dD);
                dateA = (Date) SDF.parse(dA);
            } catch (ParseException ex) {
                Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
             System.out.println("dateA="+SDF.format(dateA));
             /*
             dated.setYear(and);
             dated.setMonth(moisd);
            // dated.setDay(jourd);
             datea.setYear(2000);
             datea.setMonth(04);
            // datea.setDay(joura);
               */      
             
             
             int idPays;
             int idVille;
             
             List<Pays> listPays = paysFacade.findByNom(pays);
             List<Ville> listVilles = villeFacade.findByVille(ville);
             if ((listPays==null)||(listPays.size() == 0)||(listPays.get(0) == null)){
                 paysManager.createPays(pays, 1);
                 idPays = paysFacade.findByNom(pays).get(0).getIdpays();
                 villeManager.createVille(ville, idPays, 0, 0, 0);
                 idVille = villeFacade.findByVille(ville).get(0).getIdVille();
                 
             }
             else{
                 idPays = listPays.get(0).getIdpays();
                 if ((listVilles)==null||(listVilles.size() == 0)||(listVilles.get(0) == null)){
                     villeManager.createVille(ville, idPays, 0, 0, 0);
                     idVille = villeFacade.findByVille(ville).get(0).getIdVille();
                 }
                 else{
                     idVille = listVilles.get(0).getIdVille();
                 }
             }
                 
             
            destinationManager.createDestination(idVille, idPays, 1/*(Integer)request.getSession().getAttribute("idProfil")*/, type, "organ", com, dateD, dateA);
         
         }
         
         
         
        else if (userPath.equals("/xProfile")) {
            
          Integer id = Integer.parseInt(request.getParameter("id"));  
          
                    String name = profilFacade.findByIdprofil(id).get(0).getNom();
                    String firstName = profilFacade.findByIdprofil(id).get(0).getPrenom();
                    int promo = profilFacade.findByIdprofil(id).get(0).getPromo();
                    String email = compteFacade.findByIdprofil(id).get(0).getEmail();
                                                      
                    //String pays = destinationFacade.findByProfilIdprofil(id).get(0).getPays().getNom();
                    //String ville = destinationFacade.findByProfilIdprofil(id).get(0).getVille();    
                    
                     getServletContext().setAttribute("id", id);
                     getServletContext().setAttribute("nom", name);
                     getServletContext().setAttribute("prenom", firstName);
                     getServletContext().setAttribute("promo", promo);
                     getServletContext().setAttribute("email", email);                     
            
            //getServletContext().setAttribute("pays", pays);
            //getServletContext().setAttribute("ville", ville);
            
            
            url = "/WEB-INF/compte_view/xProfile.jsp";
                      
        }
        
         else if (userPath.equals("/listeProfils")){    //Affiche la page de tous les pays créés
            getServletContext().setAttribute("tousProfils", profilFacade.findAllOrderedByName());
            url = "/WEB-INF/compte_view/listeProfils.jsp";
        }
        
        else if (userPath.equals("/listePays")){    //Affiche la page de tous les pays créés
            getServletContext().setAttribute("tousPays", paysFacade.findAllOrderedByName());
            url = "/WEB-INF/compte_view/listePays.jsp";
        }

        else if (userPath.equals("/modifierPays")){ //Modification des rubriques d'un pays
            if (ControllerServlet.isConnected(request) ){
                String action = request.getParameter("action");
                int idPays = Integer.parseInt(request.getParameter("idPays"));
                Pays pays = paysFacade.findByIdpays(idPays).get(0);

                if (action.equals("ajouterRubrique")){
                    String nouveauTitre = request.getParameter("titreNouvelleRubrique");
                    nouveauTitre = partenariat.Util.InitialeMajuscule(nouveauTitre);
                    List<Rubrique> liste = rubriqueFacade.findByNomEtIdpays(nouveauTitre,idPays);

                    if ((liste == null) || (liste.size() == 0) || (liste.get(0) == null)){
                        if (nouveauTitre.equals("")){
                            request.setAttribute("messageErreur","Le titre de la rubrique que vous voulez créer est vide");
                        }
                        else {
                            String nouveauContenu = request.getParameter("contenuNouvelleRubrique");
                            rubriqueManager.createRubrique(nouveauTitre, nouveauContenu, idPays);
                        }
                    }
                    else {
                        request.setAttribute("messageErreur","La catégorie " + nouveauTitre + " existe déjà !");
                    }
                }

                else if (action.equals("modifierRubrique")){
                    int idRubrique = Integer.parseInt(request.getParameter("idRubrique"));
                    String nouveauContenu = request.getParameter("nouveauContenuRubrique");
                    rubriqueManager.updateText(idRubrique, nouveauContenu, idPays);
                }

                else if (action.equals("supprimerRubrique")){
                    int idRubrique = Integer.parseInt(request.getParameter("idRubrique"));
                    rubriqueManager.updateText(idRubrique, "--null--", idPays);
                }
                url= "/pays?idPays="+idPays;
            }
            else {
                url = "";
            }
        }

        else if (userPath.equals("/historique")){
            int idPays = Integer.parseInt(request.getParameter("idPays"));
            String nomPays = paysFacade.findByIdpays(idPays).get(0).getNom();
            List<Rubrique> listeRub = rubriqueFacade.findOrderedByNameThenDate(idPays);
            request.setAttribute("nom",nomPays);
            request.setAttribute("idPays", request.getParameter("idPays"));
            getServletContext().setAttribute("historique", listeRub);
            url = "/WEB-INF/compte_view/historique.jsp";
        }

        else if (userPath.equals("/uploadFichier")){
            if (ControllerServlet.isConnected(request) ){
                //int idProfil = profilFacade.findAll().get(0).getIdprofil();
                int idProfil = (Integer) request.getAttribute("idProfil");
                int idPays = Integer.parseInt((String)request.getSession().getAttribute("idPays"));
                Pays pays = paysFacade.findByIdpays(idPays).get(0);
                try {
                    // Create a factory for disk-based file items
                    FileItemFactory factory = new DiskFileItemFactory();
                    // Create a new file upload handler
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    // Parse the request
                    List items;
                    items = upload.parseRequest(request);
                    // Process the uploaded items
                    Iterator iter = items.iterator();
                    FileItem item = (FileItem) iter.next();

                    String nom = item.getName();
                    int maxLongueurNom = 38;
                    int longNom = nom.length();
                    if (longNom > maxLongueurNom){
                        nom = nom.substring(0, maxLongueurNom-5) + nom.substring(longNom - 5);
                    }
                    nom = idPays + nom;
                    List<FichierUploade> liste = fichierUploadeFacade.findByNom(nom);
                    if ((liste != null) && (liste.size() > 0) && (liste.get(0) != null)){
                        String curNom;
                        int i = 2;
                        while (true){
                            curNom = i + "_" + nom;
                            List<FichierUploade> curListe = fichierUploadeFacade.findByNom(curNom);
                            if ((curListe != null) && (curListe.size() > 0) && (curListe.get(0) != null)){
                                i++;
                            }
                            else {
                                nom = curNom;
                                break;
                            }
                        }
                    }

                    long tailleEnBytes = item.getSize();

                    if (tailleEnBytes < 5*1048576){  //La taille du fichier doit être inférieure à 5Mo
                        //On copie le fichier
                        String context = getServletContext().getRealPath(dossierFichiersUploades + "/" + nom);
                        File uploadedFile = new File(context);
                        item.write(uploadedFile);
                        //On crée le fichier dans la bdd
                        fichierUploadeManager.createFichier(nom, idPays, idProfil, (int)tailleEnBytes);
                    }
                    else {
                        request.setAttribute("messageErreur","La taille du fichier de doit pas excéder 5Mo");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                url = "pays?idPays="+idPays;
            }
            else {
                url = "";
            }
        }

        else if (userPath.equals("/downloadFile")){
            String nomFichier = request.getParameter("nomFichier");
            url= dossierFichiersUploades + "/" + nomFichier;
        }

        else if (userPath.equals("/nouvelleDestination")){
            String action = request.getParameter("action");

            if (action.equals("villeExistante")){
                String sIdVille = request.getParameter("idVille");
                int idVille = Integer.parseInt(sIdVille);
                int idPays = villeFacade.findByIdVille(idVille).get(0).getPaysIdpays().getIdpays();
                String type = request.getParameter("type");
                String organisme =  request.getParameter("organisme");
                String commentaire = request.getParameter("commentaire");
                if (type.equals("stage") || type.equals("organisme") ||type.equals("tourisme")){
                   // destinationManager.createDestination(idVille, idPays, (Integer) request.getAttribute("idProfil"), type, organisme, commentaire, "0000-00-00","0000-00-00");
                }
                url = "pays?idPays=" + idPays;
            }
        }

        else if (userPath.equals("/inscription")) { //inscription request

            userPath = "inscription";
            url = "/WEB-INF/compte_view/inscription.jsp";
        }
        
        else if (userPath.equals("/deconnect")){ //deconnexion
            request.getSession().invalidate();
            userPath = "/pagePrincipale";
            url = "/WEB-INF/compte_view" +userPath + ".jsp";
        }
        try {
              
        request.getRequestDispatcher(url).forward(request, response);
        
        }catch (Exception ex) {
            ex.printStackTrace();
        } 
        //String url = "/WEB-INF/compte_view/" + userPath + ".jsp";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
* Handles the HTTP <code>GET</code> method.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
//        try {
//            if (ControllerServlet.isConnected(request) ){
//                request.setAttribute(inscription, "true");
//            }
//            else {
//                request.setAttribute("connecte", "false");
//            }
//            request.getRequestDispatcher(url).forward(request, response);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            //out.close();
//        }
    }

    /**
* Handles the HTTP <code>POST</code> method.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userPath = request.getServletPath();
        String url = "/WEB-INF/";
        
        
        if (userPath.equals("/inscriptionValidation")) {

            boolean allInputsOk = true;

                String name = request.getParameter("name");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String password2 = request.getParameter("password2"); 

                String day = request.getParameter("day");
                String month = request.getParameter("month");
                String yearS = request.getParameter("year");

                String phone = request.getParameter("phone");

                if (!InputValidator.checkEmail(email)){
                    allInputsOk = false;
                   // response.sendError(400, "email error");
                }
                if (!(InputValidator.checkNames(name)) || !(InputValidator.checkNames(username))){
                    allInputsOk = false;
                    //response.sendError(400, "name or username error");
                }
                if (!InputValidator.checkPassword(password)){
                    allInputsOk = false;
                userPath = "index";
                url += "compte_view/pagePrincipale.jsp";
                    //response.sendError(400, "passwd");
                }
                if (!InputValidator.checkYear(yearS)){
                    allInputsOk = false;
                    //response.sendError(400,"year");
                }
                if (!password.equals(password2)){
                    allInputsOk = false;
                    //response.sendError(400,"verification pasword failed");
                }
              boolean isOK = false;

                if (allInputsOk)
                    isOK = inscriptionManager.createUser(name, username, email, password, Integer.decode(yearS));

                if (isOK){
                    userPath = "confirmation";
                    createNewSession(request, email);
                }
                else
                    userPath ="errorSuscribe";

                url += "compte_view/" + userPath + ".jsp";
        }
        else if (userPath.equals("/connect")){
            String username = request.getParameter("login");
            String password = request.getParameter("password");
            boolean ok = false; // default : connection is not ok
            
            if (InputValidator.checkEmail(username))
                ok = inscriptionManager.connect(username, password);

            userPath = "pagePrincipale";
            url += "compte_view/" + userPath + ".jsp";
            //userPath = "index";
            if (ok){
                this.createNewSession(request, username);

            }

        }
//        else {
//            processRequest(request, response);
//        }
        
        request.getRequestDispatcher(url).forward(request, response);

//        try {
//            if (ControllerServlet.isConnected(request) ){
//                request.setAttribute("connecte", "true");
//            }
//            else {
//                request.setAttribute("connecte", "false");
//            }
//            request.getRequestDispatcher(url).forward(request, response);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

    }

    /**
* Returns a short description of the servlet.
* @return a String containing servlet description
*/
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

        /**
     * Create a session whan for user tracking
     * @param name : user name
     * @param request : request from servlet 
     */
    private void createNewSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        
        //add other attribut
        Compte c = compteFacade.findTheCompteByEmail(email);        
        Integer idProfil = c.getProfilIdprofil().getIdprofil();
        
        session.setAttribute("idProfil",idProfil );
        session.setAttribute("nom", profilFacade.findByIdprofil(idProfil).get(0).getNom());
        session.setAttribute("prenom",profilFacade.findByIdprofil(idProfil).get(0).getPrenom());
        //Cookie c = new Cookie("nom", name);
        
        
    }
    
    /**
     * 
     * @param request
     * @return true if user is logged
     */
    public static boolean isConnected(HttpServletRequest request){
        return request.getSession(false)!= null && request.getSession(false).getAttribute("email") != null;
    }
}