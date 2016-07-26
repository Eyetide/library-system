package com.lauguobin.www.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.lauguobin.www.service.UserService;
import com.lauguobin.www.util.Judge;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String identify = request.getParameter("identify");
		String error = "";
		if(username==null && password==null && repassword==null)
		{
			response.sendRedirect("regist.jsp");
			return ;
		}
		
		if(!Judge.isUserName(username))
		{
			error = "用户名称错误";
			request.setAttribute("error",error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		if(password.length()<6||password.length()>18||repassword.length()<6||repassword.length()>18)
		{
			error = "密码长度必须位于6 - 18 位！";
			request.setAttribute("error",error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		if(Judge.isChinese(password))
		{
			error = "密码不能输入中文！";
			request.setAttribute("error",error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		if(!password.equals(repassword))
		{
			error = "两次密码不一致！";
			request.setAttribute("error",error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		
		if(identify==null)
		{
			error = "选择身份！";
			request.setAttribute("error",error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
		}
		
		if("".equals(error))
		{	
			try
			{
				if(!new UserService().handleUser(username, repassword, identify))
				{
					error = "用户名已存在";
					request.setAttribute("error", error);
					request.getRequestDispatcher("regist.jsp").forward(request, response);
					return ;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			response.sendRedirect("index.jsp");
			return ;
		}
		else
		{
			request.setAttribute("error", error);
			request.getRequestDispatcher("regist.jsp").forward(request, response);
			return ;
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
