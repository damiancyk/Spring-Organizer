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
@Table(name = "details")
@NamedQueries({
    @NamedQuery(name = "Details.findAll", query = "SELECT d FROM Details d")})
public class Details implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DETAILS")
    private Integer idDetails;
    @Column(name = "Email")
    private String email;
    @Column(name = "Firstname")
    private String firstname;
    @Column(name = "Lastname")
    private String lastname;
    @Lob
    @Column(name = "About")
    private String about;
    @Column(name = "Telephone")
    private String telephone;
    @OneToOne(mappedBy = "idDetails")
    private User user;

    public Details() {
    }

    public Details(Integer idDetails) {
        this.idDetails = idDetails;
    }

    public Integer getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(Integer idDetails) {
        this.idDetails = idDetails;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetails != null ? idDetails.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Details)) {
            return false;
        }
        Details other = (Details) object;
        if ((this.idDetails == null && other.idDetails != null) || (this.idDetails != null && !this.idDetails.equals(other.idDetails))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Details[ idDetails=" + idDetails + " ]";
    }
    
}
