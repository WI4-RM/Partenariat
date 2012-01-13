/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lolo
 */
public class Util {

    /**
     * Cette fonction redirige la page vers une autre adresse. Souvent, elle est utilisée pour la déconnection.
     * @param url
     * @param out
     */
    static public boolean isConnected(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try{
            HttpSession session = req.getSession(true);

            String login = (String) session.getAttribute("login");		//On récupère le login et mdp qui ont été stockés
            String cryptPass = (String) session.getAttribute("cryptPass");

            if (login != null && cryptPass != null ){	//Si l'un des deux est nul, la session n'est pas active
                if (!login.equals("") && !cryptPass.equals("")){
                    boolean out = false;
                    //On vérifie ensuite si le mdp correspond bien au login : cela évite à quelqu'un de pouvoir se
                    //faire passer pour l'admin
                    try {

                    } catch (Exception e){e.printStackTrace();}

                    if (out == true){
                        return true;
                    }
                    else {
                        req.getRequestDispatcher("login.jsp").forward(req, resp);
                        return false;
                    }
                }else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return false;
                }
            }else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
                return false;
            }
        } catch (Exception e){e.printStackTrace();}
        return false;
    }

    static public boolean isAdmin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return false;
    }

    static public String InitialeMajuscule(String mot){
        if ((mot != null) && (mot.length() > 0)){
            String premiereLettre = mot.substring(0, 1);
            String leReste = mot.substring(1);
            return premiereLettre.toUpperCase() + leReste.toLowerCase();
        }
        return mot;
    }
}
