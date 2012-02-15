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
import session.InscriptionManager;
import validator.InputValidator;

/**
 *
 * @author fingon
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/forgottenPasswordForm",
    "/resetPassword", "/modifyPassword", "/newPasswordSubmission"})
public class AccountServlet extends HttpServlet {

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

        String userPath = request.getServletPath();
        String url = "/WEB-INF/";

        if (userPath.equals("/forgottenPasswordForm")) {
            url += "compte_view/forgottenPassword.jsp";
        } else if (userPath.equals("/modifyPassword")) {
            if (ControllerServlet.isConnected(request))
              url += "compte_view/modifyPassword.jsp";
            else
                url += "compte_view/pagePrincipale.jsp";
        }
        request.getRequestDispatcher(url).forward(request, response);
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
        String url = "WEB-INF/compte_view/";

        if (userPath.equals("/resetPassword")) {
            String email = request.getParameter("email");
            if (!InputValidator.checkEmail(email)) {
                response.sendError(400, "Cet email n'est pas un email de l'EMSE !");
            } else {
                inscriptionManager.resetPassword(email);
            }

            url += "pagePrincipale.jsp";
        } else if (userPath.equals("/newPasswordSubmission")) {
            if (ControllerServlet.isConnected(request)) { //connection check
                String Oldpassword = request.getParameter("old_password");
                String new_password1 = request.getParameter("new_password");
                String new_password2 = request.getParameter("new_password2");

                if (!InputValidator.checkPassword(Oldpassword) //test inputs
                        || !InputValidator.checkPassword(new_password1)
                        || !InputValidator.checkPassword(new_password2)
                        || !new_password1.equals(new_password2)) {
                    userPath = "index";
                    url += "modifyPassword.jsp";
                    request.getRequestDispatcher(url).forward(request, response);
                    return;
                }
                String email = (String) request.getSession(false).getAttribute("email");

                boolean succes = inscriptionManager.changePassword(email, Oldpassword, new_password2);
                if (succes) {
                    url += "pagePrincipale.jsp";
                } else {
                    url += "modifyPassword.jsp";
                }



            }
        }

        request.getRequestDispatcher(url).forward(request, response);

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
}
