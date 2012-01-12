/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package controller;

import entity.FichierUploade;
import entity.Pays;
import entity.Rubrique;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import partenariat.Historique;
import partenariat.PaysManager;
import partenariat.RubriqueManager;

import session.InscriptionManager;
import validator.InputValidator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import partenariat.FichierManager;
/**
*
* @author fingon
*/
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/index","/inscription","/inscriptionValidation","/connect", "", "/deconnect","/index.html", "/pays", "/historique",
        "/paysAlphabet","/afficherRecherche", "/recherche", "/listePays", "/dernieresDestinations", "/nouveauPays", "/modifierPays", "/uploadFichier"})
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
    private FichierManager fichierManager;

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
        PrintWriter out = response.getWriter();
        String userPath = request.getServletPath();
        String url = "";

        if (request.getAttribute("connecte") == null) // default : not connected
            request.setAttribute("connecte", "false");

        getServletContext().setAttribute("derniersPays", paysFacade.findAllOrderedById());
  /*     
        if (request.getSession(false) == null){ //connexion check
            request.setAttribute("connecte", "false");
            
            //System.out.println("false");
        }
        else {
            request.setAttribute("connecte", "true");
            System.out.println("true");
        }*/

        if (userPath.equals("/inscription")) { //inscription request

           // userPath = "inscription";
            url = "/WEB-INF/compte_view/inscription.jsp";
        }

        else if (userPath.equals("/index.html") || userPath.equals("/index") || userPath.equals("")) {  //Page d'accueil
            url = "/WEB-INF/compte_view/pagePrincipale.jsp";
        }

        else if (userPath.equals("/nouveauPays")){  //Créer un nouveau pays
            String nomPays = request.getParameter("nouveauPays");
            if (!nomPays.equals("")){
                nomPays = partenariat.Util.InitialeMajuscule(nomPays);
                List<Pays> pays = paysFacade.findByNom(nomPays);
                int idPays;

                if ((pays == null) || (pays.size() == 0) || (pays.get(0) == null)){
                    paysManager.createPays(nomPays);
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

            List<FichierUploade> liste = fichierUploadeFacade.findByIdpays(idPays);

            request.setAttribute("nom",nomPays);
            request.setAttribute("idPays", request.getParameter("idPays"));
            getServletContext().setAttribute("titresRub", titresRubriquesTriees);
            getServletContext().setAttribute("rubriques", rubriquesPublieesTriees);
            getServletContext().setAttribute("fichiers", liste);
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
            
            request.setAttribute("nom",nomPays);
            request.setAttribute("idPays", request.getParameter("idPays"));
            url= "/pays";

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
            int idPays = Integer.parseInt(request.getParameter("idPays"));
            int idProfil = profilFacade.findAll().get(0).getIdprofil(); //FIXME l'id de l'utilisateur connecté
            try {
                // Create a factory for disk-based file items
                FileItemFactory factory = new DiskFileItemFactory();
                // Create a new file upload handler
                ServletFileUpload upload = new ServletFileUpload(factory);
                // Parse the request
                List items;
                items = upload.parseRequest(request);
                // Process the uploaded items
                int idFichier = 0;
                Iterator iter = items.iterator();
                FileItem item = (FileItem) iter.next();
                String urlFichier = "/fichiersUploades/" + idFichier + ".jpg";
                String context = getServletContext().getRealPath(urlFichier);
                File uploadedFile = new File(context);
                item.write(uploadedFile);
                //On crée la photo
                //fichierManager.createFichier(urlFichier, idPays, idProfil, uploadedFile.length());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        else if (userPath.equals("/deconnect")){ //deconnexion
            request.getSession().invalidate();
            userPath = "/pagePrincipale";
            url = "/WEB-INF/compte_view" +userPath + ".jsp";
        }


        //String url = "/WEB-INF/compte_view/" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            out.close();
        }
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
                url = userPath +".jsp";
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
                    request.getSession(); // create session
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

            url = "index.html";
            userPath = "index";
            if (ok){
                request.setAttribute("connecte", "true");
                request.getSession(); // create session

            }
            else {
                request.setAttribute("connecte", "false");
            }

        }
        else {
            processRequest(request, response);
        }


        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //processRequest(request, response);
    }

    /**
* Returns a short description of the servlet.
* @return a String containing servlet description
*/
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
