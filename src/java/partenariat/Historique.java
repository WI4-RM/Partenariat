/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package partenariat;

import entity.Rubrique;

/**
 *
 * @author lolo
 */
public class Historique {

    private Rubrique rubrique;
    private String nomProfil;
    private String prenomProfil;
    
    public String getNomProfil() {
        return nomProfil;
    }

    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }

    public String getPrenomProfil() {
        return prenomProfil;
    }

    public void setPrenomProfil(String prenomProfil) {
        this.prenomProfil = prenomProfil;
    }
    
    public Rubrique getRubrique() {
        return rubrique;
    }

    public void setRubrique(Rubrique rubrique) {
        this.rubrique = rubrique;
    }


}
