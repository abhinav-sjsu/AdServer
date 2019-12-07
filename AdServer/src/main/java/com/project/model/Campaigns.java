package com.project.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Campaigns")
public class Campaigns implements Serializable {
	
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cid;
    
    @Column(name="name")
    private String name;
    
    @Column(name="startDate")
    private Date startDate;
    
    @Column(name="endDate")
    private Date endDate;
    
    @JoinColumn(name="cid",nullable=false)
    private Banners banners;

    public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public Campaigns() {
    }

    public Campaigns(int cid, String name, Date startDate,Date endDate) {
        this.cid = cid;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }



    @Override
    public String toString() {
        return "City{" + "cid=" + cid + ", name=" + name
                + ", startDate=" + startDate + '}';
    }

}
