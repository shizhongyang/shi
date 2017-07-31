package com.user.Dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.terracotta.offheapstore.util.FindbugsSuppressWarnings;

import com.user.entity.MyUser;

//ע��
@Repository
public class MyUserDao extends HibernateDaoSupport {
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public List findmessage() {
		String hql = "from MyUser";
		List<MyUser> list = (List<MyUser>) this.getHibernateTemplate().find(hql);
		return list;
	}
	
	
	
	//查询登录
	public MyUser login(String telephone, String password) {
		String hql = "from MyUser where telephone = ? and pwd = ?";
		List<MyUser> list = (List<MyUser>) this.getHibernateTemplate().find(hql, telephone,password);
		if (list.size()!=0) {
			return list.get(0);			
		}
		return null;
	}

	public MyUser get(String id) {
		String hql = "from MyUser where id = ?";
		List<MyUser> list = (List<MyUser>) this.getHibernateTemplate().find(hql, id);
		if (list.size()!=0) {
			return list.get(0);			
		}
		return null;
	}

	public void update(MyUser myUser) {
		this.getHibernateTemplate().update(myUser);		
	}

	public void save(MyUser myUser) {
		this.getHibernateTemplate().save(myUser);				
	}

	public MyUser findUserByPhone(String phone) {
		String hql = "from MyUser where telephone = ?";
		List<MyUser> list = (List<MyUser>) this.getHibernateTemplate().find(hql, phone);
		if (list.size()!=0) {
			return list.get(0);			
		}
		return null;
	}

}
