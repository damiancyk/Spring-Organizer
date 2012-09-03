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
@Table(name = "invitation")
@NamedQueries({
    @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i")})
public class Invitation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Invitation")
    private Integer idInvitation;
    @JoinColumn(name = "Id_Team", referencedColumnName = "Id_Team")
    @ManyToOne(optional = false)
    private Team idTeam;
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User")
    @ManyToOne(optional = false)
    private User idUser;

    public Invitation() {
    }

    public Invitation(Integer idInvitation) {
        this.idInvitation = idInvitation;
    }

    public Integer getIdInvitation() {
        return idInvitation;
    }

    public void setIdInvitation(Integer idInvitation) {
        this.idInvitation = idInvitation;
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
        hash += (idInvitation != null ? idInvitation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.idInvitation == null && other.idInvitation != null) || (this.idInvitation != null && !this.idInvitation.equals(other.idInvitation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Invitation[ idInvitation=" + idInvitation + " ]";
    }
    
}
