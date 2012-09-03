/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.organizer.form;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author dejmien
 */
@Entity
@Table(name = "role")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Role")
    private Integer idRole;
    @Basic(optional = false)
    @Column(name = "AUTHORITY")
    private String authority;
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User")
    @OneToOne
    private User idUser;

    public Role() {
    }

    public Role(Integer idRole) {
        this.idRole = idRole;
    }

    public Role(Integer idRole, String authority) {
        this.idRole = idRole;
        this.authority = authority;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Role[ idRole=" + idRole + " ]";
    }
    
}
