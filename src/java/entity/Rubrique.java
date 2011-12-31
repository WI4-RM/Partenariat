/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fingon
 */
@Entity
@Table(name = "rubrique")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rubrique.findAll", query = "SELECT r FROM Rubrique r"),
    @NamedQuery(name = "Rubrique.findByIdrubrique", query = "SELECT r FROM Rubrique r WHERE r.idrubrique = :idrubrique"),
    @NamedQuery(name = "Rubrique.findByNom", query = "SELECT r FROM Rubrique r WHERE r.nom = :nom"),
    @NamedQuery(name = "Rubrique.findByDate", query = "SELECT r FROM Rubrique r WHERE r.date = :date"),
    //@NamedQuery(name = "Rubrique.findByIdPays", query = "SELECT r FROM Rubrique r WHERE r.paysIdpays = :idPays ORDER BY r.serialVersionUID DESC")})
    @NamedQuery(name = "Rubrique.findByIdPays", query = "SELECT r FROM Rubrique r WHERE r.paysIdpays.idpays = :idPays")})
public class Rubrique implements Serializable {
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "idrubrique")
    private Integer idrubrique;
    @Size(max = 45)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "texte")
    private String texte;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil")
    @ManyToOne(optional = false)
    private Profil profilIdprofil;
    @JoinColumn(name = "pays_idpays", referencedColumnName = "idpays")
    @ManyToOne(optional = false)
    private Pays paysIdpays;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubriqueIdrubrique")
    private List<FichierUploade> fichierUploadeList;

    public Rubrique() {
    }

    public Rubrique(Integer idrubrique) {
        this.idrubrique = idrubrique;
    }

    public Integer getIdrubrique() {
        return idrubrique;
    }

    public void setIdrubrique(Integer idrubrique) {
        this.idrubrique = idrubrique;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Profil getProfilIdprofil() {
        return profilIdprofil;
    }

    public void setProfilIdprofil(Profil profilIdprofil) {
        this.profilIdprofil = profilIdprofil;
    }

    public Pays getPaysIdpays() {
        return paysIdpays;
    }

    public void setPaysIdpays(Pays paysIdpays) {
        this.paysIdpays = paysIdpays;
    }

    @XmlTransient
    public List<FichierUploade> getFichierUploadeList() {
        return fichierUploadeList;
    }

    public void setFichierUploadeList(List<FichierUploade> fichierUploadeList) {
        this.fichierUploadeList = fichierUploadeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrubrique != null ? idrubrique.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rubrique)) {
            return false;
        }
        Rubrique other = (Rubrique) object;
        if ((this.idrubrique == null && other.idrubrique != null) || (this.idrubrique != null && !this.idrubrique.equals(other.idrubrique))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rubrique[ idrubrique=" + idrubrique + " ]";
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
