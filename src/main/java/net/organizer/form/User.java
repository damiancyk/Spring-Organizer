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
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_User")
    private Integer idUser;
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @Column(name = "ENABLED")
    private int enabled;
    @OneToMany(mappedBy = "idUser")
    private Collection<Diary> diaryCollection;
    @OneToOne(mappedBy = "idUser")
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
    private Collection<Invitation> invitationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPosition")
    private Collection<Contact> contactCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOwner")
    private Collection<Contact> contactCollection1;
    @JoinColumn(name = "Id_Details", referencedColumnName = "ID_DETAILS")
    @OneToOne
    private Details idDetails;
    @OneToMany(mappedBy = "idUser")
    private Collection<UserTeam> userTeamCollection;
    @OneToMany(mappedBy = "idUser")
    private Collection<Note> noteCollection;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String login, String password, int enabled) {
        this.idUser = idUser;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Collection<Diary> getDiaryCollection() {
        return diaryCollection;
    }

    public void setDiaryCollection(Collection<Diary> diaryCollection) {
        this.diaryCollection = diaryCollection;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Collection<Invitation> getInvitationCollection() {
        return invitationCollection;
    }

    public void setInvitationCollection(Collection<Invitation> invitationCollection) {
        this.invitationCollection = invitationCollection;
    }

    public Collection<Contact> getContactCollection() {
        return contactCollection;
    }

    public void setContactCollection(Collection<Contact> contactCollection) {
        this.contactCollection = contactCollection;
    }

    public Collection<Contact> getContactCollection1() {
        return contactCollection1;
    }

    public void setContactCollection1(Collection<Contact> contactCollection1) {
        this.contactCollection1 = contactCollection1;
    }

    public Details getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Details idDetails) {
        this.idDetails = idDetails;
    }

    public Collection<UserTeam> getUserTeamCollection() {
        return userTeamCollection;
    }

    public void setUserTeamCollection(Collection<UserTeam> userTeamCollection) {
        this.userTeamCollection = userTeamCollection;
    }

    public Collection<Note> getNoteCollection() {
        return noteCollection;
    }

    public void setNoteCollection(Collection<Note> noteCollection) {
        this.noteCollection = noteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.User[ idUser=" + idUser + " ]";
    }
    
}
