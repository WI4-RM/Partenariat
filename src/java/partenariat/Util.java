/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package partenariat;

/**
*
* @author lolo
*/
public class Util {



    static public String InitialeMajuscule(String mot){
        if ((mot != null) && (mot.length() > 0)){
            String premiereLettre = mot.substring(0, 1);
            String leReste = mot.substring(1);
            return premiereLettre.toUpperCase() + leReste.toLowerCase();
        }
        return mot;
    }

    static public String verificationTailleString(String chaine, int tailleMax){
        int lg = chaine.length();
        if ((chaine != null) && (lg > 0)){
            if (lg > tailleMax)
                chaine = chaine.substring(0, tailleMax);
        }
        return chaine;
    }
}