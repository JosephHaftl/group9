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
@Table(name = "PRIVATEMESSAGEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privatemessagemodel.findAll", query = "SELECT p FROM Privatemessagemodel p")
    , @NamedQuery(name = "Privatemessagemodel.findByMessageid", query = "SELECT p FROM Privatemessagemodel p WHERE p.messageid = :messageid")
    , @NamedQuery(name = "Privatemessagemodel.findByPrivatemessagename", query = "SELECT p FROM Privatemessagemodel p WHERE p.privatemessagename = :privatemessagename")
    , @NamedQuery(name = "Privatemessagemodel.findByPrivatemessage", query = "SELECT p FROM Privatemessagemodel p WHERE p.privatemessage = :privatemessage")})
public class Privatemessagemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MESSAGEID")
    private Integer messageid;
    @Column(name = "PRIVATEMESSAGENAME")
    private String privatemessagename;
    @Column(name = "PRIVATEMESSAGE")
    private String privatemessage;

    public Privatemessagemodel() {
    }

    public Privatemessagemodel(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public String getPrivatemessagename() {
        return privatemessagename;
    }

    public void setPrivatemessagename(String privatemessagename) {
        this.privatemessagename = privatemessagename;
    }

    public String getPrivatemessage() {
        return privatemessage;
    }

    public void setPrivatemessage(String privatemessage) {
        this.privatemessage = privatemessage;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageid != null ? messageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privatemessagemodel)) {
            return false;
        }
        Privatemessagemodel other = (Privatemessagemodel) object;
        if ((this.messageid == null && other.messageid != null) || (this.messageid != null && !this.messageid.equals(other.messageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Privatemessagemodel[ messageid=" + messageid + " ]";
    }
    
}
