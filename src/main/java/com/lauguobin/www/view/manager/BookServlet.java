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
 * Servlet 添加图书
 */
@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private ServletContext application;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory(); //将请求消息实体中的每一个项目封装成单独的DiskFileItem (FileItem接口的实现) 对象
		ServletFileUpload upload = new ServletFileUpload(factory); //构造一个实例，并根据参数指定的FileItemFactory 对象，设置 fileItemFactory属性。
		Book book = new Book();
		try
		{
			List<?> items = upload.parseRequest(request);  //表单中每一个HTML标签提交的数据封装成一个FileItem对象，然后以List列表的形式返回。
			Iterator<?> itr = items.iterator();  //迭代器，遍历list元素
			while (itr.hasNext()) 
			{
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) 
				{
					if("".equals(item.getString("UTF-8")))
					{
						request.setAttribute("error", " * 请输入相应信息");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isInteger(item.getString("UTF-8"))&&item.getFieldName().equals("bookid"))
					{
						request.setAttribute("error", " * 请输入正确的图书号");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isName(item.getString("UTF-8"))&&item.getFieldName().equals("bookname"))
					{
						request.setAttribute("error", " * 请输入正确的图书名称");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isName(item.getString("UTF-8"))&&item.getFieldName().equals("author"))
					{
						request.setAttribute("error", " * 请输入正确的作者名称");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
						return ;
					}
					
					if(!Judge.isInteger(item.getString("UTF-8"))&&item.getFieldName().equals("amont"))
					{
						request.setAttribute("error", " * 请输入正确的库存");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
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
					if (item.getName() != null && !item.getName().equals("")) 
					{
						if(!item.getContentType().contains("image"))
						{
							request.setAttribute("error", " * 请确保上传图片！");
							request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
							return ;
						}
						BookService bs = new BookService();
						if(!bs.addBookHandler(book,item.getName(),item,application.getRealPath("/")))
						{
							request.setAttribute("error", " * 图书序列号重复或者图书信息重复！");
							request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
							return ;
						}
					}
					else
					{
						request.setAttribute("error", " * 请上传图片！");
						request.getRequestDispatcher("manager/addbook.jsp").forward(request, response);
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
		
		response.sendRedirect("manager/librarymanage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

	public void init(ServletConfig config) 
	{
		 application = config.getServletContext();
	}
}
