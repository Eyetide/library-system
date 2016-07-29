package com.lauguobin.www.view;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;
import com.lauguobin.www.po.Log;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;
import com.lauguobin.www.service.LogService;

@RestController
@RequestMapping("/student")
public class StudentViewController
{
	@Autowired
	private FavouratesService favouratesService;
	@Autowired
	private BorrowReturnService borrowReturnService;
	@Autowired
	private LogService logService;
	
	@RequestMapping("/borrowfavourate")
	public ModelAndView borrowFavourateHandler(@ModelAttribute("info")BorrowInfo info,String flag,HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		int userId = (int) session.getAttribute("userId");
		
		if("collect".equals(flag))
		{
			favouratesService.collectBook(userId, info.getBookid());
			//更新session里的收藏夹信息
			session.setAttribute("favourates",favouratesService.showUserFavourates(userId));
		}

		if("borrow".equals(flag))
		{
			info.setUserId(userId);
			borrowReturnService.toBorrow(info);
			session.setAttribute("borrowlist", borrowReturnService.showAllBorrow(userId));
		}
		mv.setViewName("redirect:/books");
		return mv;
	}
	
	//BorrowPageShow
	@RequestMapping("/yourbooks")
	public ModelAndView borrowlistHandler(HttpSession session)
	{
		ModelAndView mv = new ModelAndView("student/borrowpage");
		List<Book> list = borrowReturnService.showUsersBorrow((int)session.getAttribute("userId"));
		mv.addObject("myborrowbook", list);
		
		return mv;
	}
	
	//DeleteFavourateServlet
	@RequestMapping("/delete")
	public ModelAndView delFavourateHandler(HttpSession session,String id)
	{
		ModelAndView mv = new ModelAndView("student/favourates");
		int userId = (int)session.getAttribute("userId");
		if(id != null)
		{
			favouratesService.deleteFavourte(userId, id);
			session.setAttribute("favourates", favouratesService.showUserFavourates(userId));
		}
		return mv;
	}
	
	//ReturnServlet
	@RequestMapping("/return")
	public ModelAndView returnHandler(HttpSession session,String bookid)
	{
		ModelAndView mv = new ModelAndView("student/borrowpage");
		int userId = (int ) session.getAttribute("userId");
		if(bookid != null)
		{
			borrowReturnService.toReturn(userId, bookid);
			session.setAttribute("borrowlist", borrowReturnService.showAllBorrow(userId));
		}
		
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView searchLogHandler(String bookid,String bookname,String other,HttpSession session)
	{
		ModelAndView mv = new ModelAndView("student/loginfo");
		if(bookid==null || bookname==null || other==null)
			return mv;
		int userId = (int) session.getAttribute("userId");
		List<Log> list = logService.showStuSearchLogs(userId, bookid,bookname,other);
		mv.addObject("logs",list);
		return mv;
	}
}
