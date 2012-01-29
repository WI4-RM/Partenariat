/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
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
    @NamedQuery(name = "Destination.findByIddestination", query = "SELECT d FROM Destination d WHERE d.iddestination = :iddestination"),
    @NamedQuery(name = "Destination.findByIdprofil", query = "SELECT d FROM Destination d WHERE d.profilIdprofil.idprofil = :profilIdprofil")})
@XmlRootElement
public class Destination implements Serializable {   
    @Column(name =     "datearrivee")
    @Temporal(TemporalType.DATE)
    private Date datearrivee;
    @Column(name = "datedepart")
    @Temporal(TemporalType.DATE)
    private Date datedepart;
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "destination_idDestination")
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddestination")
    private Integer iddestination;
    @JoinColumn(name = "destination_idDestination", referencedColumnName = "idVille")
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

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


    public Integer getIddestination() {
        return iddestination;
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
        return "entity.Destination[iddestination=" + iddestination + "]";    }
   
}
