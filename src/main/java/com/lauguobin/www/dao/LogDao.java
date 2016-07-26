package com.lauguobin.www.dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lauguobin.www.po.Log;
import com.lauguobin.www.util.DbUtil;

public class LogDao
{
	/**
	 * 获取所有的用户日志信息
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> getLogs() throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "logMapper.allLogs";
		List<Log> list = session.selectList(statement);
		session.close();
		return list;
	}
	
	/**
	 * 获取单个用户的日志信息，重载方法
	 * @param username
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> getLogs(int userId) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "logMapper.oneUserLogs";
		List<Log> list = session.selectList(statement, userId);
		session.close();
		return list;
	}
	
	/**
	 * 
	 * @param username
	 * @param bookid
	 * @return
	 * @throws IOException 
	 */
	public String getBookBorrowDate(int userId,int bookid) throws IOException 
	{
		Log log = new Log(userId,bookid);
		SqlSession session = DbUtil.getSqlSession();
		String statement = "logMapper.getBookBorrowDay";
		String borrowDay = session.selectOne(statement, log);
		session.close();
		return borrowDay;
	}
	
	/**
	 * 搜索日志信息
	 * @param sql
	 * @return
	 */
	public List<Log> getSearchLogs(String sql) 
	{
		//TODO
		
		
		
		
		
		
		
		
		return new ArrayList<Log>();
	}
}
