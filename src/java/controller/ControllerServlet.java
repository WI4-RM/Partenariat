/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import entity.Compte;
import entity.FichierUploade;
import entity.Pays;
import entity.Rubrique;
import entity.Ville;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import partenariat.FichierUploadeManager;
import javax.servlet.http.*;
import partenariat.Historique;
import partenariat.PaysManager;
import partenariat.RubriqueManager;
import session.CompteFacade;
import session.InscriptionManager;
import validator.InputValidator;



/**
*
* @author fingon
*/
@WebServlet(name = "ControllerServlet",
loadOnStartup = 1,
        urlPatterns = {"/index","/inscription","/inscriptionValidation","/connect", "", "/deconnect","/index.html", 
        "/pays", "/historique", "/paysAlphabet","/afficherRecherche", "/recherche", "/listePays",
        "/dernieresDestinations", "/nouveauPays", "/modifierPays", "/uploadFichier", "/downloadFile"})

public class ControllerServlet extends HttpServlet {

    @PersistenceContext(unitName = "ProjetPartenariatsPU")
    private EntityManager em;


    @EJB
    private PaysManager paysManager;

    @EJB
    private RubriqueManager rubriqueManager;

    @EJB
    private FichierUploadeManager fichierUploadeManager;

    @EJB
    private session.PaysFacade paysFacade;

    @EJB
    private session.RubriqueFacade rubriqueFacade ;

    @EJB
    private session.VilleFacade villeFacade ;

    @EJB
    private session.FichierUploadeFacade fichierUploadeFacade ;

    @EJB
    private session.ProfilFacade profilFacade ;

    @EJB
    private InscriptionManager inscriptionManager;
    
    @EJB
    private CompteFacade compteFacade;

    private String dossierFichiersUploades = "/fichiersUploades";

    /**
* Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
* @param request servlet request
* @param response servlet response
* @throws ServletException if a servlet-specific error occurs
* @throws IOException if an I/O error occurs
*/
    protected String processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userPath = request.getServletPath();
        String url = "";
        
        HttpSession session = request.getSession(false);

        if (request.getSession(false) != null && !request.getSession(false).isNew() ){
            request.getSession().invalidate();
            //request.getSession().setAttribute("profil", profilFacade.findAll().get(0));
            //session.setAttribute("idProfil", String.valueOf(profilFacade.findAll().get(0).getIdprofil()));
        }
        else{
            //this.createNewSession(request, "sessionLauria");
            //session.setAttribute("idProfil","1") ;
        }
          
        getServletContext().setAttribute("derniersPays", paysFacade.findAllOrderedById());


        if (userPath.equals("/index.html") || userPath.equals("/index") || userPath.equals("")) {  //Page d'accueil
            url = "/WEB-INF/compte_view/pagePrincipale.jsp";
        }

        else if (userPath.equals("/nouveauPays")){  //Créer un nouveau pays
            if (request.getSession(false) != null && !request.getSession(false).isNew() ){
                String nomPays = request.getParameter("nouveauPays");
                if (!nomPays.equals("")){
                    nomPays = partenariat.Util.InitialeMajuscule(nomPays);
                    List<Pays> pays = paysFacade.findByNom(nomPays);
                    int idPays;

                    if ((pays == null) || (pays.size() == 0) || (pays.get(0) == null)){
                        paysManager.createPays(nomPays, Integer.parseInt((String)session.getAttribute("idProfil")));
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
            String nomPays = paysFacade.findByIdpays(idPays).get(0).getNom();
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

            List<Ville> listeVilles = villeFacade.findByIdpays(idPays);

            List<FichierUploade> listeFichiers = fichierUploadeFacade.findByIdpays(idPays);

            request.setAttribute("nom",nomPays);
            request.setAttribute("idPays", request.getParameter("idPays"));
            getServletContext().setAttribute("titresRub", titresRubriquesTriees);
            getServletContext().setAttribute("rubriques", rubriquesPublieesTriees);
            getServletContext().setAttribute("villes", listeVilles);
            getServletContext().setAttribute("fichiers", listeFichiers);
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
            String type = (String)request.getAttribute("type");
            if (type.equals("rapide")){
                ;
            }
            else {
                ;
            }
        }
        else if (userPath.equals("/afficherRecherche")) {   //Affiche la page de recherche
            url = "/WEB-INF/compte_view/recherche.jsp";
        }

        else if (userPath.equals("/listePays")){    //Affiche la page de tous les pays créés
            getServletContext().setAttribute("tousPays", paysFacade.findAllOrderedByName());
            url = "/WEB-INF/compte_view/listePays.jsp";
        }

        else if (userPath.equals("/modifierPays")){ //Modification des rubriques d'un pays
            if (request.getSession(false) != null && !request.getSession(false).isNew() ){
                String action = request.getParameter("action");
                int idPays = Integer.parseInt(request.getParameter("idPays"));
                String nomPays = paysFacade.findByIdpays(idPays).get(0).getNom();

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
            ArrayList<Historique> listeHist = new ArrayList<Historique>();
            List<Rubrique> listeRub = rubriqueFacade.findOrderedByNameThenDate(idPays);

            for (int i = 0; i < listeRub.size(); i++){
                Historique hist = new Historique();
                Rubrique rub = listeRub.get(i);
                int idProfil = rub.getProfilIdprofil().getIdprofil();
                String nomProfil = profilFacade.findByIdprofil(idProfil).get(0).getNom();
                String prenomProfil = profilFacade.findByIdprofil(idProfil).get(0).getPrenom();
                hist.setRubrique(rub);
                hist.setNomProfil(nomProfil);
                hist.setPrenomProfil(prenomProfil);
                listeHist.add(hist);
            }

            request.setAttribute("nom",nomPays);
            request.setAttribute("idPays", request.getParameter("idPays"));
            getServletContext().setAttribute("historique", listeHist);
            url = "/WEB-INF/compte_view/historique.jsp";
        }

        else if (userPath.equals("/uploadFichier")){
            if (request.getSession(false) != null && !request.getSession(false).isNew() ){
                int idProfil = profilFacade.findAll().get(0).getIdprofil(); //FIXME l'id de l'utilisateur connecté
                int idPays = Integer.parseInt((String)request.getSession().getAttribute("idPays"));
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
 
        else if (userPath.equals("/inscription")) { //inscription request

            // userPath = "inscription";
            url = "/WEB-INF/compte_view/inscription.jsp";
        }
        
        else if (userPath.equals("/deconnect")){ //deconnexion
            request.getSession().invalidate();
            userPath = "/pagePrincipale";
            url = "/WEB-INF/compte_view" +userPath + ".jsp";
        }
        return url;
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
        String url = processRequest(request, response);
        try {
            if (request.getSession(false) != null && !request.getSession(false).isNew() ){
                request.setAttribute("connecte", "true");
            }
            else {
                request.setAttribute("connecte", "false");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //out.close();
        }
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
                    request.setAttribute("connecte", "true");
                    createNewSession(request, name);
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
            else {
                request.setAttribute("connecte", "false");
            }

        }
        else {
            url = processRequest(request, response);
        }
        try {
            if (request.getSession(false) != null && !request.getSession(false).isNew() ){
                request.setAttribute("connecte", "true");
            }
            else {
                request.setAttribute("connecte", "false");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

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
}
