package com.lauguobin.www.view.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lauguobin.www.service.FavouratesService;

/**
 * 向后台发出珊瑚收藏数据的请求
 * Servlet implementation class DeleteFavourateServlet
 */
@WebServlet("/DeleteFavourateServlet")
public class DeleteFavourateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFavourateServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		int userId = (int)request.getSession().getAttribute("userId");
		String id = request.getParameter("id");
		if(id != null)
		{
			new FavouratesService().deleteFavourte(userId, id);
			request.getSession().setAttribute("favourates", new FavouratesService().showUserFavourates(userId));
			response.sendRedirect("student/favourates.jsp");
			return ;
		}
		else
		{
			response.sendRedirect("student/favourates.jsp");
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
