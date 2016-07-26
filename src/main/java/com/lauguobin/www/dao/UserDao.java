package com.lauguobin.www.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lauguobin.www.po.User;
import com.lauguobin.www.util.DbUtil;

public class UserDao
{
	/**
	 * 获取所有已经通过注册用户
	 * @param isAll
	 * @return
	 * @throws Exception
	 */
	public List<User> getExistUser(boolean isAll) throws Exception
	{
		SqlSession session = DbUtil.getSqlSession();
			String statement = "userMapper.allUsers";
		if(!isAll)
			statement = "userMapper.realUsers";
		List<User> list  = session.selectList(statement);
		session.close();
		return list;
	}
	
	/**
	 * 获取搜索到的用户
	 * @param sql
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<User> getSearchUser(String sql) throws IOException 
	{
		//TODO
		
		
		
		
		
		
		return new ArrayList<User>();
	}

	/**
	 *  添加一个用户
	 * @param user
	 * @param isManager
	 * @return
	 * @throws Exception
	 */
	public int addUser(User user) throws Exception
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.addUser";
		int result = session.insert(statement, user);
		session.close();
		return result;
	}
	
	/**
	 * 拒绝注册，删除临时用户
	 * @param username
	 * @param isTempUser
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int deleteUser(String username) throws IOException
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.deleteUser";
		int result = session.delete(statement, username);
		session.close();
		return result;
	}

	/**
	 * 通过审核，升级成正式用户。
	 * @param username
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int tempuserToReal(User user) throws IOException 
	{
		user.setIsReal(true);
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.updateUser";
		int result = session.update(statement, user);
		return result;
	}
	
	/**
	 * 获取所有请求注册的用户
	 * @return
	 * @throws IOException 
	 */
	public List<User> getTempUsers() throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.getTempUsers";
		
		List<User> list = session.selectList(statement);
		session.close();
		return list;
	}

	/**
	 * 获取所有请求
	 * @return
	 * @throws Exception
	 */
	public List<User> getTempUser() throws Exception
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.tempUsers";
		List<User> list = session.selectList(statement);
		session.close();
		return list;
	}
 
	/**
	 *获取一个用户的唯一标识
	 * @param username
	 * @return
	 * @throws IOException
	 */
	public int getUsersId(String username) throws IOException
	{
		User user = new User();
		user.setUsername(username);
		SqlSession session = DbUtil.getSqlSession();
		String statement = "userMapper.getUserId";
		int userId = session.selectOne(statement,user);
		session.close();
		return userId;
	}
}
