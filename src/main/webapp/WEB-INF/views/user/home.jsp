<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<frameset bordercolor="red" frameborder="1px" rows="103,*,43" frameborder=0 border="0" framespacing="0">
	<frame 
		name="topFrame" scrolling="NO" noresize>
	<frameset cols="200,*" frameborder="1px" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/menu.jsp"
			name="leftFrame"  scrolling="YES" noresize>
		<frame src="${pageContext.request.contextPath}/regist.jsp"
			name="mainFrame">
	</frameset>
	<frame 
		name="bottomFrame" scrolling="NO" noresize>
</frameset>
</html>