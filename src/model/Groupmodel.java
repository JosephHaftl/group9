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
@Table(name = "GROUPMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupmodel.findAll", query = "SELECT g FROM Groupmodel g")
    , @NamedQuery(name = "Groupmodel.findByGroupid", query = "SELECT g FROM Groupmodel g WHERE g.groupid = :groupid")
    , @NamedQuery(name = "Groupmodel.findByGroupname", query = "SELECT g FROM Groupmodel g WHERE g.groupname = :groupname")})
public class Groupmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GROUPID")
    private Boolean groupid;
    @Column(name = "GROUPNAME")
    private String groupname;

    public Groupmodel() {
    }

    public Groupmodel(Boolean groupid) {
        this.groupid = groupid;
    }

    public Boolean getGroupid() {
        return groupid;
    }

    public void setGroupid(Boolean groupid) {
        this.groupid = groupid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupid != null ? groupid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmodel)) {
            return false;
        }
        Groupmodel other = (Groupmodel) object;
        if ((this.groupid == null && other.groupid != null) || (this.groupid != null && !this.groupid.equals(other.groupid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Groupmodel[ groupid=" + groupid + " ]";
    }
    
}
