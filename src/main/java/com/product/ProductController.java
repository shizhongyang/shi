package com.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.category.Category;
import com.category.CategoryService;

import com.utils.PageBean;

@Controller
public class ProductController {

	// 注入一级分类的Service
	@Autowired
	private CategoryService categoryService;
	// 注入商品的Service
	@Autowired
	private ProductService productService;

	// 分页的商品显示:
	private PageBean<Product> pageBean;

	// 查询商品的方法:
	@RequestMapping(value = "/product_findByCid", method = RequestMethod.GET)
	public String findByCid(HttpServletRequest request) {

		String cid = request.getParameter("cid");
		String page = request.getParameter("page");
		// 查询分类:
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();

		request.getSession().setAttribute("categoryList", categoryList);
		request.getSession().setAttribute("cid", cid);
		// 查询商品:
		pageBean = productService.findByPage(Integer.valueOf(cid), Integer.valueOf(page));
		request.getSession().setAttribute("pageBean", pageBean);
		System.out.println("-----------" + pageBean);
		return "user/list";
	}

	// 模型驱动类
	private Product product = new Product();

	// 查询商品详情:
	@RequestMapping(value = "/product_findByPid", method = RequestMethod.GET)
	public String findByPid(HttpServletRequest request) {
		String pid = request.getParameter("pid");
		System.out.println("-----------" + pid);
		// 查询所有一级分类:
		List<Category> categoryList = categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		product = productService.findByPid(Integer.valueOf(pid));
		request.getSession().setAttribute("pid", pid);
		request.getSession().setAttribute("product", product);
		System.out.println("-----------" + product);
		return "user/desc";
	}

	// 查询二级分类的商品:
	@RequestMapping(value = "/product_findByCsid", method = RequestMethod.GET)
	public String findByCsid(HttpServletRequest request) {
		String csid = request.getParameter("csid");
		String page = request.getParameter("page");
		request.getSession().setAttribute("csid", csid);
		// 查询所有一级分类:
		//List<Category> categoryList = categoryService.findAll();
		// request.getSession().setAttribute("categoryList", categoryList);
		pageBean = productService.findByCsid(Integer.valueOf(csid), Integer.valueOf(page));
		return "user/cslist";
	}

}
