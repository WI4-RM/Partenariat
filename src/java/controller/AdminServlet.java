/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.InscriptionManager;
import validator.InputValidator;


    /**
 *
 * @author fingon
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/administration","/connectAdmin"})
public class AdminServlet extends HttpServlet {
    
    @EJB
    private InscriptionManager inscriptionManager;

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
        String userPath= request.getServletPath();
        String url = "WEB-INF/admin/";
        
        if (userPath.equals("/administration")){
            url+="connectionPanel.jsp";
        }
        

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
        
        if (userPath.equals("/connectAdmin")){
            String username = request.getParameter("login");
            String password = request.getParameter("password");
            boolean ok = false; // default : connection is not ok
            
            if (InputValidator.checkEmail(username))
                ok = inscriptionManager.connectAdmin(username, password);

            
            userPath="connectionPanel";
            //userPath = "index";
            if (ok){
                userPath = "index";
                this.createNewSession(request, username);

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
    
        private void createNewSession(HttpServletRequest request, String name) {
        HttpSession session = request.getSession(true);
        session.setAttribute("nom", name);
        //Cookie c = new Cookie("nom", name);
        
        
    }
}
