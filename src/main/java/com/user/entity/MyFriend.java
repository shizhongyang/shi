package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friend_relation")
public class MyFriend {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	@Column(name = "Fsrcid")
	private String Fsrcid;
	
	@Column(name = "FDesId")
	private String FDesId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFsrcid() {
		return Fsrcid;
	}

	public void setFsrcid(String fsrcid) {
		Fsrcid = fsrcid;
	}

	public String getFDesId() {
		return FDesId;
	}

	public void setFDesId(String fDesId) {
		FDesId = fDesId;
	}

}
