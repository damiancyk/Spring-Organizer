/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.organizer.form;

import java.io.Serializable;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dejmien
 */
@Entity
@Table(name = "note")
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Note")
    private Integer idNote;
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Contents")
    private String contents;
    @Basic(optional = false)
    @Column(name = "Year")
    private int year;
    @Basic(optional = false)
    @Column(name = "Month")
    private int month;
    @Basic(optional = false)
    @Column(name = "Day")
    private int day;
    @Basic(optional = false)
    @Column(name = "Hour")
    private int hour;
    @Basic(optional = false)
    @Column(name = "Minute")
    private int minute;
    @Basic(optional = false)
    @Column(name = "Second")
    private int second;
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User")
    @ManyToOne
    private User idUser;

    public Note() {
    }

    public Note(Integer idNote) {
        this.idNote = idNote;
    }

    public Note(Integer idNote, int year, int month, int day, int hour, int minute, int second) {
        this.idNote = idNote;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Integer getIdNote() {
        return idNote;
    }

    public void setIdNote(Integer idNote) {
        this.idNote = idNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @JsonIgnore
    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNote != null ? idNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.idNote == null && other.idNote != null) || (this.idNote != null && !this.idNote.equals(other.idNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Note[ idNote=" + idNote + " ]";
    }
    
}
