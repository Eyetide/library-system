package com.lauguobin.www.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.LogDao;
import com.lauguobin.www.po.Log;

@Service
public class LogService
{
	/**
	 * 显示所有用户信息
	 * @return
	 * @throws IOException 
	 */
	public List<Log> showLogs() throws IOException
	{
		return new LogDao().getLogs();
	}
	
	/**
	 * 显示某个用户的日志
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Log> showUserLogs(int userId) throws IOException 
	{
		return new LogDao().getLogs(userId);
	}

	/**
	 * 根据搜索条件显示日志
	 * @param search
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> showSearchLogs(String[] search) throws ClassNotFoundException, SQLException
	{
		String sql = "select * from log where id = '" +search[0]+"' or username like '%"+search[0]+"%' or bookName like '%" +search[0]+ "%' or author like '%"+search[0]+"%'";
		if(search.length == 2)
			sql = "SELECT * FROM LOG WHERE  username LIKE '%"+search[0]+"%' AND id = '"+search[1]+"' UNION ALL SELECT * FROM LOG WHERE username LIKE '%"+search[0]+"%' AND bookName LIKE '%"+search[1]+"%' UNION ALL SELECT * FROM LOG WHERE username LIKE '%"+search[0]+"%' AND author LIKE '%"+search[1]+"%'";
		if(search.length == 3)
			sql = "select * from log where username like '%"+search[0]+"%' and bookName like '%"+search[1]+"%' or author like '%"+search[2]+"%'";
		return new LogDao().getSearchLogs(sql);
	}
	
	/**
	 * 某个用户的搜索条件
	 * @param search
	 * @param username
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public List<Log> showStuSearchLogs(String[] search,String username ) throws ClassNotFoundException, SQLException
	{
		String sql = "SELECT * FROM LOG WHERE username = '"+username+"' AND id = '"+search[0]+"' "
				+ "OR username = '"+username+"' AND bookName LIKE '%"+search[0]+"%' "
				+ "OR username = '"+username+"' AND author LIKE '%"+search[0]+"%'"
				+ "OR username = '"+username+"' AND other LIKE '%"+search[0]+"%'";
		if(search.length == 2)
			sql = "select * from log where username = '"+username+"' and bookName like '%"+search[0]+"%'  and author like '%"+search[1]+"%'"
				+ " or username = '"+username+"' and bookName like '%"+search[0]+"%'  and other like '%"+search[1]+"%'"
				+ " or username = '"+username+"' and author like '%"+search[0]+"%'  and other like '%"+search[1]+"%'";
		if(search.length == 3)
			sql = "select * from log where username = '"+username+"' and bookName like '%"+search[0]+"%'  and author like '%"+search[1]+"%' and other like '%"+search[2]+"%'";
		return new LogDao().getSearchLogs(sql);
	}
}
