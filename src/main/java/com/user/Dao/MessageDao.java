package com.user.Dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.message.UserMessage;
import com.order.service.Order;

@Repository
public class MessageDao extends HibernateDaoSupport {
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// 持久层保存订单
	public String save(UserMessage message) {
		String oid = (String) this.getHibernateTemplate().save(message);
		return oid;
	}

}
