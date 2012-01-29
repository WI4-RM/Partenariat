/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author charles
 */
@Entity
@Table(name = "ville")
@NamedQueries({
    @NamedQuery(name = "Ville.findAll", query = "SELECT v FROM Ville v"),
    @NamedQuery(name = "Ville.findByIdVille", query = "SELECT v FROM Ville v WHERE v.idVille = :idVille"),
    @NamedQuery(name = "Ville.findByX", query = "SELECT v FROM Ville v WHERE v.x = :x"),
    @NamedQuery(name = "Ville.findByY", query = "SELECT v FROM Ville v WHERE v.y = :y"),
    @NamedQuery(name = "Ville.findByZoomLevel", query = "SELECT v FROM Ville v WHERE v.zoomLevel = :zoomLevel"),
    @NamedQuery(name = "Ville.findByVille", query = "SELECT v FROM Ville v WHERE v.ville = :ville"),
    @NamedQuery(name = "Ville.findByIdpays", query = "SELECT v FROM Ville v WHERE v.paysIdpays.idpays = :idPays")})
@XmlRootElement
public class Ville implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVille")
    private Integer idVille;
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
    @Size(max = 60)
    @Column(name = "ville")
    private String ville;
    @JoinColumn(name = "pays_idpays", referencedColumnName = "idpays")
    @ManyToOne(optional = false)
    private Pays paysIdpays;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ville")
    private List<Destination> destinationList;

    public Ville() {
    }

    public Ville(Integer idVille) {
        this.idVille = idVille;
    }

    public Ville(Integer idVille, float x, float y, int zoomLevel) {
        this.idVille = idVille;
        this.x = x;
        this.y = y;
        this.zoomLevel = zoomLevel;
    }

    public Integer getIdVille() {
        return idVille;
    }

    public void setIdVille(Integer idVille) {
        this.idVille = idVille;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Pays getPaysIdpays() {
        return paysIdpays;
    }

    public void setPaysIdpays(Pays paysIdpays) {
        this.paysIdpays = paysIdpays;
    }

    @XmlTransient
    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public void setDestinationList(List<Destination> destinationList) {
        this.destinationList = destinationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVille != null ? idVille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ville)) {
            return false;
        }
        Ville other = (Ville) object;
        if ((this.idVille == null && other.idVille != null) || (this.idVille != null && !this.idVille.equals(other.idVille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ville[ idVille=" + idVille + " ]";
    }
    
}
