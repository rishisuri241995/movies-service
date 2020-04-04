/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rishi
 */
@Entity
@Table(name = "ASSIGNMENT_MOVIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssignmentMovie.findAll", query = "SELECT a FROM AssignmentMovie a"),
    @NamedQuery(name = "AssignmentMovie.findById", query = "SELECT a FROM AssignmentMovie a WHERE a.id = :id"),
    @NamedQuery(name = "AssignmentMovie.findByTitle", query = "SELECT a FROM AssignmentMovie a WHERE a.title = :title"),
    @NamedQuery(name = "AssignmentMovie.findByDescription", query = "SELECT a FROM AssignmentMovie a WHERE a.description = :description"),
    @NamedQuery(name = "AssignmentMovie.findByProducer", query = "SELECT a FROM AssignmentMovie a WHERE a.producer = :producer"),
    @NamedQuery(name = "AssignmentMovie.findByDirector", query = "SELECT a FROM AssignmentMovie a WHERE a.director = :director"),
    @NamedQuery(name = "AssignmentMovie.findByReleaseDate", query = "SELECT a FROM AssignmentMovie a WHERE a.releaseDate = :releaseDate"),
    @NamedQuery(name = "AssignmentMovie.findByBudget", query = "SELECT a FROM AssignmentMovie a WHERE a.budget = :budget")})
public class AssignmentMovie implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Size(max = 50)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Lob
    @Column(name = "IMAGE")
    private byte[] image;
    @Size(max = 50)
    @Column(name = "PRODUCER")
    private String producer;
    @Size(max = 50)
    @Column(name = "DIRECTOR")
    private String director;
    @Column(name = "RELEASE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;
    @Column(name = "BUDGET")
    private Double budget;

    public AssignmentMovie() {
    }

    public AssignmentMovie(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
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
        if (!(object instanceof AssignmentMovie)) {
            return false;
        }
        AssignmentMovie other = (AssignmentMovie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.models.AssignmentMovie[ id=" + id + " ]";
    }
    
}
