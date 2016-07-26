package com.lauguobin.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lauguobin.www.po.Log;
import com.lauguobin.www.service.LogService;

/**
 * Servlet implementation class LogServlet
 */
@WebServlet("/LogShow")
public class LogShow extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogShow() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String identify = (String) session.getAttribute("identify");
		int userId = (int) session.getAttribute("userId");
		if("student".equals(identify))
		{
			List<Log> list = new LogService().showUserLogs(userId);
			request.setAttribute("logs", list);
			request.getRequestDispatcher("student/loginfo.jsp").forward(request, response);
			return ;
		}
		else if("manager".equals(identify))
		{
			List<Log> list = new LogService().showLogs();
			request.setAttribute("logs", list);
			request.getRequestDispatcher("manager/log.jsp").forward(request, response);
			return ;
		}
		else
		{
			response.sendRedirect("index.jsp");
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
