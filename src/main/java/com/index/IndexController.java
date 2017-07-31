package com.index;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.category.Category;
import com.category.CategoryService;
import com.product.Product;
import com.product.ProductService;
import com.thoughtworks.xstream.mapper.Mapper.Null;

@Controller
public class IndexController {

	@Autowired
	CategoryService categoryService;

	// 注入商品的Service
	@Autowired
	private ProductService productService;

	// 热门商品的集合
	private List<Product> hotList;
	// 最新商品
	private List<Product> newList;

	
	
	/**
	 * 进入首页
	 * @return
	 */
	@RequestMapping("/toIndex")
	public String toIndex(HttpServletRequest request) {
		System.out.println("jingru");
		ArrayList<Category> mdatas = (ArrayList<Category>) categoryService.findAll();
		request.getSession().setAttribute("categoryList", mdatas);
		
		/*for (Category category : mdatas) {
			System.out.println("------"+category);
		}*/
		// 查询热门商品
		hotList = productService.findHot();
		request.getSession().setAttribute("hotList", hotList);
		// 查询最新商品
		newList = productService.findNew();
		request.getSession().setAttribute("newList", newList);
		System.out.println("--------"+hotList+"\n"+newList);
		return "user/index";
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/quit")
	public String quit(HttpServletRequest request) {
		System.out.println("jingru");
		request.getSession().setAttribute("myUser", null);
		return "user/index";
	}
}
