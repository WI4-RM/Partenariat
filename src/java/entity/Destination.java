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
 * @author charles
 */
@Entity
@Table(name = "destination")
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByType", query = "SELECT d FROM Destination d WHERE d.type = :type"),
    @NamedQuery(name = "Destination.findByOrganisme", query = "SELECT d FROM Destination d WHERE d.organisme = :organisme"),
    @NamedQuery(name = "Destination.findByDate", query = "SELECT d FROM Destination d WHERE d.date = :date"),
    @NamedQuery(name = "Destination.findByIddestination", query = "SELECT d FROM Destination d WHERE d.iddestination = :iddestination")})
@XmlRootElement
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "destination_idDestination")
    private Integer iddestination;
    protected Destination Destination;
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

    public Destination(Destination Destination) {
        this.Destination = Destination;
    }

    public Destination(int destinationidDestination, Profil profil) {
        this.iddestination = destinationidDestination;
        this.profil = profil;
    }

    public Destination getDestination() {
        return Destination;
    }

    public void setDestination(Destination Destination) {
        this.Destination = Destination;
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
        hash += (Destination != null ? Destination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.Destination == null && other.Destination != null) || (this.Destination != null && !this.Destination.equals(other.Destination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Destination[ Destination=" + Destination + " ]";
    }
    
}
