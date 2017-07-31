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
<title>侧边栏目</title>
<link href="<%=basePath%>css/comm.css" rel="stylesheet" type="text/css">
<script src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<!-- 包含 bootstrap 样式表 -->
<link rel="stylesheet"
	href="https://apps.bdimg.com/libs/bootstrap/3.2.0/css/bootstrap.min.css">
<script
	src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link media="all" href="<%=basePath%>css/index_1.css" type="text/css" rel="stylesheet">
	
	
<script type="text/javascript">
	$(function() {
		//alert("加载完成");
		/* var el = document.createElement("a");
		document.body.appendChild(el);
		el.href = "input.jsp"; //url 是你得到的连接
		el.target = 'mainFrame'; //指定在新窗口打开
		el.click();
		document.body.removeChild(el); */
	})
	
	
</script>
	

</head>
<body>

	<div class="accordion" id="menu">
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapsea79b2472efa344fd946d41599634f2f1" title=""><i class="icon-chevron-down"></i>&nbsp;护工管理</a>
		    </div>
		    <div id="collapsea79b2472efa344fd946d41599634f2f1" class="accordion-body collapse in">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li class="active"><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurse/" target="mainFrame"><i class="icon-user icon-white"></i>&nbsp;护工管理</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseJudge/" target="mainFrame"><i class="icon-folder-open"></i>&nbsp;护工评价</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseReserve/nurseReserveStat" target="mainFrame"><i class="icon-th-list"></i>&nbsp;预约分账统计</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/integral/" target="mainFrame"><i class="icon-book"></i>&nbsp;积分查询</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/sys/nursingCompany/" target="mainFrame"><i class="icon-heart"></i>&nbsp;护理公司</a></li>
					</ul>
				</div>
		    </div>
		</div>
	
	
		
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse2a8db2f5b4d048c4a84be3e140076d25" title=""><i class="icon-chevron-right"></i>&nbsp;服务管理</a>
		    </div>
		    <div id="collapse2a8db2f5b4d048c4a84be3e140076d25" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseIncrementPackage/" target="mainFrame"><i class="icon-barcode"></i>&nbsp;增值套餐</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/elderlyproduct/goodsOrder/incrementOrder" target="mainFrame"><i class="icon-th-list"></i>&nbsp;增值订单</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseReserve/list" target="mainFrame"><i class="icon-eject"></i>&nbsp;护工预约订单</a></li>
					
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseReserve/nurseReserveStaChart" target="mainFrame"><i class="icon-film"></i>&nbsp;护理订单统计</a></li>
					</ul>
				</div>
		    </div>
		</div>
	
	
		<div class="accordion-group">
		    <div class="accordion-heading">
		    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu" href="#collapse77ad58abe2004934ad018da000e70196" title=""><i class="icon-chevron-right"></i>&nbsp;资讯管理</a>
		    </div>
		    <div id="collapse77ad58abe2004934ad018da000e70196" class="accordion-body collapse ">
				<div class="accordion-inner">
					<ul class="nav nav-list">
						<li><a href="http://community.nug-hospital.com:8085/communityhealthNew/admin/nurseservice/nurseInformation/" target="mainFrame"><i class="icon-cog"></i>&nbsp;护工资讯</a></li>
					</ul>
				</div>
		    </div>
		</div>

	</div>
</body>
</html>