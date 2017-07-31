<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 	<base <%=basePath %>> --%>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link href="<%=basePath%>js/jquery.searchableSelect.css" rel="stylesheet" type="text/css">
    <script src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script src="<%=basePath%>js/jquery.searchableSelect.js"></script>
</head>
<body>
	<div style="width: 800px; margin: 0 auto;">
		<form:form action="${pageContext.request.contextPath }/myuser/update" method="POST" modelAttribute="myUser" >
		<c:if test="${myUser.id != null }">
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		<!-- <input type="hidden" name="_method" value="PUT"/> -->
		姓名：<form:input path="name" />
			<br><br>
			<form:errors path="name" />
		年龄：<form:input path="age" />
			<br><br>
			<form:errors path="age" />
		生日：<form:input path="birthday" />
			<br><br>
			<form:errors path="birthday" />
		血型：<form:select path="bloodType">
				<form:option value="A">A型</form:option>
				<form:option value="B">B型</form:option>
				<form:option value="AB">AB型</form:option>
				<form:option value="O">O型</form:option>
				<form:option value="other">不详</form:option>
			</form:select>	
			<br><br>				
<%-- 		血型：<form:input path="bloodType" />		
			<br><br>
			<form:errors path="bloodType" /> --%>
 		省份：<form:input path="province" />
			<br><br>
			<form:errors path="province" />
		城市：<form:input path="city" />
			<br><br>
			<form:errors path="city" />
		辖区：<form:input path="districtOrCounty" />
			<br><br>
			<form:errors path="districtOrCounty" />
		社区：<form:input path="community" />
			<br><br>
			<form:errors path="community" />			
		街道：<form:input path="street" />
			<br><br>
			<form:errors path="street" />
		地址：<form:input path="location" />
			<br><br>
			<form:errors path="location" />		
			
		性别：<form:select path="gender">
				<form:option value="1">男</form:option>
				<form:option value="2">女</form:option>
			</form:select>	
			<br><br>
<%-- 		性别：<form:input path="gender" />
			<br><br> --%>
			<form:errors path="gender" />
		Id：<form:input path="idCard" />
			<br><br>
			<form:errors path="idCard" />

		婚否：<form:input path="maritalStatus" />
			<br><br>
			<form:errors path="maritalStatus" />

		身高：<form:input path="stature" />
			<br><br>
			<form:errors path="stature" />

		电话：<form:input path="telephone" />
			<br><br>
			<form:errors path="telephone" />	

		提交：<input type="submit" value="提交">				
		</form:form>

	</div>

   <!--  <select>
      <option value="jQuery插件库">jQuery插件库</option>
      <option value="BlackBerry">BlackBerry</option>
    </select> -->
    <script>
		$(function(){
			$('select').searchableSelect();	
		});
    </script>

	<%-- 	<form action="/myuser/update">
		提交：<input type="submit" >
	</form> --%>
</body>
</html>

