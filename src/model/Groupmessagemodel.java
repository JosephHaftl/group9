/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseph Haftl
 */
@Entity
@Table(name = "GROUPMESSAGEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupmessagemodel.findAll", query = "SELECT g FROM Groupmessagemodel g")
    , @NamedQuery(name = "Groupmessagemodel.findByGroupmessageid", query = "SELECT g FROM Groupmessagemodel g WHERE g.groupmessageid = :groupmessageid")
    , @NamedQuery(name = "Groupmessagemodel.findByGroupmessagename", query = "SELECT g FROM Groupmessagemodel g WHERE g.groupmessagename = :groupmessagename")
    , @NamedQuery(name = "Groupmessagemodel.findByGroupmessage", query = "SELECT g FROM Groupmessagemodel g WHERE g.groupmessage = :groupmessage")})
public class Groupmessagemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUPMESSAGEID")
    private Integer groupmessageid;
    @Column(name = "GROUPMESSAGENAME")
    private String groupmessagename;
    @Column(name = "GROUPMESSAGE")
    private String groupmessage;

    public Groupmessagemodel() {
    }

    public Groupmessagemodel(Integer groupmessageid) {
        this.groupmessageid = groupmessageid;
    }

    public Integer getGroupmessageid() {
        return groupmessageid;
    }

    public void setGroupmessageid(Integer groupmessageid) {
        this.groupmessageid = groupmessageid;
    }

    public String getGroupmessagename() {
        return groupmessagename;
    }

    public void setGroupmessagename(String groupmessagename) {
        this.groupmessagename = groupmessagename;
    }

    public String getGroupmessage() {
        return groupmessage;
    }

    public void setGroupmessage(String groupmessage) {
        this.groupmessage = groupmessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupmessageid != null ? groupmessageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmessagemodel)) {
            return false;
        }
        Groupmessagemodel other = (Groupmessagemodel) object;
        if ((this.groupmessageid == null && other.groupmessageid != null) || (this.groupmessageid != null && !this.groupmessageid.equals(other.groupmessageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Groupmessagemodel[ groupmessageid=" + groupmessageid + " ]";
    }
    
}
