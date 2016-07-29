package com.lauguobin.www.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.FavouratesDao;
import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Favourate;

@Service
public class FavouratesService
{
	@Autowired
	private FavouratesDao fd;
	/**
	 * 判断是否可以收藏
	 * @param username
	 * @param bookid
	 * @param bookName
	 * @param author
	 * @throws IOException 
	 */
	public void collectBook(int userId,int bookid)
	{
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
			Favourate favo = new Favourate(userId,Integer.parseInt(id));
			fd.removeFavourate(favo);
	}

	/**
	 * 显示出一个用户的收藏列表
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Book> showUserFavourates(int userId)
	{
		return fd.getFavourates(userId);
	}
}
