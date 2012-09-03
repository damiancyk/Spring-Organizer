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
@Table(name = "user_team")
@NamedQueries({
    @NamedQuery(name = "UserTeam.findAll", query = "SELECT u FROM UserTeam u")})
public class UserTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_User_Team")
    private Integer idUserTeam;
    @Basic(optional = false)
    @Column(name = "Is_Admin")
    private boolean isAdmin;
    @JoinColumn(name = "Id_Team", referencedColumnName = "Id_Team")
    @ManyToOne
    private Team idTeam;
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User")
    @ManyToOne
    private User idUser;

    public UserTeam() {
    }

    public UserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public UserTeam(Integer idUserTeam, boolean isAdmin) {
        this.idUserTeam = idUserTeam;
        this.isAdmin = isAdmin;
    }

    public Integer getIdUserTeam() {
        return idUserTeam;
    }

    public void setIdUserTeam(Integer idUserTeam) {
        this.idUserTeam = idUserTeam;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Team getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Team idTeam) {
        this.idTeam = idTeam;
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
        hash += (idUserTeam != null ? idUserTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTeam)) {
            return false;
        }
        UserTeam other = (UserTeam) object;
        if ((this.idUserTeam == null && other.idUserTeam != null) || (this.idUserTeam != null && !this.idUserTeam.equals(other.idUserTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.UserTeam[ idUserTeam=" + idUserTeam + " ]";
    }
    
}
