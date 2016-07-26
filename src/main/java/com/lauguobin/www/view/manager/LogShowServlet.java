package com.lauguobin.www.view.manager;

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
 * 给后台传递搜索条件，并获取后台搜索数据
 * 
 * Servlet implementation class LogShowServlet
 */
@WebServlet("/LogShowServlet")
public class LogShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogShowServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String string = request.getParameter("search");
		String[] search = string.split("\\+");
		try
		{
			List<Log> list = new LogService().showSearchLogs(search);
			request.setAttribute("logs", list);
			request.getRequestDispatcher("manager/log.jsp").forward(request, response);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
