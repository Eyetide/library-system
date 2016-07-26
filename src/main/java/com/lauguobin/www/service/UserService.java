package com.lauguobin.www.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.UserDao;
import com.lauguobin.www.po.*;

@Service
public class UserService
{
	UserDao us = new UserDao();
	
	/**
	 * 判断用户名是否重复，并判断身份
	 * @param username
	 * @param password
	 * @param identify
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public boolean handleUser(String username,String password,String identify) throws Exception
	{
			User user = new User(username,password,identify,false,new Date().toLocaleString());
			List<User> list = us.getExistUser(true);
			//判断是不是重复
			for(User s : list)
				if(s.getUsername().equals(user.getUsername()))
						return false;
			if(identify.equals("manager"))
				user.setIsReal(true);
			us.addUser(user);
			return true;
	}
	
	/**
	 * 判断是否可以登录
	 * @param user
	 * @return
	 */
	public boolean handleLogin(User user)
	{
		try
		{
			List<User> list = us.getExistUser(false);
			for(User s : list)
				if(s.equals(user))
						return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断搜索条件，并显示搜索用户列表
	 * @param search
	 * @return
	 * @throws IOException 
	 */
	public List<User> ShowSearchUsers(String[] search) throws  IOException
	{
		String sql = "select * from user where username like '%"+search[0]+"%' or identify like '%"+search[0]+"%'";
		if(search.length == 2)
			sql = "select * from user where username like '%"+search[0]+"%' and identify like '%"+search[1]+"%'";
		return new UserDao().getSearchUser(sql);
	}
	
	/**
	 * 显示所有的用户
	 * @param isAll
	 * @return
	 * @throws Exception
	 */
	public List<User> showUsers(boolean isAll) throws Exception
	{
		return new UserDao().getExistUser(isAll);
	}
	/**
	 * 临时用户转正
	 * @param username
	 */
	public void isTempUser(String username)
	{
		try
		{
			User user = new User(username,true);
			new UserDao().tempuserToReal(user);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 拒绝注册临时用户
	 * @param username
	 */
	public void refuse(String username)
	{
		try
		{
			new UserDao().deleteUser(username);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示注册请求
	 * @return
	 * @throws Exception
	 */
	public List<User> showUsersRequests() throws Exception
	{
		return new UserDao().getTempUsers();
	}
	
	/**
	 * 
	 * @param username
	 * @return
	 * @throws IOException
	 */
	public int getUserId(String username) throws IOException
	{
		return new UserDao().getUsersId(username);
	}
}
