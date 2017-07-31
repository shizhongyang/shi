package com.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.entity.MyUser;



@Service
public class OrderService {
	// 注入DAO
	@Autowired
	private OrderDao orderDao;
	
	
	// 业务层保存订单
	public Integer baocun(Order order) {
		return orderDao.baocun(order);
	}



	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}



	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}



	public List<Order> findByUid(MyUser existUser) {
		return orderDao.findByUid(existUser);
	}

}
