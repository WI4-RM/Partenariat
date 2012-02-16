/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Compte;
import entity.Pays;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CompteFacade;
import session.InscriptionManager;
import session.PaysFacade;
import validator.InputValidator;

/**
 *
 * @author fingon
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/administration", "/connectAdmin","/viewAccount",
                                                    "/viewPays","/delPays"})
public class AdminServlet extends HttpServlet {

    @EJB
    private InscriptionManager inscriptionManager;
    
    @EJB
    private CompteFacade compteFacade;
    
    @EJB
    private PaysFacade paysFacade;

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

        if (userPath.equals("/administration")) {
            url += "connectionPanel.jsp";
        }
        else if (!AdminServlet.isAdminConnected(request))
        {
            url += "connectionPanel.jsp";
        }
        else if (userPath.equals("/viewAccount")){
            url += "accounts.jsp";
            List<Compte> comptes = compteFacade.findAll();
            //Iterator<Compte> it = comptes.iterator();
           // while (it)
            
            request.getSession(false).setAttribute("comptes", comptes);
            
        }
        else if (userPath.equals("/viewPays")){
            url += "paysView.jsp";
            List<Pays> pays = paysFacade.findAll();
            request.getSession(false).setAttribute("paysList", pays);
            
        }
        else if (userPath.equals("/delPays")){
            Object o = request.getParameter("idDelPays");
            Integer id = Integer.parseInt(request.getParameter("idDelPays"));
            
            if (id != null){
                List<Pays> p = paysFacade.findByIdpays(id);
                if (p.size() > 0){
                    paysFacade.remove(p.get(0));
                }
            }
            url += "paysView.jsp";
            List<Pays> pays = paysFacade.findAll();
            request.getSession(false).setAttribute("paysList", pays);
            userPath = "/viewPays";

            
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

    private void createNewAdminSession(HttpServletRequest request, String email) {
        HttpSession session = request.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("isAdmin", "true");
    }
    
    public static boolean isAdminConnected(HttpServletRequest request){
        return ControllerServlet.isConnected(request) &&
                request.getSession(false).getAttribute("isAdmin") != null;
    }
}
