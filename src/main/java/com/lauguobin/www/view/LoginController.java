package com.lauguobin.www.view;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Log;
import com.lauguobin.www.po.User;
import com.lauguobin.www.service.BookService;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;
import com.lauguobin.www.service.LogService;
import com.lauguobin.www.service.UserService;
import com.lauguobin.www.util.CaptchaUtil;

@RestController
public class LoginController
{
	@Autowired
	private UserService userService;
	@Autowired
	private BorrowReturnService borrowReturnService;
	@Autowired
	private FavouratesService favouratesService;
	@Autowired
	private BookService bookService;
	@Autowired
	private LogService logService;
	
	@RequestMapping("/login")
	public ModelAndView loginHandler(@ModelAttribute("user") User user,String page,HttpSession session)
	{
		ModelAndView mv = new ModelAndView("home");
		String error = "";
		if(user.getUsername() == null || user.getPassword() == null || user.getIdentify() == null)
		{
			error = "请输入完整的信息";
			if("1".equals(page))
				mv.setViewName("index");
			else
				mv.setViewName("signin");
			mv.addObject("error", error);
			return mv;
		}
		
		if(userService.handleLogin(user))
		{
			int userId = userService.getUserId(user.getUsername());
			session.setAttribute("username",user.getUsername());
			session.setAttribute("password",user.getPassword());
			session.setAttribute("identify",user.getIdentify());
			session.setAttribute("userId",userId);
			if("student".equals(user.getIdentify()))
			{
				session.setAttribute("borrowlist", borrowReturnService.showAllBorrow(userId));
				session.setAttribute("favourates", favouratesService.showUserFavourates(userId));
			}
			return mv;
		}
		else
		{
			error = "请检查用户名、密码和身份";
			mv.addObject("error", error);
			
			if("1".equals(page))
				mv.setViewName("index");
			else if("2".equals(page))
				mv.setViewName("signin");
		}
		return mv;
	}
	
	@RequestMapping("/regist")
	public ModelAndView registHandler(String username,String password,String repassword,String identify,String code,HttpSession session)
	{
		ModelAndView mv = new ModelAndView("regist");
		String randomString = (String) session.getAttribute("randomString");
		
		String error= userService.handleUser(username, password, repassword, identify, code, randomString);
		
		mv.addObject("error", error);
		return mv;
	}
	
	
	@RequestMapping("/books")
	public ModelAndView bookListHandler(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		String identify = (String) session.getAttribute("identify");
		List<Book> list =  bookService.showBooks();
		mv.addObject("bookList", list);
		if("manager".equals(identify))
			mv.setViewName("manager/librarymanage");
		else if("student".equals(identify))
			mv.setViewName("student/librarypage");
		return mv;
	}
	
	@RequestMapping("/logs")
	public ModelAndView logsHandler(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		String identify = (String) session.getAttribute("identify");
		int userId = (int) session.getAttribute("userId");
		if("student".equals(identify))
		{
			mv.setViewName("student/loginfo");
			List<Log> list = logService.showUserLogs(userId);
			mv.addObject("logs", list);
		}
		else if("manager".equals(identify))
		{
			mv.setViewName("manager/log");
			List<Log> list = logService.showLogs();
			mv.addObject("logs", list);
		}
		
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(String bookid,String bookName,String author,String amont,HttpSession session)
	{
		ModelAndView mv = new ModelAndView("/books");
		String identify = (String) session.getAttribute("identify");
		if("manager".equals(identify))
			mv.setViewName("manager/librarymanage");
		else if("student".equals(identify))
			mv.setViewName("student/librarypage");
		if(bookid==null || bookName==null || author == null || amont==null)
			return mv;
		List<Book> list = bookService.showSearchBooks(bookid,bookName, author, amont);
		
		mv.addObject("bookList",list);
		return mv;
	}
	
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        CaptchaUtil.outputCaptcha(request, response);
    }
	
}
