package com.lauguobin.www.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.UserDao;
import com.lauguobin.www.po.*;
import com.lauguobin.www.util.Judge;

@Service
public class UserService
{
	@Autowired
	private UserDao us;
	
	/**
	 * 判断用户名是否重复，并判断身份
	 * @param username
	 * @param password
	 * @param identify
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String handleUser(String username,String password,String repassword,String identify,String code,String randomString)
	{
		if(username == null || password == null || repassword == null)
			return "请输入信息";
		
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
		if(!randomString.equalsIgnoreCase(code))
			error = "验证码不正确！";
		
		if("".equals(error))
		{
			User user = new User(username,password,identify,false,new Date().toLocaleString());
			List<User> list = us.getExistUser();
			//判断是不是重复
			for(User s : list)
				if(s.getUsername().equals(user.getUsername()))
						return "用户信息已存在";
			if("manager".equals(identify))
			{
				user.setIsReal(true);
				error="注册成功";
			}
			us.addUser(user);
			if("student".equals(identify))
				error = "注册成功，管理员验证后即可登录。";
		}
		return error;
	}
	
	/**
	 * 判断是否可以登录
	 * @param user
	 * @return
	 */
	public boolean handleLogin(User user)
	{
		List<User> list = us.getExistUser();
		
		if(!us.isFormalUser(user))
			return false;
		for(User s : list)
			if(s.equals(user))
					return true;
		return false;
	}

	/**
	 * 判断搜索条件，并显示搜索用户列表
	 * @param search
	 * @return
	 */
	public List<User> ShowSearchUsers(String[] search)
	{
		String sql = "select * from user where username like '%"+search[0]+"%' or identify like '%"+search[0]+"%'";
		if(search.length == 2)
			sql = "select * from user where username like '%"+search[0]+"%' and identify like '%"+search[1]+"%'";
		return us.getSearchUser(sql);
	}
	
	/**
	 * 显示所有的用户
	 * @param isAll
	 * @return
	 */
	public List<User> showUsers()
	{
		return us.getExistUser();
	}
	/**
	 * 临时用户转正
	 * @param username
	 */
	public void isTempUser(String username)
	{
		User user = new User(username,true);
		us.updateRoot(user);
	}
	
	/**
	 * 拒绝注册临时用户
	 * @param username
	 */
	public void refuse(String username)
	{
		us.deleteUser(username);
	}
	
	/**
	 * 显示注册请求
	 * @return
	 */
	public List<User> showUsersRequests()
	{
		return us.getTempUsers();
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public int getUserId(String username)
	{
		return us.getUserId(username);
	}
}
