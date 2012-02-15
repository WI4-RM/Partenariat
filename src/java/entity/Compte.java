/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author charles
 */
@Entity
@Table(name = "compte")
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdcompte", query = "SELECT c FROM Compte c WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compte.findByEmail", query = "SELECT c FROM Compte c WHERE c.email = :email"),
    @NamedQuery(name = "Compte.findByPasswordHash", query = "SELECT c FROM Compte c WHERE c.passwordHash = :passwordHash"),
    @NamedQuery(name = "Compte.findByValidationHash", query = "SELECT c FROM Compte c WHERE c.validationHash = :validationHash"),
    @NamedQuery(name = "Compte.findByIsAdministrator", query = "SELECT c FROM Compte c WHERE c.isAdministrator = :isAdministrator"),
    @NamedQuery(name = "Compte.findBySalt", query = "SELECT c FROM Compte c WHERE c.salt = :salt"),
    @NamedQuery(name = "Compte.findByIdprofil", query = "SELECT c FROM Compte c WHERE c.profilIdprofil.idprofil = :idprofil")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompte")
    private Integer idcompte;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    @Column(name = "password_hash")
    private String passwordHash;
    @Size(max = 64)
    @Column(name = "validation_hash")
    private String validationHash;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_administrator")
    private boolean isAdministrator;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "salt")
    private String salt;
    @JoinColumn(name = "profil_idprofil", referencedColumnName = "idprofil")
    @ManyToOne(optional = false)
    private Profil profilIdprofil;

    public Compte() {
    }

    public Compte(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public Compte(Integer idcompte, String email, String passwordHash, boolean isAdministrator, String salt) {
        this.idcompte = idcompte;
        this.email = email;
        this.passwordHash = passwordHash;
        this.isAdministrator = isAdministrator;
        this.salt = salt;
    }

    public Integer getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getValidationHash() {
        return validationHash;
    }

    public void setValidationHash(String validationHash) {
        this.validationHash = validationHash;
    }

    public boolean getIsAdministrator() {
        return isAdministrator;
    }

    public void setIsAdministrator(boolean isAdministrator) {
        this.isAdministrator = isAdministrator;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
        hash += (idcompte != null ? idcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idcompte == null && other.idcompte != null) || (this.idcompte != null && !this.idcompte.equals(other.idcompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Compte[ idcompte=" + idcompte + " ]";
    }
    
}
