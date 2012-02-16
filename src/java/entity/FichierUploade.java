/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fingon
 */
@Entity
@Table(name = "fichier_uploade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FichierUploade.findAll", query = "SELECT f FROM FichierUploade f"),
    @NamedQuery(name = "FichierUploade.findByIdfichierUploade", query = "SELECT f FROM FichierUploade f WHERE f.idfichierUploade = :idfichierUploade"),
    @NamedQuery(name = "FichierUploade.findByNom", query = "SELECT f FROM FichierUploade f WHERE f.nom = :nom"),
    @NamedQuery(name = "FichierUploade.findByTaille", query = "SELECT f FROM FichierUploade f WHERE f.taille = :taille"),
        @NamedQuery(name = "FichierUploade.findByIdpays", query = "SELECT f FROM FichierUploade f WHERE f.paysIdpays.idpays = :idPays"),
    @NamedQuery(name = "FichierUploade.findByDate", query = "SELECT f FROM FichierUploade f WHERE f.date = :date")})
public class FichierUploade implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "idfichier_uploade")
    private Integer idfichierUploade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "taille")
    private int taille;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil")
    @ManyToOne(optional = false)
    private Profil profilIdprofil;
    @JoinColumn(name = "pays_idpays", referencedColumnName = "idpays")
    @ManyToOne(optional = false)
    private Pays paysIdpays;

    public FichierUploade() {
    }

    public FichierUploade(Integer idfichierUploade) {
        this.idfichierUploade = idfichierUploade;
    }

    public FichierUploade(Integer idfichierUploade, String nom, int taille) {
        this.idfichierUploade = idfichierUploade;
        this.nom = nom;
        this.taille = taille;
    }

    public Integer getIdfichierUploade() {
        return idfichierUploade;
    }

    public void setIdfichierUploade(Integer idfichierUploade) {
        this.idfichierUploade = idfichierUploade;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfichierUploade != null ? idfichierUploade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FichierUploade)) {
            return false;
        }
        FichierUploade other = (FichierUploade) object;
        if ((this.idfichierUploade == null && other.idfichierUploade != null) || (this.idfichierUploade != null && !this.idfichierUploade.equals(other.idfichierUploade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.FichierUploade[ idfichierUploade=" + idfichierUploade + " ]";
    }
    
}