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
@Table(name = "diary")
@NamedQueries({
    @NamedQuery(name = "Diary.findAll", query = "SELECT d FROM Diary d")})
public class Diary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Diary")
    private Integer idDiary;
    @Lob
    @Column(name = "Contents")
    private String contents;
    @Basic(optional = false)
    @Column(name = "Day")
    private int day;
    @Basic(optional = false)
    @Column(name = "Month")
    private int month;
    @Basic(optional = false)
    @Column(name = "Year")
    private int year;
    @JoinColumn(name = "Id_User", referencedColumnName = "Id_User")
    @ManyToOne
    private User idUser;

    public Diary() {
    }

    public Diary(Integer idDiary) {
        this.idDiary = idDiary;
    }

    public Diary(Integer idDiary, int day, int month, int year) {
        this.idDiary = idDiary;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Integer getIdDiary() {
        return idDiary;
    }

    public void setIdDiary(Integer idDiary) {
        this.idDiary = idDiary;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
        hash += (idDiary != null ? idDiary.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Diary)) {
            return false;
        }
        Diary other = (Diary) object;
        if ((this.idDiary == null && other.idDiary != null) || (this.idDiary != null && !this.idDiary.equals(other.idDiary))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Diary[ idDiary=" + idDiary + " ]";
    }
    
}
