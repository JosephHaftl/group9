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
@Table(name = "LIKEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Likemodel.findAll", query = "SELECT l FROM Likemodel l")
    , @NamedQuery(name = "Likemodel.findByLikeid", query = "SELECT l FROM Likemodel l WHERE l.likeid = :likeid")
    , @NamedQuery(name = "Likemodel.findByLikestatus", query = "SELECT l FROM Likemodel l WHERE l.likestatus = :likestatus")})
public class Likemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LIKEID")
    private Integer likeid;
    @Column(name = "LIKESTATUS")
    private Boolean likestatus;

    public Likemodel() {
    }

    public Likemodel(Integer likeid) {
        this.likeid = likeid;
    }

    public Integer getLikeid() {
        return likeid;
    }

    public void setLikeid(Integer likeid) {
        this.likeid = likeid;
    }

    public Boolean getLikestatus() {
        return likestatus;
    }

    public void setLikestatus(Boolean likestatus) {
        this.likestatus = likestatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (likeid != null ? likeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Likemodel)) {
            return false;
        }
        Likemodel other = (Likemodel) object;
        if ((this.likeid == null && other.likeid != null) || (this.likeid != null && !this.likeid.equals(other.likeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Likemodel[ likeid=" + likeid + " ]";
    }
    
}
