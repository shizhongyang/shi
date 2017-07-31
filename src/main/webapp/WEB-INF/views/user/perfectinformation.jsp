<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>完善信息页面</title>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/register.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("input").attr("disabled", true);

	});

	function edit() {
		$("input").attr("disabled", false);
	}

	function checkForm() {
		// 校验用户名:
		var username = document.getElementById("username").value;
		if (username == '') {
			alert("用户名不能为空!");
			return false;
		}
		var string = document.getElementById("span1").innerHTML;
		// 校验密码:
		var password = document.getElementById("password").value;
		if (password == '') {
			alert("密码不能为空!");
			return false;
		}

		// 校验确认密码
		var repassword = document.getElementById("repassword").value;
		if (password != repassword) {
			alert("两次密码不一致!");
			return false;
		}
		
		$("input").attr("disabled", true);
	}

	function checkCode() {
		//alert("----2");
		var checkcode = $("#checkcode").val();
		//alert("----2"+checkcode);
		$("#span2").load(
				"${basePath}/myuser/checkCode?" + new Date().getTime(), {
					'checkcode' : checkcode
				}, function(responseTxt, statusTxt, xhr) {
					if (statusTxt == "success") {
						// alert("外部内容加载成功!");
						// alert(responseTxt) 
						str = responseTxt;
					}
					if (statusTxt == "error")
						alert("Error: " + xhr.status + ": " + xhr.statusText);
				});
		//alert(str);
	}
	function change() {
		var img = document.getElementById("checkImg");
		img.src = "${basePath}/img?" + new Date().getTime();
	}

	function changeImg() {
		var filePath = $("#file").val();

		var src = $("#img1")[0].src
		$("#img1").attr('src', filePath);
	}
</script>
</head>
<body>
	<div class="container header">
		<%-- <div class="span5">
			<div class="logo">
				<a href="http://localhost:8080/mango/"> <img
					src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif"
					alt="坚如磐石"></a>
			</div>
		</div>
		<div class="span9">
			<div class="headerAd">
				<img src="${pageContext.request.contextPath}/image/header.jpg"
					width="320" height="50" alt="正品保障" title="正品保障">
			</div>
		</div> --%>
				
		
		<%@ include file="header.jsp" %>

	</div>


	<div class="container register">
		<div class="span24">
			<div class="wrap">
				<div class="main clearfix">
					<div class="title">
						<strong>用户注册</strong>USER REGISTER
					</div>
					<div style="background-color: red;" >

						<form id="registerForm" action="${basePath }myuser/update"
							method="post" novalidate="novalidate"
							onsubmit="return checkForm();">
							<table>
								<tbody>
									<tr>
										<th>更改头像:</th>
										<td style="margin-left: 100px"><input type="file"
											id="file" name="file" class="text" maxlength="20"
											onchange="changeImg()" /></td>
										<tr>
											<c:if test="${myUser.imgAvatar!=null }">
												<td align="center"><img id="img1"
													style="margin-left: 100px"
													src="${basePath}${myUser.imgAvatar }" width="42"
													height="42"></img></td>
											</c:if>
										</tr>
									</tr>
									
									<c:if test="${myUser.id!=null }">
										<tr>
											<th>id:</th>
											<td><input type="text" id="userid" name="id"
												value="${myUser.id }" class="text" maxlength="100"
												onblur="checkUserName()" /></td>
										</tr>
									</c:if>
									

									<tr>
										<th><span class="requiredField">*</span>用户名:</th>
										<td><input type="text" id="username" name="name"
											value="${myUser.name }" class="text" maxlength="20"
											onblur="checkUserName()" /><span id="span1"> </span></td>
									</tr>
									<tr>
										<th><span class="requiredField">*</span>年&nbsp;&nbsp;龄:</th>
										<td><input type="text" id="age" name="age"
											value="${myUser.age }" class="text" maxlength="20"
											autocomplete="off" /><span> </span></td>
									</tr>
									<tr>
										<th><span class="requiredField">*</span>血型:</th>

										<td><input type="text" id="bloodType"
											value="${myUser.bloodType }" name="bloodType" class="text"
											maxlength="20" autocomplete="off" /></td>

									</tr>
									<tr>
										<th><span class="requiredField">*</span>E-mail:</th>
										<td><input type="text" id="email" name="email"
											value="${myUser.email }" class="text" maxlength="100" /> </span></td>
									</tr>
									<tr>
										<th><span class="requiredField">*</span>电话:</th>
										<td><input type="text" id="phone" name="telephone"
											value="${myUser.telephone }" class="text" maxlength="100"></td>
									</tr>

									<tr>
										<th><span class="requiredField">*</span>生日:</th>
										<td><input type="text" id="birthday" name="birthday"
											value="${myUser.birthday }" class="text" maxlength="100"></td>
									</tr>
									<!-- <tr>
									<th>姓名:</th>
									<td><input type="text" name="name" class="text"
										maxlength="200"></td>
								</tr> -->
									<tr>
										<th>性别:</th>
										<td><span class="fieldSet"> <c:if
													test="${myUser.gender==1 }">
													<label><input type="radio" name="gender" value="1"
														checked="checked">男 </label>
													<label> <input type="radio" name="gender" value="2">女
													</label>
												</c:if> <c:if test="${myUser.gender==2 }">
													<label><input type="radio" name="gender" value="1">男
													</label>
													<label> <input type="radio" name="gender" value="2"
														checked="checked">女 </label>
												</c:if> <c:if test="${myUser.gender==null }">
													<label><input type="radio" name="gender" value="1">男
													</label>
													<label> <input type="radio" name="gender" value="2">女
													</label>
												</c:if>
										</span></td>
									</tr>
									<tr>
										<th>地址:</th>
										<td><input type="text" name="location"
											value="${myUser.location }" class="text" maxlength="200"></td>
									</tr>
									<tr style="display: none;">
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
										<td><input type="submit" value="提交完善信息"></td>
									</tr>

								</tbody>
							</table>
						</form>

 						<button value="编辑" onclick="edit()">编辑</button>
					</div>
					<div class="login" >
						<div class="ad">
							<dl>
								<dt>注册即享受</dt>
								<dd>正品保障、正规发票</dd>
								<dd>货到付款、会员服务</dd>
								<dd>自由退换、售后上门</dd>
							</dl>
						</div>
						<dl>
							<dt>已经拥有账号了？</dt>
							<dd>
								立即登录即可体验在线购物！ <a href="./会员登录.htm">立即登录</a>
							</dd>
						</dl>
					</div>
				</div>
			</div>
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
	<div id="_my97DP"
		style="position: absolute; top: -1970px; left: -1970px;">
		<iframe style="width: 190px; height: 191px;"
			src="./会员注册 - Powered By Mango Team_files/My97DatePicker.htm"
			frameborder="0" border="0" scrolling="no"></iframe>
	</div>
</body>
</html>