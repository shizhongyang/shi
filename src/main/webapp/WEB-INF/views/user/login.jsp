<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0080)http://localhost:8080/mango/login.jhtml?redirectUrl=%2Fmango%2Fcart%2Flist.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--  initial-scale=1  不缩放   width=device-width 物理设备的宽度 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>用户登录</title>

<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/jquery.validate.min.js"></script>	
<script src="http://static.runoob.com/assets/jquery-validation-1.14.0/dist/localization/messages_zh.js"></script>

<script type="text/javascript">
	function change() {
		var img = document.getElementById("checkImg");
		img.src = "${basePath}/img?" + new Date().getTime();
	}
	
	
	$(function() {
	    $("#registerForm").validate();
	});
</script>
</head>
<body>

	<div class="container header">
		<%-- <div class="col-md-2">
			<div class="logo">
				<a href="${basePath}/shi/toIndex"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
					alt="传智播客"></a>
			</div>
		</div>
		<div class="col-md-5">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障">
			</div>
		</div> --%>
		<%@ include file="header.jsp"%>
		<%@ include file="menu.jsp"%>
	</div>
	<div class="container  login">
		<div class="col-md-6">
			<div class="ad">
				<img src="${pageContext.request.contextPath}/image/login.jpg"
					width="400" height="330" alt="会员登录" title="会员登录">
			</div>
		</div>

		<div class="col-md-6">
			<div>
				<div>
					<div class="title">
						<strong>会员登录</strong>USER LOGIN
					</div>

					<div class="row" style="margin-top: 20px;">
						<div class="center-block"
							style="width: 200px; background-color: #fff;">
							<div>
								<form action="${ pageContext.request.contextPath }/myuser/login"
									method="post" class="form-horizontal">
									<div class="form-group">
										<label class="sr-only" for="exampleInputAmount">Amount
											(in dollars)</label>
										<div class="input-group">
											<div class="input-group-addon">
												<span class="glyphicon glyphicon-user"></span>
											</div>
											<input type="text" class="form-control"
												name="telephone"
												id="exampleInputAmount" placeholder="username" required>
										</div>
									</div>
									<div class="form-group">
										<label class="sr-only" for="exampleInputAmount">Amount
											(in dollars)</label>
										<div class="input-group">
											<div class="input-group-addon">
												<span class="glyphicon glyphicon-lock"></span>
											</div>
											<input type="password" class="form-control"
												name="password"
												id="exampleInputAmount" placeholder="password" required>
										</div>
									</div>

									<div class="form-group">
										<label class="sr-only" for="exampleInputAmount">Amount
											(in dollars)</label>
									<!-- 	<div class="input-group"> -->
										<!-- 	<div class="input-group-addon">
												<span class="glyphicon " aria-hidden="true"></span>
											</div> -->
											<div class="col-md-6 form-group">
												<input type="text" class="form-control"
												name="checkcode"
												id="exampleInputAmount" aria-describedby="helpBlock" placeholder="验证码" required> 
												<c:if test="${sessionScope.message!=null }">
													<span> ${sessionScope.message }</span>
												</c:if>
											</div>
											<div  class="col-md-6 form-group">
												<img id="checkImg"
												class=" col-md-offset-2"
											src="${ pageContext.request.contextPath }/img"
											title="点击更换验证码" onclick="change()" />
												
											</div>
											
										<!-- </div> -->
									</div>


									<div class="checkbox form-group">
										<label> <input type="checkbox">记住密码
										</label>
									</div>
									<div class="form-group">
										<button type="submit"
											class="btn btn-primary col-md-5 pull-left">登录</button>
										<a class="btn btn-primary col-md-5 pull-right" role="button "
											href="registPage">注册</a>
										
									</div>
									<div class="form-group">

										<p class="bg-info">
											<h3>还没有注册账号？</h3>
										</p>
										<p class="text-muted">
											立即注册即可体验在线购物！ <a href="./会员注册.htm">立即注册</a>
										</p>

									</div>
								</form>


							</div>

						</div>
					</div>

					<%-- 
					<form id="loginForm"
						action="${ pageContext.request.contextPath }/myuser/login"
						method="post" novalidate="novalidate">
						<table>
							<tbody>
								<tr>
									<th>用户名:</th>
									<td><input type="text" id="username" name="username"
										class="text" maxlength="20" /><span><s:fielderror
												fieldName="username" /></span></td>
								</tr>
								<tr>
									<th>密&nbsp;&nbsp;码:</th>
									<td><input type="password" id="password" name="password"
										class="text" maxlength="20" autocomplete="off" /><span><s:fielderror
												fieldName="password" /></span></td>
								</tr>
								<tr>
									<th><span class="requiredField">*</span>验证码:</th>
									<td><span class="fieldSet"> <input type="text"
											id="checkcode" name="checkcode" class="text" maxlength="4"
											onblur="checkCode()" /> <img id="checkImg"
											class="captchaImage"
											src="${ pageContext.request.contextPath }/img"
											title="点击更换验证码" onclick="change()" />
									</span> <span id="span2"> </span></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><label> <input type="checkbox"
											id="isRememberUsername" name="isRememberUsername"
											value="true">记住用户名 </label> <label> &nbsp;&nbsp;<a>找回密码</a>
									</label></td>
								</tr>
								<tr>
									<th>&nbsp;</th>
									<td><input type="submit" class="submit" value="登 录"></td>
								</tr>
								<tr class="register">
									<th>&nbsp;</th>
									<td>
										<dl>
											<dt>还没有注册账号？</dt>
											<dd>
												立即注册即可体验在线购物！ <a href="./会员注册.htm">立即注册</a>
											</dd>
										</dl>
									</td>
								</tr>
							</tbody>
						</table>
					</form> --%>
				</div>
			</div>
		</div>
	</div>
	<div class="container footer">
		<div class="span24">
			<div class="footerAd">
				<img src="${pageContext.request.contextPath}/image/footer.jpg"
					width="950" height="52" alt="我们的优势" title="我们的优势" />
			</div>
		</div>
		<div class="span24">
			<ul class="bottomNav">
				<li><a>关于我们</a> |</li>
				<li><a>联系我们</a> |</li>
				<li><a>招贤纳士</a> |</li>
				<li><a>法律声明</a> |</li>
				<li><a>友情链接</a> |</li>
				<li><a target="_blank">支付方式</a> |</li>
				<li><a target="_blank">配送方式</a> |</li>
				<li><a>服务声明</a> |</li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div class="span24">
			<div class="copyright">Copyright © 2005-2013 Mango商城 版权所有</div>
		</div>
	</div>
</body>
</html>