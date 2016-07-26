package com.lauguobin.www.view.student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lauguobin.www.po.Log;
import com.lauguobin.www.service.LogService;

/**
 * 传入搜索条件，获取所有搜索到的学生借阅日志信息
 * Servlet implementation class StuLogShow
 */
@WebServlet("/StuLogShow")
public class StuLogShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StuLogShow() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String string = request.getParameter("search");
		String[] search = string.split("\\+");
		String username = (String) request.getSession().getAttribute("username");
		try
		{
			List<Log> list = new LogService().showStuSearchLogs(search,username);
			request.setAttribute("logs", list);
			request.getRequestDispatcher("student/loginfo.jsp").forward(request, response);
		}
		catch (ClassNotFoundException | SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
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
