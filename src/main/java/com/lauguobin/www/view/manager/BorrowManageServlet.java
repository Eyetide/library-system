package com.lauguobin.www.view.manager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.UserService;

/**
 * 处理管理图书借阅的信息
 * Servlet implementation class BorrowManageServlet
 */
@WebServlet("/BorrowManageServlet")
public class BorrowManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowManageServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String bookid = request.getParameter("bookid");
		String flag = request.getParameter("flag");
		int userId = new UserService().getUserId(username);
		if("refuse".equals(flag))
		{
			int id = Integer.parseInt(bookid);
			new BorrowReturnService().toRefuse(userId, id);
			response.sendRedirect("BorrowRequestServlet");
			return ;
		}
		else if("accept".equals(flag))
		{
			int id = Integer.parseInt(bookid);
			new BorrowReturnService().toAccept(userId, id);
			response.sendRedirect("BorrowRequestServlet");
			return ;
		}
		else
		{
			response.sendRedirect("BorrowRequestServlet");
			return ;
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
