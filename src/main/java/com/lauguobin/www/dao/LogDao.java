package com.lauguobin.www.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lauguobin.www.po.Log;

@Repository
public interface LogDao
{
	/**
	 * 获取所有的用户日志信息
	 * @return
	 */
	public List<Log> getAllLogs();
	
	/**
	 * 获取单个用户的日志信息
	 * @param username
	 * @return
	 */
	public List<Log> getOneUserLogs(int userId);
	
	/**
	 * @param username
	 * @param bookid
	 * @return
	 */
	public String getBookBorrowDate(int userId,int bookid);
	
	/**
	 * 搜索日志信息
	 * @param sql
	 * @return
	 */
	public List<Log> getSearch(Log log);
	
	/**
	 * 搜索日志信息
	 * @param sql
	 * @return
	 */
	public List<Log> getSearchByOne(Log log);
}
