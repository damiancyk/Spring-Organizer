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
@Table(name = "contact")
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")})
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Contact")
    private Integer idContact;
    @JoinColumn(name = "Id_Position", referencedColumnName = "Id_User")
    @ManyToOne(optional = false)
    private User idPosition;
    @JoinColumn(name = "Id_Owner", referencedColumnName = "Id_User")
    @ManyToOne(optional = false)
    private User idOwner;

    public Contact() {
    }

    public Contact(Integer idContact) {
        this.idContact = idContact;
    }

    public Integer getIdContact() {
        return idContact;
    }

    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public User getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(User idPosition) {
        this.idPosition = idPosition;
    }

    public User getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(User idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idContact != null ? idContact.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.idContact == null && other.idContact != null) || (this.idContact != null && !this.idContact.equals(other.idContact))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Contact[ idContact=" + idContact + " ]";
    }
    
}
