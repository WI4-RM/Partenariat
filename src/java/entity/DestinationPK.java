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
    @Column(name = "profil_idprofil")
    private int profilIdprofil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pays_idpays")
    private int paysIdpays;

    public DestinationPK() {
    }

    public DestinationPK(int profilIdprofil, int paysIdpays) {
        this.profilIdprofil = profilIdprofil;
        this.paysIdpays = paysIdpays;
    }

    public int getProfilIdprofil() {
        return profilIdprofil;
    }

    public void setProfilIdprofil(int profilIdprofil) {
        this.profilIdprofil = profilIdprofil;
    }

    public int getPaysIdpays() {
        return paysIdpays;
    }

    public void setPaysIdpays(int paysIdpays) {
        this.paysIdpays = paysIdpays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) profilIdprofil;
        hash += (int) paysIdpays;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DestinationPK)) {
            return false;
        }
        DestinationPK other = (DestinationPK) object;
        if (this.profilIdprofil != other.profilIdprofil) {
            return false;
        }
        if (this.paysIdpays != other.paysIdpays) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.DestinationPK[ profilIdprofil=" + profilIdprofil + ", paysIdpays=" + paysIdpays + " ]";
    }
    
}
