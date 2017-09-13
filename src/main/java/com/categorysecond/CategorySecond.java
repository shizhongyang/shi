package com.categorysecond;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.category.Category;
import com.product.Product;



/**
 * 二级分类的实体类对象
 * @author 传智.郭嘉
 *CREATE TABLE `categorysecond` (
  `csid` int(11) NOT NULL AUTO_INCREMENT,
  `csname` varchar(255) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`csid`),
  KEY `FK936FCAF21DB1FD15` (`cid`),
  CONSTRAINT `FK936FCAF21DB1FD15` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name="categorysecond")
public class CategorySecond {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="csid")
	private Integer csid;
	
	
	@Column(name="csname")
	private String csname;
	
	
	// 二级分类所属的一级分类:
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "cid", referencedColumnName = "cid") 
	private Category category;
	
	// 关联的商品的集合
	@OneToMany(mappedBy = "categorySecond", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<Product>();
	
	public Integer getCsid() {
		return csid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	public String getCsname() {
		return csname;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "CategorySecond [csid=" + csid + ", csname=" + csname + ", products="
				+ products + "]";
	}
	
}
