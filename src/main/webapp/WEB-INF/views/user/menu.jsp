<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="span24 col-md-12">
		<ul class="mainNav">
					<li>
						<a href="${ pageContext.request.contextPath }/toIndex">首页</a>
						|
					</li>					
					<c:forEach items="${categoryList }" var="s">
						<li></li>
						<li>
							<a href="${ pageContext.request.contextPath }/product_findByCid?cid=${s.cid }&page=1">${s.cname }</a>
							|
						</li>
					</c:forEach>
		</ul>
	</div>