package com.lauguobin.www.view;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;
import com.lauguobin.www.po.Log;
import com.lauguobin.www.po.User;
import com.lauguobin.www.service.BookService;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.LogService;
import com.lauguobin.www.service.UserService;
import com.lauguobin.www.util.Judge;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerViewController
{
	@Autowired
	private UserService userService;
	@Autowired
	private BorrowReturnService borrowReturnService;
	@Autowired
	private BookService bookService;
	@Autowired
	private LogService logService;
	
	//AuditServlet
	@RequestMapping("/tempuser")
	public ModelAndView tempUserHandler()
	{
		ModelAndView mv = new ModelAndView("manager/audit");
		List<User> list = userService.showUsersRequests();
		mv.addObject("registRequest", list);
		return mv;
	}
	
	//BorrowManageServlet
	@RequestMapping("/borrowmanage")
	public ModelAndView borrowManageHandler(String username,String bookid,String flag)
	{
		ModelAndView mv = new ModelAndView("redirect:borrowrequest");
		int userId = userService.getUserId(username);
		if("refuse".equals(flag))
		{
			int id = Integer.parseInt(bookid);
			borrowReturnService.toRefuse(userId, id);
		}
		else if("accept".equals(flag))
		{
			int id = Integer.parseInt(bookid);
			borrowReturnService.toAccept(userId, id);
		}
		return mv;
	}
	
	@RequestMapping("/borrowrequest")
	public ModelAndView borrowRequestHandler() throws IOException
	{
		ModelAndView mv = new ModelAndView("manager/borrowManage");
		List<BorrowInfo> list = borrowReturnService.showAllTempBorrow();
		mv.addObject("borrowRequest", list);
		return mv;
	}
	
	//RequestServlet
	@RequestMapping("/request")
	public ModelAndView registRequestHandler(@ModelAttribute("user")User user,String flag)
	{
		ModelAndView mv = new ModelAndView("redirect:audit");
		
		//判断前端给出的数据，执行相应的方法
		if("yes".equals(flag))
			userService.isTempUser(user.getUsername());
		else if("no".equals(flag))
			userService.refuse(user.getUsername());
		return mv;
	}
	
	@RequestMapping("/userlist")
	public ModelAndView userListHandler()
	{
		ModelAndView mv = new ModelAndView("manager/userlist");
		List<User> list = userService.showUsers();

		for (User user : list)
			System.out.println(user);

		mv.addObject("userlist", list);
		return mv;
	}
	
	@RequestMapping("/addbook")
	public ModelAndView addBook(MultipartHttpServletRequest request) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mv = new ModelAndView("manager/addbook");
		String bookid = request.getParameter("bookid");
		String bookName = request.getParameter("bookname");
		String author = request.getParameter("author");
		String amont = request.getParameter("amont");
		MultipartFile file = request.getFile("file");
		String error = "";
		if(bookid==null||bookName==null||author==null||amont==null)
			error="输入正确的值";
		if("".equals(bookid)||!Judge.isInteger(bookid))
			error="请输入正确id";
		if("".equals(bookName)||!Judge.isName(bookName))
			error="请输入书名";
		if("".equals(author)||!Judge.isName(author))
			error="请输入作者";
		if("".equals(amont)||!Judge.isInteger(amont))
			error="请输入库存信息";
		if("".equals(file))
			error="请上传文件";
		if(!"".equals(error))
		{
			mv.addObject("error", error);
			return mv;
		}
		
		Book book = new Book(Integer.parseInt(bookid), bookName, author, Integer.parseInt(amont));
		
		if(!bookService.addBookHandler(book))
			error="图书信息重复";
		
		if("".equals(error))
		{
			String fileName = file.getOriginalFilename();
			String prefix=fileName.substring(fileName.lastIndexOf("."));
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/assets/images"),bookid+prefix));
			error="添加成功！";
		}
		mv.addObject(error);
		return mv;
	}
	
	@RequestMapping("/updatebook")
	public ModelAndView updateBook(MultipartHttpServletRequest request) throws IOException
	{
		request.setCharacterEncoding("UTF-8");
		ModelAndView mv = new ModelAndView("manager/updatebook");
		String bookid = request.getParameter("bookid");
		String bookName = request.getParameter("bookname");
		String author = request.getParameter("author");
		String amont = request.getParameter("amont");
		MultipartFile file = request.getFile("file");
		String error = "";
		if(bookid==null||bookName==null||author==null||amont==null)
			error="输入正确的值";
		if("".equals(bookid)||!Judge.isInteger(bookid))
			error="请输入正确id";
		if("".equals(bookName)||!Judge.isName(bookName))
			error="请输入书名";
		if("".equals(author)||!Judge.isName(author))
			error="请输入作者";
		if("".equals(amont)||!Judge.isInteger(amont))
			error="请输入库存信息";
		if(!"".equals(error))
		{
			mv.addObject("error", error);
			return mv;
		}
		
		Book book = new Book(Integer.parseInt(bookid), bookName, author, Integer.parseInt(amont));
		if(!bookService.updateBookHandler(book))
			error="图书信息重复";
		
		if("".equals(error))
		{
			if(!file.getName().equals("file"))
				if(!file.getContentType().contains("image"))
					error="请上传正确的图片";
				else
				{
					String fileName = file.getOriginalFilename();
					String prefix=fileName.substring(fileName.lastIndexOf("."));
					FileUtils.copyInputStreamToFile(file.getInputStream(), new File(request.getSession().getServletContext().getRealPath("/assets/images"),bookid+prefix));
					error="信息修改成功";
				}
			else
				error="信息修改成功";
		}
		
		mv.addObject("error",error);
		return mv;
	}
	
	@RequestMapping("/delete")
	public ModelAndView deleteBookHandler(HttpSession session,String bookid)
	{
		ModelAndView mv = new ModelAndView("redirect:/books");
		if(bookid!=null)
		{
			File file = new File(session.getServletContext().getRealPath("/assets/images") + "/" + bookid + ".jpg");
			file.delete();
			bookService.deleteBookHandler(bookid);
		}
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView searchLogHandler(String username,String bookid,String bookname,String other)
	{
		ModelAndView mv = new ModelAndView("manager/log");
		if(bookid==null || bookname==null || username == null || other==null)
			return mv;
		
		List<Log> list = logService.showSearchLogs(username, bookid,bookname,other);
		mv.addObject("logs",list);
		return mv;
	}
}
