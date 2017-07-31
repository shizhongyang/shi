package com.order.service;

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

import com.product.Product;

/**
 * 订单项实体
 * @author 传智.郭嘉
 *CREATE TABLE `orderitem` (
  `itemid` int(11) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `subtotal` double DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FKE8B2AB6166C01961` (`oid`),
  KEY `FKE8B2AB6171DB7AE4` (`pid`),
  CONSTRAINT `FKE8B2AB6166C01961` FOREIGN KEY (`oid`) REFERENCES `orders` (`oid`),
  CONSTRAINT `FKE8B2AB6171DB7AE4` FOREIGN KEY (`pid`) REFERENCES `product` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
 */
@Entity
@Table(name="orderitem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="itemid")
	private Integer itemid;
	
	@Column(name="count")
	private Integer count;
	
	@Column(name="subtotal")
	private Double subtotal;
	
	
	
	// 订单项包含的商品信息
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "pid", referencedColumnName = "pid") 
	private Product product;
	
	// 订单项所属的订单
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "oid", referencedColumnName = "oid") 
	private Order order;
	
	
	
	
	public Integer getItemid() {
		return itemid;
	}
	
	
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
}
