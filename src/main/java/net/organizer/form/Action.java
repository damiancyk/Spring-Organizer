/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.organizer.form;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author dejmien
 */
@Entity
@Table(name = "action")
@NamedQueries({
    @NamedQuery(name = "Action.findAll", query = "SELECT a FROM Action a")})
public class Action implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Action")
    private Integer idAction;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Lob
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @Column(name = "Date_When")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateWhen;
    @Column(name = "Periodic")
    private String periodic;
    @Column(name = "Priority")
    private Short priority;
    @Column(name = "Type_Action")
    private Short typeAction;
    @JoinColumn(name = "Id_Team", referencedColumnName = "Id_Team")
    @ManyToOne(optional = false)
    private Team idTeam;

    public Action() {
    }

    public Action(Integer idAction) {
        this.idAction = idAction;
    }

    public Action(Integer idAction, String name, Date dateWhen) {
        this.idAction = idAction;
        this.name = name;
        this.dateWhen = dateWhen;
    }

    public Integer getIdAction() {
        return idAction;
    }

    public void setIdAction(Integer idAction) {
        this.idAction = idAction;
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

    public Date getDateWhen() {
        return dateWhen;
    }

    public void setDateWhen(Date dateWhen) {
        this.dateWhen = dateWhen;
    }

    public String getPeriodic() {
        return periodic;
    }

    public void setPeriodic(String periodic) {
        this.periodic = periodic;
    }

    public Short getPriority() {
        return priority;
    }

    public void setPriority(Short priority) {
        this.priority = priority;
    }

    public Short getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(Short typeAction) {
        this.typeAction = typeAction;
    }

    @JsonIgnore
    public Team getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(Team idTeam) {
        this.idTeam = idTeam;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAction != null ? idAction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Action)) {
            return false;
        }
        Action other = (Action) object;
        if ((this.idAction == null && other.idAction != null) || (this.idAction != null && !this.idAction.equals(other.idAction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.organizer.form.Action[ idAction=" + idAction + " ]";
    }
    
}
