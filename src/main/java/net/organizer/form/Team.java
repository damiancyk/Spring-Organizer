/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.organizer.form;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;

/**
 *
 * @author dejmien
 */
@Entity
@Table(name = "team")
@NamedQueries({
    @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")})
public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Team")
    private Integer idTeam;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Lob
    @Column(name = "Description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTeam")
    private Collection<Action> actionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTeam")
    private Collection<Invitation> invitationCollection;
    @OneToMany(mappedBy = "idTeam")
    private Collection<UserTeam> userTeamCollection;

    public Team() {
    }

    public Team(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public Team(Integer idTeam, String name, String description) {
        this.idTeam = idTeam;
        this.name = name;
        this.description = description;
    }

    public Integer getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Integer idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Action> getActionCollection() {
        return actionCollection;
    }

    public void setActionCollection(Collection<Action> actionCollection) {
        this.actionCollection = actionCollection;
    }

    public Collection<Invitation> getInvitationCollection() {
        return invitationCollection;
    }

    public void setInvitationCollection(Collection<Invitation> invitationCollection) {
        this.invitationCollection = invitationCollection;
    }

    public Collection<UserTeam> getUserTeamCollection() {
        return userTeamCollection;
    }

    public void setUserTeamCollection(Collection<UserTeam> userTeamCollection) {
        this.userTeamCollection = userTeamCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTeam != null ? idTeam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Team)) {
            return false;
        }
        Team other = (Team) object;
        if ((this.idTeam == null && other.idTeam != null) || (this.idTeam != null && !this.idTeam.equals(other.idTeam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Team[ idTeam=" + idTeam + " ]";
    }
    
}
