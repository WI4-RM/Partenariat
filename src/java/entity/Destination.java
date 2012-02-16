/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
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
    @NamedQuery(name = "Destination.findByDestinationidDestination", query = "SELECT d FROM Destination d WHERE d.destinationPK.destinationidDestination = :destinationidDestination"),
    @NamedQuery(name = "Destination.findByProfilIdprofil", query = "SELECT d FROM Destination d WHERE d.destinationPK.profilIdprofil = :profilIdprofil"),
    @NamedQuery(name = "Destination.findByType", query = "SELECT d FROM Destination d WHERE d.type = :type"),
    @NamedQuery(name = "Destination.findByOrganisme", query = "SELECT d FROM Destination d WHERE d.organisme = :organisme"),
    @NamedQuery(name = "Destination.findByDate", query = "SELECT d FROM Destination d WHERE d.date = :date"),
    @NamedQuery(name = "Destination.findByDatearrivee", query = "SELECT d FROM Destination d WHERE d.datearrivee = :datearrivee"),
    @NamedQuery(name = "Destination.findByDatedepart", query = "SELECT d FROM Destination d WHERE d.datedepart = :datedepart"),
       @NamedQuery(name = "Destination.deleteDestination", query = "DELETE FROM Destination d WHERE d.destinationPK.destinationidDestination = :destinationidDestination")})  
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DestinationPK destinationPK;
    @Size(max = 8)
    @Column(name = "type")
    private String type;
    @Size(max = 70)
    @Column(name = "organisme")
    private String organisme;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "datearrivee")
    @Temporal(TemporalType.DATE)
    private Date datearrivee;
    @Column(name = "datedepart")
    @Temporal(TemporalType.DATE)
    private Date datedepart;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Profil profil;
    @JoinColumn(name = "destination_idDestination", referencedColumnName = "idVille", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ville ville;

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

    public Date getDatearrivee() {
        return datearrivee;
    }

    public void setDatearrivee(Date datearrivee) {
        this.datearrivee = datearrivee;
    }

    public Date getDatedepart() {
        return datedepart;
    }

    public void setDatedepart(Date datedepart) {
        this.datedepart = datedepart;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
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
