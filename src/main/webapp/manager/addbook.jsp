<%@page import="com.lauguobin.www.service.*,com.lauguobin.www.po.*,java.util.List"%>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>图书馆后台</title>
		<base href="<%=basePath%>">
		<link rel="shortcut icon" href="assets/images/gt_favicon.png">
		
		<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700"> 
		<link rel="stylesheet" href="assets/css/bootstrap.min.css">
		<link rel="stylesheet" href="assets/css/font-awesome.min.css">
	
		<!-- Custom styles for our template -->
		<link rel="stylesheet" href="assets/css/bootstrap-theme.css" media="screen" >
		<link rel="stylesheet" href="assets/css/main.css"> 
	
		<link rel="stylesheet" href="assets/css/table.css">
	</head>
	
	<body class="home">
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top headroom" >
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
				<a class="navbar-brand" href="home.jsp">Library System</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right">
					<li class="active"><a href="manager/librarymanage.jsp">书籍</a></li>
					<li><a href="manager/addbook.jsp">上架新书</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">用户页面 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="manager/userlist.jsp">查看用户信息</a></li>
							<li><a href="manager/log.jsp">查看用户日志</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">审核信息 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="manager/audit.jsp">注册信息</a></li>
							<li><a href="manager/borrowManage.jsp">借阅信息</a></li>
						</ul>
					</li>
					<li><a class="btn" href="signin.jsp">退出</a></li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
	</div> 

	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="home.jsp">Home</a></li>
			<li class="active">上架新书</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h5 class="page-title"></h5>
				</header>
				
				<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
					<div class="panel panel-default">
						<div class="panel-body">
							<h3 class="thin text-center">此处输入您的书籍信息</h3>
							<p class="text-center text-muted">根据提示信息进行。</p>
							<hr>
							
							<form action = "BookServlet" enctype="multipart/form-data" method = "post">
								<div class="top-margin">
									<label>书本索取号 - Id <span class="text-danger">*</span></label>
									<input type = "text" name = "bookid"  class="form-control"placeholder = "在此输入新书索引号">
								</div>
								<div class="top-margin">
									<label>书本名称 - Book Name <span class="text-danger">*</span></label>
									<input type = "text" name = "bookname"  class="form-control" placeholder = "在此输入书本名称">
								</div>
								<div class="top-margin">
									<label>作者 - Author <span class="text-danger">*</span></label>
									<input type = "text" name = "author"  class="form-control" placeholder = "在此输入作者信息">
								</div>
								<div class="top-margin">
									<label>库存 - Amont <span class="text-danger">*</span></label>
									<input type = "text" name = "amont"  class="form-control" placeholder = "在此输入库存">
								</div>
								<div class="top-margin">
									<label>上传图片 - Picture <span class="text-danger">*</span></label>
									<input type = "file"  class="form-control" name = "file"  >
								</div>								
								<hr>

								<div class="row">
									<div class="col-lg-8">
										<b class="text-danger"><%if(request.getAttribute("error")!=null){ %><%=request.getAttribute("error")%><% } %></b>
									</div>
									<div class="col-lg-4 text-right">
										<button class="btn btn-action" type="submit">提交信息</button>
									</div>
								</div>
							</form>
						</div>
					</div>

				</div>
				
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	

	
<%-- 	<div>
		<table>
		<tr><th>添加这本书的信息</th></tr>
		<tr><td>
			<form action = "BookServlet" enctype="multipart/form-data" method = "post">
				<p style = "color:red;"><% if(request.getAttribute("error")!=null)%><%=request.getAttribute("error") %></p>
				<p>索引号&nbsp;&nbsp;&nbsp;
					<input type = "text" name = "bookid" class = "aboutbook" placeholder = "在此输入新书索引号">
				</p>
				<p>书本名称
						<input type = "text" name = "bookname" class = "aboutbook"placeholder = "在此输入书本名称">
				</p>
				<p>作者&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type = "text" name = "author" class = "aboutbook"placeholder = "在此输入作者信息">
				</p>
				<p>库存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type = "text" name = "amont" class = "aboutbook"placeholder = "在此输入库存">
				</p>
						上传图片
						<input type = "file" class = "aboutbook" name = "file" style = "width:1250px;" >
				<p>
				<button type = "submit" >提交信息</button>
			</form>
		</td></tr>
		</table>
	</div> --%>

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
								<b><a href="signup.html"></a>登录</b>
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
		
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets/js/headroom.min.js"></script>
	<script src="assets/js/jQuery.headroom.min.js"></script>
	<script src="assets/js/template.js"></script>
</body>
</html>