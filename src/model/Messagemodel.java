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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ianchong16
 */
@Entity
@Table(name = "MESSAGEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messagemodel.findAll", query = "SELECT m FROM Messagemodel m")
    , @NamedQuery(name = "Messagemodel.findById", query = "SELECT m FROM Messagemodel m WHERE m.id = :id")
    , @NamedQuery(name = "Messagemodel.findByName", query = "SELECT m FROM Messagemodel m WHERE m.name = :name")
    , @NamedQuery(name = "Messagemodel.findByPmname", query = "SELECT m FROM Messagemodel m WHERE m.pmname = :pmname")})
public class Messagemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PMNAME")
    private String pmname;
    @Lob
    @Column(name = "PM")
    private String pm;

    public Messagemodel() {
    }

    public Messagemodel(Integer id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPmName() {
        return pmname;
    }

    public void setPmName(String pmname) {
        this.pmname = pmname;
    }

    public String getPm() {
        return pm;
    }

    public void setPm(String pm) {
        this.pm = pm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagemodel)) {
            return false;
        }
        Messagemodel other = (Messagemodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Messagemodel[ id=" + id + " ]";
    }
    
}
