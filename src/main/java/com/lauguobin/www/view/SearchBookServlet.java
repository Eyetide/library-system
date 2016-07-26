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

/**
 * Servlet implementation class SearchBookSerblet
 */
@WebServlet("/SearchBookSerblet")
public class SearchBookServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookServlet() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String string = request.getParameter("search");
		String identify = (String) session.getAttribute("identify");
		String token = (String)session.getAttribute("token");
		String tokenValue = request.getParameter("token");
		if(token == null || !tokenValue.equals(token) ||"".equals(string))
		{
			response.sendRedirect("LibraryShowServlet?search="+string);
			return ;
		}
		else
		{
			session.removeAttribute("token");
			try
			{
				String[] search = string.split("\\+");
				List<Book> list = new BookService().showSearchBooks(search);
				request.setAttribute("bookList", list);
				if(identify.equals("manager"))
				{
					request.getRequestDispatcher("manager/librarymanage.jsp").forward(request, response);
					return ;
				}
				else if(identify.equals("student"))
				{
					request.getRequestDispatcher("student/librarypage.jsp").forward(request, response);
					return ;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
				response.sendRedirect("LibraryShowServlet");
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
