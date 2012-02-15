/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author lolo
*/
@Entity
@Table(name = "profil")
@NamedQueries({
    @NamedQuery(name = "Profil.findAll", query = "SELECT p FROM Profil p"),
    @NamedQuery(name = "Profil.findByIdprofil", query = "SELECT p FROM Profil p WHERE p.idprofil = :idprofil"),
    @NamedQuery(name = "Profil.findByNom", query = "SELECT p FROM Profil p WHERE p.nom = :nom"),
    @NamedQuery(name = "Profil.findByPrenom", query = "SELECT p FROM Profil p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Profil.findByPromo", query = "SELECT p FROM Profil p WHERE p.promo = :promo"),
    @NamedQuery(name = "Profil.changeNom", query="UPDATE Profil p SET p.nom = :nom WHERE p.idprofil = :idprofil"),
    @NamedQuery(name = "Profil.changePrenom", query="UPDATE Profil p SET p.prenom = :prenom WHERE p.idprofil = :idprofil"),
    @NamedQuery(name = "Profil.changePromo", query="UPDATE Profil p SET p.promo = :promo WHERE p.idprofil = :idprofil")})

public class Profil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofil")
    private Integer idprofil;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "promo")
    private int promo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private List<Rubrique> rubriqueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private List<Compte> compteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private List<FichierUploade> fichierUploadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private List<Destination> destinationList;

    public Profil() {
    }

    public Profil(Integer idprofil) {
        this.idprofil = idprofil;
    }

    public Profil(Integer idprofil, String nom, String prenom, int promo) {
        this.idprofil = idprofil;
        this.nom = nom;
        this.prenom = prenom;
        this.promo = promo;
    }

    public Integer getIdprofil() {
        return idprofil;
    }

    public void setIdprofil(Integer idprofil) {
        this.idprofil = idprofil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPromo() {
        return promo;
    }

    public void setPromo(int promo) {
        this.promo = promo;
    }

    public List<Rubrique> getRubriqueList() {
        return rubriqueList;
    }

    public void setRubriqueList(List<Rubrique> rubriqueList) {
        this.rubriqueList = rubriqueList;
    }

    public List<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(List<Compte> compteList) {
        this.compteList = compteList;
    }

    public List<FichierUploade> getFichierUploadeList() {
        return fichierUploadeList;
    }

    public void setFichierUploadeList(List<FichierUploade> fichierUploadeList) {
        this.fichierUploadeList = fichierUploadeList;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprofil != null ? idprofil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profil)) {
            return false;
        }
        Profil other = (Profil) object;
        if ((this.idprofil == null && other.idprofil != null) || (this.idprofil != null && !this.idprofil.equals(other.idprofil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Profil[idprofil=" + idprofil + "]";
    }

}