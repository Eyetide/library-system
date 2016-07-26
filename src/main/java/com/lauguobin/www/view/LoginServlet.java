package com.lauguobin.www.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lauguobin.www.po.User;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;
import com.lauguobin.www.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String identify = request.getParameter("identify");
		String page = request.getParameter("page");
		if(username == null || password == null || identify == null)
		{
			String error = "请输入完整的信息";
			request.setAttribute("error", error);
			if("1".equals(page))
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return ;
			}
			else if("2".equals(page))
			{
				request.getRequestDispatcher("signin.jsp").forward(request, response);
				return ;
			}
		}
		UserService userService = new UserService();
		User user = new User(username, password, identify);
		if(userService.handleLogin(user))
		{
			int userId = userService.getUserId(username);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			session.setAttribute("username", username);
			session.setAttribute("identify", identify);
			if("student".equals(identify))
			{
				session.setAttribute("borrowlist", new BorrowReturnService().showAllBorrow(userId));
				session.setAttribute("favourates", new FavouratesService().showUserFavourates(userId));
			}
			response.sendRedirect("home.jsp");
			return ;
		}
		else
		{
			String error = "登陆错误，请检查用户名、密码和身份";
			request.setAttribute("error", error);
			if("1".equals(page))
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return ;
			}
			else if("2".equals(page))
			{
				request.getRequestDispatcher("signin.jsp").forward(request, response);
				return ;
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
