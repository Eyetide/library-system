<%@ page language="Java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport"    content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author"      content="Sergey Pozhilov (GetTemplate.com)">
	
	<title>注册</title>

	<link rel="shortcut icon" href="assets/images/gt_favicon.png">
	
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">

	<!-- Custom styles for our template -->
	<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
	<link rel="stylesheet" href="assets/css/main.css">

	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="index.jsp">Library System</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a class="btn" href="signin.jsp">登录 / 注册</a></li>
				</ul>
			</div>
		</div>
	</div> 

	<header id="head" class="secondary"></header>

	<div class="container">

		<ol class="breadcrumb">
			<li><a href="index.jsp">主页</a></li>
			<li class="active">用户页面</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h1 class="page-title">Registration</h1>
				</header>
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">注册一个新用户</h3>
							<p class="text-center text-muted">如果您已经注册，请<a href="signin.jsp">登录</a>. 如果没有账号，请此页面输入您的相关信息以注册一个账号. </p>
							<hr>

							<form action="regist" method="post">
								<div class="top-margin">
									<label>User Name - 用户名  </label><span class="text-danger">*</span>
									<input type="text" class="form-control" name="username" placeholder="Username">
								</div>

								<div class="row top-margin">
									<div class="col-sm-6">
										<label>Password - 密码  <span class="text-danger">*</span></label>
										<input type="password" class="form-control" name="password" placeholder="Password">
									</div>
									<div class="col-sm-6">
										<label>Confirm Password - 确认密码  <span class="text-danger">*</span></label>
										<input type="password" class="form-control" name="repassword" placeholder="Repassword"]>
									</div>
								</div>
								
								<div class="top-margin">
									<div class="row top-margin">
										<div class="col-sm-6">
										<label>Identify - 身份选择<span class="text-danger">*</span></label>
										<div style="margin-top: 10px;">
											<p class="text-center text-muted">
												学生<input type = "radio"  name = "identify" value = "student">
												管理员<input type = "radio"  name = "identify" value = "manager">
											</p>
										</div>
										</div>
										
										<div class="col-sm-6">
										<label>Code - 验证码  </label><span class="text-danger">*</span>
										<p class="text-center text-muted">
			           						<input type="text" id="captcha" name="code" class="form-control" placeholder="点击图片更改" />
										</p>
										</div>
									</div>
								</div>
								
								<div class="top-margin">
									<b class="text-danger"><c:out value="${error }"/></b>
								</div>

								<hr>
								<div class="row">
									<div class="col-lg-8">
									<img id="captchaImage"  src="captcha"/>
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">提交</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				
			</article>

		</div>
	</div>	
	

	<footer id="footer" class="top-space">

		<div class="footer1">
			<div class="container">
				<div class="row">
					
					<div class="col-md-3 widget">
						<h3 class="widget-title">我</h3>
						<div class="widget-body">
							<p>+86 156 2517 8801<br>
								<a href="mailto:#">Eyetide@live.com</a><br>
								<br>
								兴宁 梅州 广东
							</p>	
						</div>
					</div>

					<div class="col-md-3 widget">
						<h3 class="widget-title">关注我</h3>
						<div class="widget-body">
							<p class="follow-me-icons clearfix">
								<a href=""><i class="fa fa-twitter fa-2"></i></a>
								<a href=""><i class="fa fa-dribbble fa-2"></i></a>
								<a href=""><i class="fa fa-github fa-2"></i></a>
								<a href=""><i class="fa fa-facebook fa-2"></i></a>
							</p>	
						</div>
					</div>

					<div class="col-md-6 widget">
						<h3 class="widget-title">关于项目</h3>
						<div class="widget-body">
							<p>二轮考核项目2.0版本，基于maven以及mybatis框架重构。</p>
							<p>重构了整个架构，新增了一些功能，优化页面。</p>
						</div>
					</div>

				</div> 
			</div>
		</div>

		<div class="footer2">
			<div class="container">
				<div class="row">
					
					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="simplenav">
								<a href="#">主页</a> | 
								<b><a href="signin.jsp"></a>登录</b>
							</p>
						</div>
					</div>

					<div class="col-md-6 widget">
						<div class="widget-body">
							<p class="text-right">
								Copyright &copy; 2016, gb. 由<a href="http://gitlab.topview.com/" rel="designer">TopView</a>提供技术支持
							</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</footer>


	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/myscript.js"></script>
	<script src="assets/js/template.js"></script>
</body>
</html>