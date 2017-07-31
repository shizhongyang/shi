<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!--  initial-scale=1  不缩放   width=device-width 物理设备的宽度 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>用户登录系统</title>
<link href="<%=basePath%>css/comm.css" rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
<!--  [if lt IE 9]> -->
<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link media="all" href="<%=basePath%>css/index_1.css" type="text/css" rel="stylesheet">
<style type="text/css">
.contailer {
	height: 100px;
	background-color: #fff;
	text-align: center;
	line-height: 100px;
}
</style>

</head>
<body>

	<div class="container">
		<div class="contailer">
			<h1 style="margin: 0 auto; margin-top: 50px;">用户登录系统</h1>
		</div>
	</div>
	<div class="row" style="margin-top: 20px;">
		<div class="center-block"
			style="width: 200px; background-color: #fff;">
			<div>
				<form action="/shi/myuser/login" method="post">
					<div class="form-group">
						<label class="sr-only" for="exampleInputAmount">Amount (in
							dollars)</label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-user"></span>
							</div>
							<input type="text" class="form-control" id="exampleInputAmount"
								placeholder="username">
						</div>
					</div>
					<div class="form-group">
						<label class="sr-only" for="exampleInputAmount">Amount (in
							dollars)</label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-lock"></span>
							</div>
							<input type="password" class="form-control"
								id="exampleInputAmount" placeholder="password">
						</div>
					</div>
					<div>
						<button type="submit" class="btn btn-primary col-md-5 pull-left">登录</button>
						<a class="btn btn-primary col-md-5 pull-right" role="button "
							href="myuser/registPage">注册</a>
					</div>

				</form>
			</div>

		</div>



	</div>


	<!-- 	<div class="row">
		<div class="center-block"
			style="width: 600px; background-color: #ccc;">
			<div>
				<form>
					<div class="form-group">
						<label class="sr-only" for="exampleInputAmount">Amount (in
							dollars)</label>
						<div class="input-group">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-user"></span>
							</div>
							<input type="text" class="form-control" id="exampleInputAmount"
								placeholder="Amount">
						</div>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox"> Check me out
						</label>
					</div>
					<button type="submit" class="btn btn-primary">Transfer
						cash</button>
				</form>
			</div>
		</div>
	</div> -->
</body>
</html>