/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.*;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CompteFacade;
import session.DestinationFacade;
import session.InscriptionManager;
import session.PaysFacade;
import session.ProfilFacade;
import session.RubriqueFacade;
import session.VilleFacade;
import validator.InputValidator;

/**
 *Manages administrator request
 * @author fingon
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/administration", "/connectAdmin",
                                                    "/viewAccount",
                                                    "/viewPays","/delPays",
                                                       "/setNewAdmin","/deleteAccount"})
public class AdminServlet extends HttpServlet {

    @EJB
    private InscriptionManager inscriptionManager;
    
    @EJB
    private CompteFacade compteFacade;
    
    @EJB
    private PaysFacade paysFacade;
    
    @EJB
    private ProfilFacade profilFacade;
    
    @EJB
    private VilleFacade villeFacade;
    
    @EJB
    private DestinationFacade destinationFacade;
    
    @EJB 
    private RubriqueFacade rubriqueFacade;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String userPath = request.getServletPath();
        String url = "WEB-INF/admin/";

        if (userPath.equals("/administration")) {//redirection vers le tableau de connection
            url += "connectionPanel.jsp";
        }
        else if (!AdminServlet.isAdminConnected(request))//pour toutes les autres possibilites, l'administrateur doit etre authentifié
        {
            url += "connectionPanel.jsp";
        }
        else if (userPath.equals("/viewAccount")){ //regarder les comptes utilisateurs
            url += "accounts.jsp";

            List<Compte> comptes = compteFacade.findAll();
            //Iterator<Compte> it = comptes.iterator();
           // while (it)
            
            request.getSession(false).setAttribute("comptes", comptes);
            
            
            List<Profil> profils = profilFacade.findAll();
            
            request.getSession().setAttribute("profils", profils);
            
        }
        else if (userPath.equals("/viewPays")){//regarder la liste des pays
            url += "paysView.jsp";
            List<Pays> pays = paysFacade.findAll();
            request.getSession(false).setAttribute("paysList", pays);
            
        }
        else if (userPath.equals("/delPays")){ //destruction d'un pays
            Object o = request.getParameter("idDelPays");
            Integer id = Integer.parseInt(request.getParameter("idDelPays"));
            
            if (id != null){
                List<Pays> p = paysFacade.findByIdpays(id);
                if (p.size() > 0){
                   // paysFacade.remove(p.get(0));
                    List<Ville> lVille = p.get(0).getVilleList();
                    for (int i=0; i< lVille.size();i++){
                        Ville ville = lVille.get(i);
                        List<Destination> lDest = ville.getDestinationList();
                        for (int d  = 0; d < lDest.size(); d++)
                            destinationFacade.remove(lDest.get(d));
                            //destinationFacade.deleteDestination( lDest.get(d).getDestinationPK());
                        

                        
                        villeFacade.deleteVille(ville.getIdVille());
                                              
                    }
                    Pays pays = paysFacade.findByIdpays(id).get(0);
                    List<Rubrique> lRub = pays.getRubriqueList();
                    for (int i=0;i<lRub.size() ; i++)
                        rubriqueFacade.remove(lRub.get(i));
                        //rubriqueFacade.deleteRubrique(lRub.get(i));
                    
                   
                    paysFacade.remove(pays);
                   // paysFacade.deletePays(id);
                }
            }
            url += "paysView.jsp";
            List<Pays> pays = paysFacade.findAll();
            request.getSession(false).setAttribute("paysList", pays);
            userPath = "/viewPays";

            
        } else if (userPath.equals("/deleteAccount")){//destruction d'un compte
            Integer id = Integer.parseInt(request.getParameter("idCompte"));
            if (id == null)
                throw new NullPointerException("id of compte is nul");
            
            List<Compte> lCompte = compteFacade.findByIdCompte(id);
            if (lCompte.isEmpty())
                throw new NullPointerException("aucun compte trouvé");
            
            Profil profil = lCompte.get(0).getProfilIdprofil();
            System.out.println(profil.toString());

            compteFacade.remove(lCompte.get(0));
            profilFacade.remove(profil);
            
            //url += "viewAccount";
            userPath = "/viewAccount";
            
        } else if (userPath.equals("/setNewAdmin")){ // definition d'un nouvel administrateur
            Integer id = Integer.parseInt(request.getParameter("idCompte"));
            if (id == null)
                throw new NullPointerException("id of compte is nul");
            List<Compte> lCompte = compteFacade.findByIdCompte(id);
            if (lCompte.isEmpty())
                throw new NullPointerException("aucun compte trouvé");
            
            lCompte.get(0).setIsAdministrator(true);
            compteFacade.edit(lCompte.get(0));
            
            userPath = "/viewAccount";
        }


        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        String url = "WEB-INF/admin/";

        if (userPath.equals("/connectAdmin")) {
            String username = request.getParameter("login");
            String password = request.getParameter("password");
            boolean ok = false; // default : connection is not ok

            if (InputValidator.checkEmail(username)) {
                ok = inscriptionManager.connectAdmin(username, password);

            }


            userPath = "connectionPanel";
            //userPath = "index";
            if (ok) {
                userPath = "index";
                this.createNewAdminSession(request, username);

            }
            userPath += ".jsp";
            url += userPath;
        }
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
        }


    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * setup admin parameters when authentification is succesfull
     * @param request
     * @param email 
     */
    private void createNewAdminSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("isAdmin", "true");
        
        
        Compte c = compteFacade.findTheCompteByEmail(email);
        Integer idProfil = c.getProfilIdprofil().getIdprofil();

        session.setAttribute("idProfil", idProfil);
        session.setAttribute("nom", profilFacade.findByIdprofil(idProfil).get(0).getNom());
        session.setAttribute("prenom", profilFacade.findByIdprofil(idProfil).get(0).getPrenom());       
        
        
    }
    /**
     * check if the user is administrator is connected
     * @param request
     * @return true if the user is connected & is an administrator 
     */
    public static boolean isAdminConnected(HttpServletRequest request){
        return ControllerServlet.isConnected(request) &&
                request.getSession(false).getAttribute("isAdmin") != null;
    }
}
