/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.io.PrintWriter;
>>>>>>> 990410fcd6110b8fe093ac6832073da9e0771216
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import session.PaysFacade;
import entity.Pays;
import session.PaysFacadeLocal;

import session.InscriptionManager;
import validator.InputValidator;



/**
 *
 * @author fingon
 */
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
<<<<<<< HEAD
        urlPatterns = {"/inscription",
                        "/inscriptionValidation",
                        "/pays",
                        "/index.html",
                        "/deconnexion"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private PaysFacadeLocal paysFacade;

    /*@Override
    public void init() throws ServletException {

        // store category list in servlet context
        //getServletContext().setAttribute("initialePays", paysFacade.findPaysByFirstLetter());
        getServletContext().setAttribute("allPays", paysFacade.findAll());
    }*/

=======
        urlPatterns = {"/index","/inscription","/inscriptionValidation","/connect", "", "/deconnect"})
public class ControllerServlet extends HttpServlet {

    @EJB
    private InscriptionManager inscriptionManager;
    
    
>>>>>>> 990410fcd6110b8fe093ac6832073da9e0771216
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
        System.out.println(userPath);
        //if (partenariat.Util.isConnected(request, response)){
            //request.setAttribute("connecte", "true");
        //}
        //else {
            request.setAttribute("connecte", "false");
        //}

        
        try {
            if (userPath.equals("/main")) {
                String url = "/WEB-INF/compte_view/pageAccueil";
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (userPath.equals("/index.html")) {
                String url = "/WEB-INF/compte_view/pagePrincipale.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (userPath.equals("/deconnexion")) {
                request.setAttribute("connecte", "false");
                request.setAttribute("url", "index.html");
                String urlRedir = "/WEB-INF/fonctions/redirection.jsp";
                request.getRequestDispatcher(urlRedir).forward(request, response);
            }
            if (userPath.equals("/pays")) {
                //request.setAttribute("poutou", "poutou");
                //request.getSession().setAttribute("poutou","poutou");
                String url = "/WEB-INF/compte_view/" + userPath + ".jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (userPath.equals("/paysAlphabet")) {
                String lettre = request.getParameter("lettre");
                request.setAttribute("lettre",lettre);
                String url = "WEB-INF/fonctions/cataloguePays.jsp";
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (userPath.equals("/recherche")) {
                String type = (String)request.getAttribute("type");
                if (type.equals("rapide")){
                    ;
                }
                else {
                    ;
                }
            }
            if (userPath.equals("/afficherRecherche")) {
                String url = "/WEB-INF/compte_view/recherche.jsp";
                request.getRequestDispatcher(url).forward(request, response);
             }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        /*HttpSession session = request.getSession();

        String userPath = request.getServletPath();
        String url = "";
        
        if (userPath.equals("/inscription")) { //inscription request
            
           // userPath = "inscription";
            url = "/WEB-INF/compte_view" + userPath + ".jsp";
        }
        
        else if (userPath.equals("/index") || userPath.equals("")){ //index 
            if (request.getSession(false) == null){ //connexion check
                userPath = "index";
                url = userPath + ".jsp";
            }
            else {
                userPath = "/indexCo";
                url = "/WEB-INF" + userPath + ".jsp";
            }
                
        }
        else if (userPath.equals("/deconnect")){ //deconnexion
            request.getSession().invalidate();
            userPath = "index";
            url = userPath + ".jsp";
        }
<<<<<<< HEAD
        if (userPath.equals("pays")) {
            request.setAttribute("poutou", "poutou");
            request.getSession().setAttribute("key","value");
            userPath = "pays";
        }
        String url = "/WEB-INF/compte_view/" + userPath + ".jsp";
=======
        
        
        //String url = "/WEB-INF/compte_view/" + userPath + ".jsp";
        
>>>>>>> 990410fcd6110b8fe093ac6832073da9e0771216
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
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
       /*
        HttpSession session = request.getSession();
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
                if (!(InputValidator.checkNames(name)) ||  !(InputValidator.checkNames(username))){
                    allInputsOk = false;
                    //response.sendError(400, "name or username error");
                }
                if (!InputValidator.checkPassword(password)){
                    allInputsOk = false;
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
                    isOK =  inscriptionManager.createUser(name, username, email, password,  Integer.decode(yearS));
              
                if (isOK)
                    userPath = "confirmation";
                else
                    userPath ="errorSuscribe";
                
                url  += "compte_view/" + userPath + ".jsp";
        }
        else if (userPath.equals("/connect")){
            String username = request.getParameter("login");
            String password = request.getParameter("password");
            
            //todo : clean input
            
            boolean ok = inscriptionManager.connect(username, password);
            
            if (ok){
                userPath = "indexCo";
                url  += userPath + ".jsp";
                
                request.getSession(); // create session

            }
            else {
                userPath = "index";
                url = userPath +".jsp";
            }
          
        }

        if (userPath.equals("/pays")) {
            session.setAttribute("poutou", "poutou");
            userPath = "pays";
        }
        


        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }*/
        processRequest(request, response);
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
