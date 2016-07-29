package com.lauguobin.www.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.LogDao;
import com.lauguobin.www.po.Log;
import com.lauguobin.www.util.Judge;

@Service
public class LogService
{
	@Autowired
	private LogDao logDao;
	/**
	 * 显示所有用户信息
	 * @return
	 * @throws IOException 
	 */
	public List<Log> showLogs()
	{
		return logDao.getAllLogs();
	}
	
	/**
	 * 显示某个用户的日志
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Log> showUserLogs(int userId)
	{
		return logDao.getOneUserLogs(userId);
	}

	/**
	 * 某个用户的搜索条件
	 * @param search
	 * @param username
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> showStuSearchLogs(int userId,String bookid,String bookName,String other)
	{
		if("".equals(bookid) || !Judge.isInteger(bookid))
			bookid="-1";
		bookName = "%" + bookName + "%";
		other = "%" + other + "%";
		Log log = new Log(userId,Integer.parseInt(bookid), bookName, other);
		System.out.println(log);
		return logDao.getSearchByOne(log);
	}
	
	/**
	 * 搜索条件
	 * @param search
	 * @param username
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> showSearchLogs(String username,String bookid,String bookName,String other)
	{
		if("".equals(bookid) || !Judge.isInteger(bookid))
			bookid="-1";
		username = "%" + username + "%";
		bookName = "%" + bookName + "%";
		other = "%" + other + "%";
		Log log = new Log(username,Integer.parseInt(bookid), bookName, other);
		return logDao.getSearch(log);
	}
	
	/**
	 * 获取借书日期
	 * @param userId
	 * @param bookid
	 * @return
	 */
	public String getBookBorrowDate(int userId,int bookid)
	{
		String date = logDao.getBookBorrowDate(userId, bookid);
		if(date!=null)
			return date;
		else
			return "";
	}
}
