package com.project.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Banners")
public class Banners implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;
    
    @Column(name="cid")
    private int cid;
    
    @Column(name="image_url")
    private String image_url;  

    public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Banners() {
    }

    public Banners(int bid,int cid, String image_url) {
        this.bid = bid;
        this.cid = cid;
        this.image_url = image_url;
    }
	
	
}
