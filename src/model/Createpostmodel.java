/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joseph Haftl
 */
@Entity
@Table(name = "CREATEPOSTMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Createpostmodel.findAll", query = "SELECT c FROM Createpostmodel c")
    , @NamedQuery(name = "Createpostmodel.findById", query = "SELECT c FROM Createpostmodel c WHERE c.id = :id")
    , @NamedQuery(name = "Createpostmodel.findByDate", query = "SELECT c FROM Createpostmodel c WHERE c.date = :date")
    , @NamedQuery(name = "Createpostmodel.findByPost", query = "SELECT c FROM Createpostmodel c WHERE c.post = :post")})
public class Createpostmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "POST")
    private String post;

    public Createpostmodel() {
    }

    public Createpostmodel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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
        if (!(object instanceof Createpostmodel)) {
            return false;
        }
        Createpostmodel other = (Createpostmodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Createpostmodel[ id=" + id + " ]";
    }
    
}
