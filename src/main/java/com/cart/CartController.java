package com.cart;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.product.Product;
import com.product.ProductService;


@Controller
public class CartController {

	@Autowired
	private ProductService productService;

	// 添加到购物车的代码
	@RequestMapping(value = "cart_addCart", method = RequestMethod.POST)
	public String addCart(HttpServletRequest request) {
		String pid = request.getParameter("pid");
		String count = request.getParameter("count");
		// 查询商品信息
		Product product = productService.findByPid(Integer.valueOf(pid));
		System.out.println("------------" + product.toString());
		// 创建一个购物项
		CartItem cartItem = new CartItem();
		cartItem.setCount(Integer.valueOf(count));
		cartItem.setProduct(product);
		// 获取购物车 需要依赖request对象
		Cart cart = getCart(request);
		cart.addCart(cartItem);

		request.getSession().setAttribute("cart", cart);
		System.out.println("-----------");
		return "user/cart";
	}

	/**
	 * 移除购物项
	 */
	@RequestMapping(value = "cart_removeCart", method = RequestMethod.GET)
	public String removeCart(HttpServletRequest request) {
		// 获取Cart对象.
		String pid = request.getParameter("pid");
		Cart cart = getCart(request);
		cart.removeCart(Integer.valueOf(pid));
		return "user/cart";
	}

	/**
	 * 清空购物车
	 * 
	 */
	@RequestMapping(value = "cart_clearCart", method = RequestMethod.GET)
	public String clearCart(HttpServletRequest request) {
		Cart cart = getCart(request);
		cart.clearCart();
	    request.getSession().setAttribute("cart", cart);
		return "user/cart";
	}

	// 从sessiong范围获得购物车代码
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// 判断
		if (cart == null) {
			// 创建购物车对象
			cart = new Cart();
			// 将购物车对象放入到session中
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}
