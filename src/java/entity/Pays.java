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
import javax.validation.constraints.NotNull;

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "X")
    private float x;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Y")
    private float y;
    @Basic(optional = false)
    @NotNull
    @Column(name = "zoom_level")
    private int zoomLevel;
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
    private List<Rubrique> rubriqueList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paysIdpays")
    private List<FichierUploade> fichierUploadeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paysIdpays")
    private List<Ville> villeList;

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

    public List<Rubrique> getRubriqueList() {
        return rubriqueList;
    }

    public void setRubriqueList(List<Rubrique> rubriqueList) {
        this.rubriqueList = rubriqueList;
    }

    public List<FichierUploade> getFichierUploadeList() {
        return fichierUploadeList;
    }

    public void setFichierUploadeList(List<FichierUploade> fichierUploadeList) {
        this.fichierUploadeList = fichierUploadeList;
    }

    public List<Ville> getVilleList() {
        return villeList;
    }

    public void setVilleList(List<Ville> villeList) {
        this.villeList = villeList;
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

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(int zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

}