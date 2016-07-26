package com.lauguobin.www.view.manager;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.service.BookService;
import com.lauguobin.www.util.Judge;

/**
 * 更新书本信息
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		application = config.getServletContext();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		BookService bs = new BookService();
		Book book = new Book();
		try 
		{
			List<?> items = upload.parseRequest(request);
			Iterator<?> itr = items.iterator();
			while (itr.hasNext()) 
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) 
				{
					if("".equals(item.getString("UTF-8")))
					{
						request.setAttribute("error", " * 请输入相应信息");
						request.getRequestDispatcher("manager/updatebook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isName(item.getString("UTF-8"))&&item.getFieldName().equals("bookname"))
					{
						request.setAttribute("error", " * 请输入正确的图书名称");
						request.getRequestDispatcher("manager/updatebook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isName(item.getString("UTF-8"))&&item.getFieldName().equals("author"))
					{
						request.setAttribute("error", " * 请输入正确的作者名称");
						request.getRequestDispatcher("manager/updatebook.jsp").forward(request, response);
						return ;
					}
					
					if( (!Judge.isInteger(item.getString("UTF-8"))&&item.getFieldName().equals("amont") ) || ( item.getString("UTF-8").length()>10&&item.getFieldName().equals("bookid") ) )
					{
						request.setAttribute("error", " * 请输入正确的库存");
						request.getRequestDispatcher("manager/updatebook.jsp").forward(request, response);
						return ;
					}
					
					if(item.getFieldName().equals("bookid"))
						book.setBookid(Integer.parseInt(item.getString()));
					if(item.getFieldName().equals("amont"))
						book.setAmont(Integer.parseInt(item.getString()));
					if(item.getFieldName().equals("bookname"))
						book.setBookName(item.getString("UTF-8"));
					if(item.getFieldName().equals("author"))
						book.setAuthor(item.getString("UTF-8"));
				}
				else
				{
					//如果有图片
					if (item.getName() != null && !item.getName().equals("")) 
					{
						if(!item.getContentType().contains("image"))
						{
							request.setAttribute("error", " * 请确保上传图片！");
							request.getRequestDispatcher("manager/updatebook.jsp").forward(request, response);
							return ;
						}
						bs.updateBookHandler(book, item.getName(), item, application.getRealPath("/"));
					}
					else //如果没有图片，仅更新信息
					{
						bs.updateBookHandler(book);
						request.getRequestDispatcher("LibraryShowServlet").forward(request, response);
						return ;
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();  
			response.sendRedirect("manager/addbook.jsp");
			return ;
		}
		request.getRequestDispatcher("LibraryShowServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
