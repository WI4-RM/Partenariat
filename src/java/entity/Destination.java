/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fingon
 */
@Entity
@Table(name = "destination")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByProfilIdprofil", query = "SELECT d FROM Destination d WHERE d.destinationPK.profilIdprofil = :profilIdprofil"),
    @NamedQuery(name = "Destination.findByPaysIdpays", query = "SELECT d FROM Destination d WHERE d.destinationPK.paysIdpays = :paysIdpays"),
    @NamedQuery(name = "Destination.findByX", query = "SELECT d FROM Destination d WHERE d.x = :x"),
    @NamedQuery(name = "Destination.findByY", query = "SELECT d FROM Destination d WHERE d.y = :y"),
    @NamedQuery(name = "Destination.findByZoomLevel", query = "SELECT d FROM Destination d WHERE d.zoomLevel = :zoomLevel"),
    @NamedQuery(name = "Destination.findByVille", query = "SELECT d FROM Destination d WHERE d.ville = :ville")})
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DestinationPK destinationPK;
    @Column(name = "X")
    private Integer x;
    @Column(name = "Y")
    private Integer y;
    @Column(name = "zoom_level")
    private Integer zoomLevel;
    @Size(max = 60)
    @Column(name = "ville")
    private String ville;
    @JoinColumn(name = "pays_idpays", referencedColumnName = "idpays", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pays pays;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profil profil;

    public Destination() {
    }

    public Destination(DestinationPK destinationPK) {
        this.destinationPK = destinationPK;
    }

    public Destination(int profilIdprofil, int paysIdpays) {
        this.destinationPK = new DestinationPK(profilIdprofil, paysIdpays);
    }

    public DestinationPK getDestinationPK() {
        return destinationPK;
    }

    public void setDestinationPK(DestinationPK destinationPK) {
        this.destinationPK = destinationPK;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getZoomLevel() {
        return zoomLevel;
    }

    public void setZoomLevel(Integer zoomLevel) {
        this.zoomLevel = zoomLevel;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinationPK != null ? destinationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.destinationPK == null && other.destinationPK != null) || (this.destinationPK != null && !this.destinationPK.equals(other.destinationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Destination[ destinationPK=" + destinationPK + " ]";
    }
    
}
