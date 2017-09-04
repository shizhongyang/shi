package com.message;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.user.entity.MyUser;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "user_message")
public class UserMessage {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;

	@Column(name = "isRead")
	private boolean isRead;

	@ManyToOne(cascade = CascadeType.ALL, optional = false, targetEntity = MyUser.class)
	@JoinColumn(name = "uid", referencedColumnName = "id")
	private MyUser user;
	
	
	@Column(name="content")
	private String content;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public boolean isRead() {
		return isRead;
	}


	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}


	public MyUser getUser() {
		return user;
	}


	public void setUser(MyUser user) {
		this.user = user;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}
	
}
