package com.lauguobin.www.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;
import com.lauguobin.www.util.DbUtil;

public class BorrowReturnDao
{
	/**
	 * 存储临时借阅信息
	 * @param info
	 * @throws IOException 
	 */
	public int sendToBorrow(BorrowInfo info) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.insertNew";
		int result = session.insert(statement, info);
		session.close();
		return result;
	}

	/**
	 * 同意借书，临时信息转换为正式信息
	 * @param username
	 * @param bookid
	 * @param date
	 * @throws IOException 
	 */
	public int acceptBorrow(int userId ,int bookid,String date) throws IOException 
	{
		BorrowInfo info = new BorrowInfo(userId, bookid, date);
		SqlSession session = DbUtil.getSqlSession();
		
		System.out.println(userId+" "+bookid);
		String statement = "brMapper.acceptBorrow";
		int result = session.update(statement, info);
		session.close();
		return result;
	}

	/**
	 * 拒绝借书，直接删除临时借阅信息数据
	 * @param username
	 * @param bookid
	 * @throws IOException 
	 */
	public int refuseBorrow(BorrowInfo info) throws IOException
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.refuseBorrow";
		int result = session.delete(statement, info);
		session.close();
		return result;
	}

	/**
	 * 访问数据库，还书
	 * @param username
	 * @param bookid
	 * @param returnDay
	 * @param other
	 * @throws IOException 
	 */
	public void returnBook(int userId , int bookid,String returnDay,String other) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.returnBook";
		HashMap<String,Object> param = new HashMap<>();
		param.put("userId", userId);
		param.put("id", bookid);
		param.put("returnDay", returnDay);
		param.put("other", other);
		session.selectOne(statement, param);
		session.close();
	}
	
	/**
	 * 判断库存是不是0
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public boolean isNoBook(int id) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.amont";
		int amont = session.selectOne(statement, id);
		if(amont>0)
			return false;
		else
			return true;
	}
	
	/**
	 * 用于打印出审核借阅列表
	 * @return
	 * @throws IOException 
	 */
	public List<BorrowInfo> getAllTempBorrow() throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.getTempBorrow";
		List<BorrowInfo> list = session.selectList(statement);
		session.close();
		return list;
	}
	
	/**
	 * 可以获取某个用户所有申请借阅的书籍和实际借到的书籍
	 * @param isAll true包含待借阅 false实际借阅
	 * @return
	 * @throws IOException 
	 */
	public List<Book> getBorrowBooks(int userId,boolean isAll) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "brMapper.aUserBorrow";
		if(!isAll)
			statement = "brMapper.aUserHaveBorrow";
		List<Book> list = session.selectList(statement, userId);
		session.close();
		return list;
	}
	
	/**
	 * 获取用户的借书总数
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public int getBorrowCount(int userId) throws IOException
	{
		String statement = "aUserBookCount";
		SqlSession session = DbUtil.getSqlSession();
		int count = session.selectOne(statement, userId);
		session.close();
		return count;
	}
}
