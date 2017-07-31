package com.category;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends HibernateDaoSupport{
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	// DAO层的查询所有的一级分类的代码
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
		return (List<Category>) this.getHibernateTemplate().find("from Category");
	}
	
}
