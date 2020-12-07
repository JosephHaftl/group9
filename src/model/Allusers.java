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
@Table(name = "ALLUSERS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Allusers.findAll", query = "SELECT a FROM Allusers a")
    , @NamedQuery(name = "Allusers.findById", query = "SELECT a FROM Allusers a WHERE a.id = :id")
    , @NamedQuery(name = "Allusers.findByName", query = "SELECT a FROM Allusers a WHERE a.name = :name")
    , @NamedQuery(name = "Allusers.findByStatus", query = "SELECT a FROM Allusers a WHERE a.status = :status")
    , @NamedQuery(name = "Allusers.findByNotes", query = "SELECT a FROM Allusers a WHERE a.notes = :notes")

    , @NamedQuery(name = "Allusers.findByNameAdvanced", query = "SELECT a FROM Allusers a WHERE  LOWER(a.name) LIKE  CONCAT('%', LOWER(:name), '%')")})    
public class Allusers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "STATUS")
    private Boolean status;
    @Column(name = "NOTES")
    private String notes;

    public Allusers() {
    }

    public Allusers(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        if (!(object instanceof Allusers)) {
            return false;
        }
        Allusers other = (Allusers) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Allusers[ id=" + id + " ]";
    }
    
}
