<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="last">

	<div class="span5">
			<div class="logo">
				<a href="${basePath}/shi/toIndex"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
					alt="传智播客"></a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障">
			</div>
	</div>

	<div class="topNav clearfix  col-md-5">
		<ul>
			<c:if test="${sessionScope.myUser != null}" >
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${ pageContext.request.contextPath  }/myuser/toMyMessagePage">${sessionScope.myUser.name }</a>|
				</li>
				
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/">我的首页</a>|
				</li>
				
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${ pageContext.request.contextPath }/">我的订单</a>|
				</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a
					href="${pageContext.request.contextPath }/quit">退出</a>|
				</li>
			</c:if>

			<c:if test="${sessionScope.myUser == null}">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="myuser/toLoginPage">登录</a>|
				</li>
				<li id="headerRegister" class="headerRegister"
					style="display: list-item;"><a
					href="${pageContext.request.contextPath }/myuser/registPage">注册</a>|
				</li>
			</c:if>
			

			<li id="headerUsername" class="headerUsername"></li>
			<li id="headerLogout" class="headerLogout"><a>[退出]</a>|</li>
			<li><a>会员中心</a> |</li>
			<li><a>购物指南</a> |</li>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="./购物车.htm">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>96008/53277764</strong>
	</div>
</div>

