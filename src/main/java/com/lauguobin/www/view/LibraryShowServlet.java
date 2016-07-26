package com.lauguobin.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.service.BookService;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;

/**
 * 这是一个专门用来获取所有书籍信息的servlet，每次显示主页面都会经过它
 */
@WebServlet("/LibraryShowServlet")
public class LibraryShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryShowServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String identify = (String) session.getAttribute("identify");
		int userId = (int) session.getAttribute("userId");
		try
		{
			List<Book> list =  new BookService().showBooks();
			request.setAttribute("bookList", list);
			if("manager".equals(identify))
			{
				request.getRequestDispatcher("manager/librarymanage.jsp").forward(request, response);
				return ;
			}
			else if("student".equals(identify))
			{
				request.getRequestDispatcher("student/librarypage.jsp").forward(request, response);
				return ;
			}
			else 
			{
				response.sendRedirect("index.jsp");
				return ;
			}
		}
		catch (Exception e)
		{
			response.sendRedirect("index.jsp");
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
