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
@Table(name = "TRENDINGMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trendingmodel.findAll", query = "SELECT t FROM Trendingmodel t")
    , @NamedQuery(name = "Trendingmodel.findByTrendingid", query = "SELECT t FROM Trendingmodel t WHERE t.trendingid = :trendingid")
    , @NamedQuery(name = "Trendingmodel.findByTrendingstatus", query = "SELECT t FROM Trendingmodel t WHERE t.trendingstatus = :trendingstatus")})
public class Trendingmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TRENDINGID")
    private Integer trendingid;
    @Column(name = "TRENDINGSTATUS")
    private Boolean trendingstatus;

    public Trendingmodel() {
    }

    public Trendingmodel(Integer trendingid) {
        this.trendingid = trendingid;
    }

    public Integer getTrendingid() {
        return trendingid;
    }

    public void setTrendingid(Integer trendingid) {
        this.trendingid = trendingid;
    }

    public Boolean getTrendingstatus() {
        return trendingstatus;
    }

    public void setTrendingstatus(Boolean trendingstatus) {
        this.trendingstatus = trendingstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trendingid != null ? trendingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trendingmodel)) {
            return false;
        }
        Trendingmodel other = (Trendingmodel) object;
        if ((this.trendingid == null && other.trendingid != null) || (this.trendingid != null && !this.trendingid.equals(other.trendingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Trendingmodel[ trendingid=" + trendingid + " ]";
    }
    
}
