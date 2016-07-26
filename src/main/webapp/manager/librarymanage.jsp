<%@page import="com.lauguobin.www.po.*,java.util.*"%>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<% 
@SuppressWarnings("unchecked")
List<Book> list = (List<Book>)request.getAttribute("bookList");
if(list == null)
{
	request.getRequestDispatcher("../LibraryShowServlet").forward(request, response);
	return ;
}
request.setCharacterEncoding("utf-8");
String tokenValue = new Date().getTime()+"";
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
	
<%-- 	<form action = "SearchBookSerblet" method = "post">
		<p>
			<input type = "text" name = "search" class = "search"  value = "<%=request.getParameter("search") %>"style = "width:500px;color:#5b5b5b;" placeholder = "输入图书序列号、图书名称或者作者名称">
			<input type = "hidden" name = "token" value = "<%=tokenValue%>">
			<%session.setAttribute("token", tokenValue); %>
			<button type = "submit"  class = "search">点我搜索</button>
		</p>
	</form> --%>
	
	<header id="head" class="secondary"></header>

	<!-- container -->
	<div class="container">

		<ol class="breadcrumb">
			<li><a href="home.jsp">Home</a></li>
			<li class="active">图书列表</li>
		</ol>

		<div class="row">
			
			<!-- Article main content -->
			<article class="col-xs-12 maincontent">
				<header class="page-header">
					<h5 class="page-title"></h5>
										
<%-- 	<form action = "SearchBookSerblet" method = "post">
		<p>
			<input type = "text" name = "search" class = "search"  value = "<%=request.getParameter("search") %>"style = "width:500px;color:#5b5b5b;" placeholder = "输入图书序列号、图书名称或者作者名称">
			<input type = "hidden" name = "token" value = "<%=tokenValue%>">
			<%session.setAttribute("token", tokenValue); %>
			<button type = "submit"  class = "search">点我搜索</button>
		</p>
	</form> --%>
		</header>
			<table>
				<tr><th>序列号</th><th>书本名称</th><th>图片</th><th>作者</th><th>现有库存</th><th>详细</th></tr>
				<% if(list.isEmpty())
				{
					%>
					<tr class="text-danger"> <td>没有书籍！</td></tr>
					<%
				}
				for(Book b : list) {%>
				<tr>
					<td><%=b.getBookid()%></td>
					<td><%=b.getBookName() %></td>
					<td><img src='<%=b.getBookid()%>.jpg?random=<%=Math.random()%>' width = "120px" height = "160px"></td>
					<td><%=b.getAuthor() %></td>
					<td><%=b.getAmont() %></td>
					<td>
						<form action = "manager/updatebook.jsp" method = "post">
							<input type = "hidden"  name = "bookid" value = <%=b.getBookid() %> >
							<input type = "hidden"  name = "bookname" value = '<%=b.getBookName() %>' >
							<input type = "hidden"  name = "author" value = '<%=b.getAuthor() %>' >
							<input type = "hidden"  name = "amont" value = <%=b.getAmont() %> >
							<input type = "submit" name = "update"  value = "修改信息" >
							&nbsp;&nbsp;&nbsp;
							<input type = "button" name = "delete" value = "下架书本" onclick="javascript:window.location.href='DeleteBookServlet?bookid=<%=b.getBookid()%>';">
						</form>
					</td>
				</tr>
			    <%} %>
			    </table>
			</article>
			<!-- /Article -->

		</div>
	</div>	<!-- /container -->
	
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