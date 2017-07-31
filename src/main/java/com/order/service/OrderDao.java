package com.order.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.user.entity.MyUser;


@Repository
public class OrderDao extends HibernateDaoSupport{
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	// 持久层保存订单
	public Integer baocun(Order order) {
		Integer oid = (Integer) this.getHibernateTemplate().save(order);
		return oid;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// 按用户查询订单
	public List<Order> findByUid(MyUser existUser) {
		List<Order> list = (List<Order>) this.getHibernateTemplate()
				.find("from Order o where o.user.uid=? order by ordertime desc",existUser.getId());
		return list;
	}

}
