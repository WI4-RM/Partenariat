/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lolo
 */
@Entity
@Table(name = "destination")
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByDestinationidDestination", query = "SELECT d FROM Destination d WHERE d.destinationPK.destinationidDestination = :destinationidDestination"),
    @NamedQuery(name = "Destination.findByProfilIdprofil", query = "SELECT d FROM Destination d WHERE d.destinationPK.profilIdprofil = :profilIdprofil"),
    @NamedQuery(name = "Destination.findByType", query = "SELECT d FROM Destination d WHERE d.type = :type"),
    @NamedQuery(name = "Destination.findByOrganisme", query = "SELECT d FROM Destination d WHERE d.organisme = :organisme"),
    @NamedQuery(name = "Destination.findByDate", query = "SELECT d FROM Destination d WHERE d.date = :date")})
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DestinationPK destinationPK;
    @Column(name = "type")
    private String type;
    @Column(name = "organisme")
    private String organisme;
    @Lob
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "destination_idDestination", referencedColumnName = "idVille", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ville ville;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profil profil;

    public Destination() {
    }

    public Destination(DestinationPK destinationPK) {
        this.destinationPK = destinationPK;
    }

    public Destination(int destinationidDestination, int profilIdprofil) {
        this.destinationPK = new DestinationPK(destinationidDestination, profilIdprofil);
    }

    public DestinationPK getDestinationPK() {
        return destinationPK;
    }

    public void setDestinationPK(DestinationPK destinationPK) {
        this.destinationPK = destinationPK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganisme() {
        return organisme;
    }

    public void setOrganisme(String organisme) {
        this.organisme = organisme;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
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
        return "entity.Destination[destinationPK=" + destinationPK + "]";
    }

}
