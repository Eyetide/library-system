<%@ page language="Java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">   
		<title>图书管理系统</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">   
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<link rel="shortcut icon" href="assets/images/gt_favicon.png">
		
		<link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet"href="http://fonts.googleapis.com/css?family=PT+Sans:400,700">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">
	</head>
	
	<body id = "loginbody" >
        <div class="page-container">
            <h1>Login</h1>
            <form action="login" method="post">
                <input type="text" name="username" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <div>
                	学生<input type = "radio" class="radio"  name = "identify" value = "student">&nbsp;&nbsp;
					管理员<input type = "radio" class="radio"  name = "identify" value = "manager">
					<input type="hidden" name="page" value="1">
                </div>
                <div>
	                <button type="submit">登录</button>
	                <button type="button" onclick="javascript:window.location.href='regist.jsp';">注册</button>
                </div>
                <div class="error"><span>+</span></div>
                <div><c:out value="${error }"/></div>
            </form>
        </div>
 		
 		        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>
 		
	</body>
</html>