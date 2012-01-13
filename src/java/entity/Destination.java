/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lolo
 */
@Entity
@Table(name = "destination")
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByIdDestination", query = "SELECT d FROM Destination d WHERE d.idDestination = :idDestination"),
    @NamedQuery(name = "Destination.findByX", query = "SELECT d FROM Destination d WHERE d.x = :x"),
    @NamedQuery(name = "Destination.findByY", query = "SELECT d FROM Destination d WHERE d.y = :y"),
    @NamedQuery(name = "Destination.findByZoomLevel", query = "SELECT d FROM Destination d WHERE d.zoomLevel = :zoomLevel"),
    @NamedQuery(name = "Destination.findByVille", query = "SELECT d FROM Destination d WHERE d.ville = :ville")})
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idDestination")
    private Integer idDestination;
    @Column(name = "X")
    private Integer x;
    @Column(name = "Y")
    private Integer y;
    @Column(name = "zoom_level")
    private Integer zoomLevel;
    @Column(name = "ville")
    private String ville;
    @JoinTable(name = "destination_has_profil", joinColumns = {
        @JoinColumn(name = "destination_idDestination", referencedColumnName = "idDestination")}, inverseJoinColumns = {
        @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil")})
    @ManyToMany
    private List<Profil> profilList;
    @JoinColumn(name = "pays_idpays", referencedColumnName = "idpays")
    @ManyToOne(optional = false)
    private Pays paysIdpays;

    public Destination() {
    }

    public Destination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
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

    public List<Profil> getProfilList() {
        return profilList;
    }

    public void setProfilList(List<Profil> profilList) {
        this.profilList = profilList;
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
        hash += (idDestination != null ? idDestination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.idDestination == null && other.idDestination != null) || (this.idDestination != null && !this.idDestination.equals(other.idDestination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Destination[idDestination=" + idDestination + "]";
    }

}
