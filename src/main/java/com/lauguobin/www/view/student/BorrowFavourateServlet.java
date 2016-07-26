package com.lauguobin.www.view.student;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;

/**
 * Servlet implementation class BorrowFavourateServlet
 * 用于处理发出借阅请求或者收藏请求
 */
@WebServlet("/BorrowFavourateServlet")
public class BorrowFavourateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowFavourateServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		request.setCharacterEncoding("utf-8");
		String bookid = request.getParameter("bookid");
		int userId = (int) request.getSession().getAttribute("userId");
		String bookname = request.getParameter("bookname");
		String author = request.getParameter("author");
		String flag = request.getParameter("flag");
		if(flag == null || bookid == null || bookname == null || author == null)
		{
			request.getRequestDispatcher("LibraryShowServlet").forward(request, response);
			return ;
		}
		
		int id = Integer.parseInt(bookid);
		if("collect".equals(flag))
		{
			new FavouratesService().collectBook(userId, id);
			//更新session里的收藏夹信息
			request.getSession().setAttribute("favourates", new FavouratesService().showUserFavourates(userId));
			request.getRequestDispatcher("LibraryShowServlet").forward(request, response);
			return ;
		}

		if("borrow".equals(flag))
		{
			new BorrowReturnService().toBorrow(userId, id, bookname, author);
			request.getSession().setAttribute("borrowlist", new BorrowReturnService().showAllBorrow(userId));
			request.getRequestDispatcher("LibraryShowServlet").forward(request, response);
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
