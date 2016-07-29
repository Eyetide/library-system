package com.lauguobin.www.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Favourate;

@Repository
public interface FavouratesDao
{
	/**
	 *  添加收藏夹
	 * @param user
	 * @return
	 */
	public int addFavourate(Favourate favo);
	
	/**
	 * 获取一个用户所有收藏内容
	 * @param username
	 * @return
	 */
	public List<Book> getFavourates(int userId);
	
	/**
	 * 获取用户收藏数目
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public int getFavouratesCount(int userId);
	
	/**
	 *  删除一个用户的某个收藏记录
	 * @param username
	 * @param id
	 * @return
	 */
	public int removeFavourate(Favourate favo);
}
