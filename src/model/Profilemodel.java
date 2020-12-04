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
 * @author nicole
 */
@Entity
@Table(name = "PROFILEMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profilemodel.findAll", query = "SELECT p FROM Profilemodel p")
    , @NamedQuery(name = "Profilemodel.findById", query = "SELECT p FROM Profilemodel p WHERE p.id = :id")
    , @NamedQuery(name = "Profilemodel.findByName", query = "SELECT p FROM Profilemodel p WHERE p.name = :name")
    , @NamedQuery(name = "Profilemodel.findByAge", query = "SELECT p FROM Profilemodel p WHERE p.age = :age")
    , @NamedQuery(name = "Profilemodel.findByGradyear", query = "SELECT p FROM Profilemodel p WHERE p.gradyear = :gradyear")
    , @NamedQuery(name = "Profilemodel.findByBio", query = "SELECT p FROM Profilemodel p WHERE p.bio = :bio")})
public class Profilemodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "AGE")
    private Integer age;
    @Column(name = "GRADYEAR")
    private Integer gradyear;
    @Column(name = "BIO")
    private String bio;

    public Profilemodel() {
    }

    public Profilemodel(Integer id) {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGradyear() {
        return gradyear;
    }

    public void setGradyear(Integer gradyear) {
        this.gradyear = gradyear;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
        if (!(object instanceof Profilemodel)) {
            return false;
        }
        Profilemodel other = (Profilemodel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Profilemodel[ id=" + id + " ]";
    }
    
}
