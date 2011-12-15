/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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

/**
 *
 * @author fingon
 */
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
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
        
        if (userPath.equals("inscription")) {
            
            userPath = "inscription";
            
        }
        if (userPath.equals("pays")) {
            request.setAttribute("poutou", "poutou");
            request.getSession().setAttribute("key","value");
            userPath = "pays";
        }
        String url = "/WEB-INF/compte_view/" + userPath + ".jsp";
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
        
        if (userPath.equals("/inscriptionValidation")) {
            
                String name = request.getParameter("name");
                String username = request.getParameter("username");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String password2 = request.getParameter("password2");
                String day = request.getParameter("cityRegion");
                String month = request.getParameter("creditcard");
                String year = request.getParameter("creditcard");               
                String phone = request.getParameter("phone");
                
                userPath = "confirmation";
        }

        if (userPath.equals("/pays")) {
            session.setAttribute("poutou", "poutou");
            userPath = "pays";
        }
        
        String url = "/WEB-INF/compte_view/" + userPath + ".jsp";

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
