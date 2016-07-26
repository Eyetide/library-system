package com.lauguobin.www.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.FavouratesDao;
import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Favourate;

@Service
public class FavouratesService
{
	/**
	 * 判断是否可以收藏
	 * @param username
	 * @param bookid
	 * @param bookName
	 * @param author
	 * @throws IOException 
	 */
	public void collectBook(int userId,int bookid) throws IOException
	{
		FavouratesDao fd = new FavouratesDao();
		List<Book> list = fd.getFavourates(userId);
		
		//防止刷新导致的数据提交重复
		for(Book b : list)
			if(b.getBookid() == bookid)
				return ;
		
		//判断是否超过收藏上限
		if(fd.getFavouratesCount(userId)>10)
			return;
		Favourate favo = new Favourate(userId ,bookid);
		fd.addFavourate(favo);
	}
	
	/**
	 * 判断删除是否异常
	 * @param username
	 * @param id
	 */
	public void deleteFavourte(int userId,String id)
	{
		try
		{
			Favourate favo = new Favourate(userId,Integer.parseInt(id));
			new FavouratesDao().deleteFavourate(favo);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 显示出一个用户的收藏列表
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Book> showUserFavourates(int userId) throws IOException 
	{
		return new FavouratesDao().getFavourates(userId);
	}
}
