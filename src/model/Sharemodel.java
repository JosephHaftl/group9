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
@Table(name = "SHAREMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sharemodel.findAll", query = "SELECT s FROM Sharemodel s")
    , @NamedQuery(name = "Sharemodel.findByShareid", query = "SELECT s FROM Sharemodel s WHERE s.shareid = :shareid")
    , @NamedQuery(name = "Sharemodel.findBySharestatus", query = "SELECT s FROM Sharemodel s WHERE s.sharestatus = :sharestatus")})
public class Sharemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SHAREID")
    private Integer shareid;
    @Column(name = "SHARESTATUS")
    private Boolean sharestatus;

    public Sharemodel() {
    }

    public Sharemodel(Integer shareid) {
        this.shareid = shareid;
    }

    public Integer getShareid() {
        return shareid;
    }

    public void setShareid(Integer shareid) {
        this.shareid = shareid;
    }

    public Boolean getSharestatus() {
        return sharestatus;
    }

    public void setSharestatus(Boolean sharestatus) {
        this.sharestatus = sharestatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shareid != null ? shareid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharemodel)) {
            return false;
        }
        Sharemodel other = (Sharemodel) object;
        if ((this.shareid == null && other.shareid != null) || (this.shareid != null && !this.shareid.equals(other.shareid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Sharemodel[ shareid=" + shareid + " ]";
    }
    
}
