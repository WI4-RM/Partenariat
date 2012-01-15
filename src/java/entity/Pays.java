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
@Table(name = "pays")
@NamedQueries({
    @NamedQuery(name = "Pays.findAll", query = "SELECT p FROM Pays p"),
    @NamedQuery(name = "Pays.findByIdpays", query = "SELECT p FROM Pays p WHERE p.idpays = :idpays"),
    @NamedQuery(name = "Pays.findByNom", query = "SELECT p FROM Pays p WHERE p.nom = :nom"),
    @NamedQuery(name = "Pays.findAllOrderedByName", query = "SELECT p FROM Pays p ORDER BY p.nom"),
    @NamedQuery(name = "Pays.findAllOrderedById", query = "SELECT p FROM Pays p ORDER BY p.idpays DESC"),
    @NamedQuery(name = "Pays.findByFirstLetter", query = "SELECT p FROM Pays p WHERE p.nom LIKE :lettre ORDER BY p.nom")})
public class Pays implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpays")
    private Integer idpays;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paysIdpays")
    private Collection<Rubrique> rubriqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paysIdpays")
    private Collection<Ville> villeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paysIdpays")
    private Collection<FichierUploade> fichierUploadeCollection;

    public Pays() {
    }

    public Pays(Integer idpays) {
        this.idpays = idpays;
    }

    public Pays(Integer idpays, String nom) {
        this.idpays = idpays;
        this.nom = nom;
    }

    public Integer getIdpays() {
        return idpays;
    }

    public void setIdpays(Integer idpays) {
        this.idpays = idpays;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Rubrique> getRubriqueCollection() {
        return rubriqueCollection;
    }

    public void setRubriqueCollection(Collection<Rubrique> rubriqueCollection) {
        this.rubriqueCollection = rubriqueCollection;
    }

    public Collection<Ville> getVilleCollection() {
        return villeCollection;
    }

    public void setVilleCollection(Collection<Ville> villeCollection) {
        this.villeCollection = villeCollection;
    }

    public Collection<FichierUploade> getFichierUploadeCollection() {
        return fichierUploadeCollection;
    }

    public void setFichierUploadeCollection(Collection<FichierUploade> fichierUploadeCollection) {
        this.fichierUploadeCollection = fichierUploadeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpays != null ? idpays.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.idpays == null && other.idpays != null) || (this.idpays != null && !this.idpays.equals(other.idpays))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Pays[idpays=" + idpays + "]";
    }

}
