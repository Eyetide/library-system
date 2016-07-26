package com.lauguobin.www.view.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.service.BorrowReturnService;

/**
 * 获取一个登录用户所有的图书借阅信息
 * Servlet implementation class BorrowPageShow
 */
@WebServlet("/BorrowPageShow")
public class BorrowPageShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowPageShow() {    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		List<Book> list = new BorrowReturnService().showUsersBorrow((int)session.getAttribute("userId"));
		request.setAttribute("myborrowbook", list);
		request.getRequestDispatcher("student/borrowpage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
