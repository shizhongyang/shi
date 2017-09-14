package com.user.Dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.user.entity.MyFriend;
import com.user.entity.MyUser;
@Repository
public class FrinedsDao extends HibernateDaoSupport {

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public List<MyFriend> getList(String id) {
		
		String sql1 = "select s1.* FROM stu_user as s1 ,(SELECT * from friend_relation as f WHERE f.Fsrcid = ?)  as t1  WHERE s1.id = t1.FDesId";
		String sql = "select * from friend_relation where id = ?";  
		return this.getSessionFactory().getCurrentSession()
				.createSQLQuery(sql1)
				.addEntity(MyUser.class)
				.setParameter(0, id).list();
	}

	public void addFriend(MyFriend friend){
		this.getHibernateTemplate().save(friend);
	}
	
}
