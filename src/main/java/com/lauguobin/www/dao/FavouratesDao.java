package com.lauguobin.www.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Favourate;
import com.lauguobin.www.util.DbUtil;


public class FavouratesDao
{
	/**
	 *  添加收藏夹
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	public int addFavourate(Favourate favo) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "favouratesMapper.insertFavo";
		int result = session.insert(statement, favo);
		session.close();
		return result;
	}
	
	/**
	 * 获取一个用户所有收藏内容
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Book> getFavourates(int userId) throws IOException
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "favouratesMapper.userFavo";
		
		List<Book> list = session.selectList(statement, userId);
		session.close();
		return list;
	}
	
	/**
	 * 获取用户收藏数目
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public int getFavouratesCount(int userId) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "favouratesMapper.favoCount";
		int count = session.selectOne(statement, userId);
		session.close();
		return count;
	}
	
	/**
	 *  删除一个用户的某个收藏记录
	 * @param username
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public int deleteFavourate(Favourate favo) throws IOException 
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "favouratesMapper.removeFavo";
		int result = session.delete(statement, favo);
		return result;
	}
}
