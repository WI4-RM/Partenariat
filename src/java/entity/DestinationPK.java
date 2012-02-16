/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author fingon
 */
@Embeddable
public class DestinationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "destination_idDestination")
    private int destinationidDestination;
    @Basic(optional = false)
    @NotNull
    @Column(name = "profil_idprofil")
    private int profilIdprofil;

    public DestinationPK() {
    }

    public DestinationPK(int destinationidDestination, int profilIdprofil) {
        this.destinationidDestination = destinationidDestination;
        this.profilIdprofil = profilIdprofil;
    }

    public int getDestinationidDestination() {
        return destinationidDestination;
    }

    public void setDestinationidDestination(int destinationidDestination) {
        this.destinationidDestination = destinationidDestination;
    }

    public int getProfilIdprofil() {
        return profilIdprofil;
    }

    public void setProfilIdprofil(int profilIdprofil) {
        this.profilIdprofil = profilIdprofil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) destinationidDestination;
        hash += (int) profilIdprofil;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinationPK)) {
            return false;
        }
        DestinationPK other = (DestinationPK) object;
        if (this.destinationidDestination != other.destinationidDestination) {
            return false;
        }
        if (this.profilIdprofil != other.profilIdprofil) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DestinationPK[ destinationidDestination=" + destinationidDestination + ", profilIdprofil=" + profilIdprofil + " ]";
    }
    
}
