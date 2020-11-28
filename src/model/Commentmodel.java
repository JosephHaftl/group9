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
@Table(name = "COMMENTMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commentmodel.findAll", query = "SELECT c FROM Commentmodel c")
    , @NamedQuery(name = "Commentmodel.findByCommentid", query = "SELECT c FROM Commentmodel c WHERE c.commentid = :commentid")
    , @NamedQuery(name = "Commentmodel.findByComment", query = "SELECT c FROM Commentmodel c WHERE c.comment = :comment")})
public class Commentmodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COMMENTID")
    private Integer commentid;
    @Column(name = "COMMENT")
    private String comment;

    public Commentmodel() {
    }

    public Commentmodel(Integer commentid) {
        this.commentid = commentid;
    }

    public Integer getCommentid() {
        return commentid;
    }

    public void setCommentid(Integer commentid) {
        this.commentid = commentid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentid != null ? commentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentmodel)) {
            return false;
        }
        Commentmodel other = (Commentmodel) object;
        if ((this.commentid == null && other.commentid != null) || (this.commentid != null && !this.commentid.equals(other.commentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Commentmodel[ commentid=" + commentid + " ]";
    }
    
}
