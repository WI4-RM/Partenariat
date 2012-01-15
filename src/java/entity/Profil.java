/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
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
    @NamedQuery(name = "Profil.findByPromo", query = "SELECT p FROM Profil p WHERE p.promo = :promo")})
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
    private Collection<Rubrique> rubriqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private Collection<Compte> compteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profilIdprofil")
    private Collection<FichierUploade> fichierUploadeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "profil")
    private Collection<Destination> destinationCollection;

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

    public Collection<Rubrique> getRubriqueCollection() {
        return rubriqueCollection;
    }

    public void setRubriqueCollection(Collection<Rubrique> rubriqueCollection) {
        this.rubriqueCollection = rubriqueCollection;
    }

    public Collection<Compte> getCompteCollection() {
        return compteCollection;
    }

    public void setCompteCollection(Collection<Compte> compteCollection) {
        this.compteCollection = compteCollection;
    }

    public Collection<FichierUploade> getFichierUploadeCollection() {
        return fichierUploadeCollection;
    }

    public void setFichierUploadeCollection(Collection<FichierUploade> fichierUploadeCollection) {
        this.fichierUploadeCollection = fichierUploadeCollection;
    }

    public Collection<Destination> getDestinationCollection() {
        return destinationCollection;
    }

    public void setDestinationCollection(Collection<Destination> destinationCollection) {
        this.destinationCollection = destinationCollection;
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
