package com.lauguobin.www.view;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.lauguobin.www.po.User;
import com.lauguobin.www.service.BorrowReturnService;
import com.lauguobin.www.service.FavouratesService;
import com.lauguobin.www.service.UserService;
import com.lauguobin.www.util.Judge;

@RestController
public class LoginController
{
	@Autowired
	private UserService userService;
	@Autowired
	private BorrowReturnService borrowReturnService;
	@Autowired
	private FavouratesService favouratesService;
	
	
	@RequestMapping("/login")
	public ModelAndView loginHandler(@ModelAttribute("user") User user,String page,HttpSession session) throws IOException
	{
		ModelAndView mv = new ModelAndView("home");
		if(user.getUsername() == null || user.getPassword() == null || user.getIdentify() == null)
		{
			String error = "请输入完整的信息";
			
			mv.addObject("error", error);
			
			if("1".equals(page))
			{
				mv.setViewName("index");
				return mv;
			}
			else if("2".equals(page))
			{
				mv.setViewName("signin");
				return mv;
			}
		}
		
		if(userService.handleLogin(user))
		{
			int userId = userService.getUserId(user.getUsername());
			user.setUserId(userId);
			session.setAttribute("username",user.getUsername());
			session.setAttribute("password",user.getPassword());
			session.setAttribute("identify",user.getIdentify());
			if("student".equals(user.getIdentify()))
			{
				session.setAttribute("borrowlist", borrowReturnService.showAllBorrow(userId));
				session.setAttribute("favourates", favouratesService.showUserFavourates(userId));
			}
			return mv;
		}
		else
		{
			String error = "登陆错误，请检查用户名、密码和身份";
			mv.addObject("error", error);
			
			if("1".equals(page))
			{
				mv.setViewName("index");
				return mv;
			}
			else if("2".equals(page))
			{
				mv.setViewName("signin");
				return mv;
			}
		}
		return mv;
	}
	
	@RequestMapping("/regist")
	public ModelAndView registHandler(String username,String password,String repassword,String identify)
	{
		ModelAndView mv = new ModelAndView("regist");
		if(username==null && password==null && repassword==null)
			return mv;
		String error="";
		
		if(!Judge.isUserName(username))
			error = "用户名称错误";
		if(password.length()<6||password.length()>18||repassword.length()<6||repassword.length()>18)
			error = "密码长度必须位于6 - 18 位！";
		if(Judge.isChinese(password))
			error = "密码不能输入中文！";
		if(!password.equals(repassword))
			error = "两次密码不一致！";
		if(identify==null)
			error = "选择身份！";
		
		if("".equals(error))
		{	
			try
			{
				if(!new UserService().handleUser(username, repassword, identify))
					error = "用户名已存在";
				else
				{
					if("student".equals(identify))
					{
						error="注册成功，管理员验证后即可登录。";
						return mv;
					}
					else
						return mv;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return mv;
	}
	
	
}
