<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>商品列表</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/product.css"
	rel="stylesheet" type="text/css" />

</head>
<body>

	<div class="container header">
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<!-- 一级分类显示 -->
				<c:forEach var="c" items="${categoryList }">
					<dl>
						<dt>
							<a
								href="${pageContext.request.contextPath}/product_findByCid?cid=${c.cid }&page=1">${c.cname }</a>
						</dt>
						<!-- 显示二级分类 -->
						<c:forEach var="cs" items="${c.categorySeconds }">
							<dd>
								<a
									href="${pageContext.request.contextPath}/product_findByCsid?csid=${cs.csid }&page=1">${cs.csname }</a>
							</dd>
						</c:forEach>
					</dl>
				</c:forEach>
			</div>
		</div>
		<div class="span18 last">

			<form id="productForm"
				action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm"
				method="get">
				<input type="hidden" id="brandId" name="brandId" value=""> <input
					type="hidden" id="promotionId" name="promotionId" value="">
						<input type="hidden" id="orderType" name="orderType" value="">
							<input type="hidden" id="pageNumber" name="pageNumber" value="1">
								<input type="hidden" id="pageSize" name="pageSize" value="20">

									<div id="result" class="result table clearfix">
										<ul>
											<%-- <s:iterator var="p" value="pageBean.list">
												<li><a
													href="${pageContext.request.contextPath}/product_findByPid.action?pid=<s:property value="#p.pid"/>">
														<img
														src="${pageContext.request.contextPath}/<s:property value="#p.image"/>"
														width="170" height="170" style="display: inline-block;">

															<span style='color: green'> <s:property
																	value="#p.pname" />
														</span> <span class="price"> 商城价： ￥<s:property
																	value="#p.shop_price" />
														</span>
												</a></li>
											</s:iterator> --%>
											<c:forEach var="p" items="${pageBean.list }">
												<li><a
													href="${pageContext.request.contextPath}/product_findByPid?pid=${p.pid }">
														<img
														src="${pageContext.request.contextPath}/${p.image }"
														width="170" height="170" style="display: inline-block;">

															<span style='color: green'> <s:property
																	value="${p.pname }" />
														</span> <span class="price"> 商城价： ${p.shop_price }
														</span>
												</a></li>
											</c:forEach>
										</ul>
									</div>
									<div class="pagination">
										<%-- 第
										<s:property value="pageBean.page" />
										/
										<s:property value="pageBean.totalPage" />
										页
										<s:if test="pageBean.page != 1">
											<a
												href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=1"
												class="firstPage">&nbsp;</a>
											<a
												href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page-1"/>"
												class="previousPage">&nbsp;</a>
										</s:if>
										<s:iterator var="i" begin="1" end="pageBean.totalPage"
											step="1">
											<s:if test="pageBean.page==#i">
												<span class="currentPage"><s:property value="#i" /></span>
											</s:if>
											<s:else>
												<a
													href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="#i"/>"><s:property
														value="#i" /></a>
											</s:else>
										</s:iterator>

										<s:if test="pageBean.page != pageBean.totalPage">
											<a class="nextPage"
												href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.page+1"/>">&nbsp;</a>
											<a class="lastPage"
												href="${ pageContext.request.contextPath }/product_findByCsid.action?csid=<s:property value="csid"/>&page=<s:property value="pageBean.totalPage"/>">&nbsp;</a>
										</s:if> --%>
										
										第
										${pageBean.page }
										/
										${pageBean.totalPage }
										页
										<c:if test="${pageBean.page != 1 }">
											<a
												href="${ pageContext.request.contextPath }/product_findByCsid?cid=${csid }&page=1"
												class="firstPage">&nbsp;</a>
											<a
												href="${ pageContext.request.contextPath }/product_findByCsid?cid=${csid }&page=${pageBean.page-1 }"
												class="previousPage">&nbsp;</a>
										</c:if>
										<c:forEach var="i" begin="1" end="${pageBean.totalPage }"
											step="1">
											<c:if test="${pageBean.page==i  }">
												<span class="currentPage">${i }</span>
											</c:if>
											<c:if test="${pageBean.page!=i }">
												<a
													href="${ pageContext.request.contextPath }/product_findByCsid?cid=${csid }&page=${i }">${i }
											</c:if>
										</c:forEach>

										<c:if test="${pageBean.page != pageBean.totalPage }">
											<a class="nextPage"
												href="${ pageContext.request.contextPath }/product_findByCsid?cid=${csid }&page=${pageBean.page+1 }">&nbsp;</a>
											<a class="lastPage"
												href="${ pageContext.request.contextPath }/product_findByCsid?cid=${csid }&page=${pageBean.totalPage}">&nbsp;</a>
										</c:if>
									</div>
			</form>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势">
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>诚聘英才</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>SHOP++官网</a> |</li>
				<li><a>SHOP++论坛</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
		</div>
	</div>
</body>
</html>