package com.category;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.categorysecond.CategorySecond;


/**
 * 一级分类的实体类
 * @author 传智.郭嘉
 *
 */
@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cid")
	private Integer cid;
	@Column(name="cname")
	private String cname;
	
	// 有二级分类的集合
	@OneToMany(mappedBy = "category",  fetch = FetchType.LAZY)
	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<CategorySecond> getCategorySeconds() {
		return categorySeconds;
	}
	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
		this.categorySeconds = categorySeconds;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", cname=" + cname + ", categorySeconds=" + categorySeconds + "]";
	}
	
	
}
