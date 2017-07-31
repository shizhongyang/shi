package com.order.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cart.Cart;
import com.cart.CartItem;
import com.user.entity.MyUser;

@Controller
public class OrderController {

	private Order order;
	@Autowired
	private OrderService orderService;
	
	// 保存订单执行的方法;
	@RequestMapping(value="order_saveOrder")
	public String saveOrder(HttpServletRequest request) {
		order = new Order();
		/**************** 封装订单的数据 *********/
		order.setOrdertime(new Date());
		order.setState(1); // 1 未付款 2 已经付款. 3.已经发货 4 已经收货.
		// 有些数据需要从购物车中获取:

		// 获得购物车:
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		System.out.println("---------------"+cart);
		if (cart == null) {
			//this.addActionMessage("您还没有购物!请先去购物!");
			System.out.println("---------------"+cart);
			return "user/msg";
		}
		order.setTotal(cart.getTotal());
		// 订单所属的用户:
		MyUser existUser = (MyUser) request.getSession().getAttribute("myUser");
		if (existUser == null) {
			//this.addActionMessage("您还没有登录!请先去登录!");
			return "user/msg";
		}
		order.setUser(existUser);
		/******************** 封装订单项数据 *************/
		// 订单项数据从 购物项的数据获得.
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 清空购物车.
		cart.clearCart();

		// 保存订单:
		Integer oid = orderService.baocun(order);
		order.setOid(oid);
		// order = orderService.findByOid(oid);
		// System.out.println(order);
		return "user/order";
	}

}
