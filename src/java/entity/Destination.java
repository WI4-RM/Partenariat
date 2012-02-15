/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
*
* @author lolo
*/
@Entity
@Table(name = "destination")
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByType", query = "SELECT d FROM Destination d WHERE d.type = :type"),
    @NamedQuery(name = "Destination.findByOrganisme", query = "SELECT d FROM Destination d WHERE d.organisme = :organisme"),
    @NamedQuery(name = "Destination.findByDate", query = "SELECT d FROM Destination d WHERE d.date = :date"),
    @NamedQuery(name = "Destination.findByIddestination", query = "SELECT d FROM Destination d WHERE d.iddestination = :iddestination"),
    @NamedQuery(name = "Destination.findByIdprofil", query = "SELECT d FROM Destination d WHERE d.profilIdprofil.idprofil = :profilIdprofil")})
public class Destination implements Serializable {
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "datearrivee")
    @Temporal(TemporalType.DATE)
    private Date datearrivee;
    @Column(name = "datedepart")
    @Temporal(TemporalType.DATE)
    private Date datedepart;
    private static final long serialVersionUID = 1L;
    @Column(name = "type")
    private String type;
    @Column(name = "organisme")
    private String organisme;
    @Lob
    @Column(name = "commentaire")
    private String commentaire;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddestination")
    private Integer iddestination;
    @JoinColumn(name = "destination_idDestination", referencedColumnName = "idVille")
    @ManyToOne(optional = false)
    private Ville destinationidDestination;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil")
    @ManyToOne(optional = false)
    private Profil profilIdprofil;

    public Destination() {
    }

    public Destination(Integer iddestination) {
        this.iddestination = iddestination;
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

    public Integer getIddestination() {
        return iddestination;
    }

    public void setIddestination(Integer iddestination) {
        this.iddestination = iddestination;
    }

    public Ville getDestinationidDestination() {
        return destinationidDestination;
    }

    public void setDestinationidDestination(Ville destinationidDestination) {
        this.destinationidDestination = destinationidDestination;
    }

    public Profil getProfilIdprofil() {
        return profilIdprofil;
    }

    public void setProfilIdprofil(Profil profilIdprofil) {
        this.profilIdprofil = profilIdprofil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddestination != null ? iddestination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.iddestination == null && other.iddestination != null) || (this.iddestination != null && !this.iddestination.equals(other.iddestination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Destination[iddestination=" + iddestination + "]";
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

}