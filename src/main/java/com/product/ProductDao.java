package com.product;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utils.PageHibernateCallback;


@Repository
public class ProductDao extends HibernateDaoSupport{

	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	
	// DAO层查询热门商品只显示10个
	public List<Product> findHot() {
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Product.class);
		criteria.add(Restrictions.eq("is_hot", 1));
		List<Product> list = this.getHibernateTemplate().findByCriteria(criteria, 0, 10);*/
		
		List<Product> list = (List<Product>) this.getHibernateTemplate().execute(new Mycallback<Product>("from Product where is_hot=?", new Object[]{1}, 0, 10));
		
		return list;
	}

	// DAO层的查询最新商品
	public List<Product> findNew() {
		List<Product> list = (List<Product>) this.getHibernateTemplate().execute(new Mycallback<Product>("from Product order by pdate desc", null , 0, 10));
		return list;
	}

	// 统计某个分类下的商品的总数:
	public Integer findCountByCid(Integer cid) {
		//String hql = "select count(*) from Product p , CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		String hql = "select count(*) from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,cid);
		System.out.println("list:============="+list.get(0).intValue());
		return list.get(0).intValue();
	}

	public List<Product> findByPage(Integer cid, int begin, int limit) {
		// String hql = "select p from Product p ,CategorySecond cs where p.categorySecond = cs and cs.category.cid = ?";
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = (List<Product>) this.getHibernateTemplate().execute(new Mycallback<Product>(hql, new Object[]{cid}, begin, limit));
		return list;
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	// 统计某个二级分类下商品数量
	public Integer findCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p join p.categorySecond cs where cs.csid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,csid);
		return list.get(0).intValue();
	}

	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = (List<Product>) this.getHibernateTemplate().execute(new Mycallback<Product>(hql, new Object[]{csid}, begin, limit));
		return list;
	}

	
	class Mycallback<T> implements HibernateCallback<List<T>>{
		private String hql;
		private Object[] params;
		private int startIndex;
		private int pageSize;
		

		public Mycallback(String hql, Object[] params,
				int startIndex, int pageSize) {
			super();
			this.hql = hql;
			this.params = params;
			this.startIndex = startIndex;
			this.pageSize = pageSize;
		}
		public List<T> doInHibernate(Session session) throws HibernateException {
			//1 执行hql语句
			Query query = session.createQuery(hql);
			//2 实际参数
			if(params != null){
				for(int i = 0 ; i < params.length ; i ++){
					query.setParameter(i, params[i]);
				}
			}
			//3 分页
			query.setFirstResult(startIndex);
			query.setMaxResults(pageSize);
			
			return query.list();
		}
		
	}
}
