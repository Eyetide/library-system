package com.lauguobin.www.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.BorrowReturnDao;
import com.lauguobin.www.dao.LogDao;
import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;
import com.lauguobin.www.util.Judge;

@Service
public class BorrowReturnService
{
	/**
	 * 判断发出借书请求
	 * @param username
	 * @param id
	 * @param bookname
	 * @param author
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("deprecation")
	public boolean toBorrow(int userId,int id,String bookname,String author) throws IOException 
	{
		BorrowReturnDao brd = new BorrowReturnDao();
		BorrowInfo info = new BorrowInfo(userId,id,bookname,author,new Date().toLocaleString());
		List<Book> list = brd.getBorrowBooks(userId,true);
		
		//防止刷新重复提交数据判断，不能重复借书（重要）
		for(Book b : list)
			if(b.getBookid() == id)
				return false ; 
		//判断库存
		if(brd.isNoBook(id))
			return false;
		
		//判断是否超过借书总数(因为不确定管理员什么时候会管理请求所以不用session)
		if(brd.getBorrowCount(userId)>3)
			return false;
		
		brd.sendToBorrow(info);
		return true;
	}

	public List<Book> showAllBorrow(int userId) throws IOException 
	{
		 return new BorrowReturnDao().getBorrowBooks(userId,true);
	}
	
	/**
	 * 处理还书请求
	 * @param username
	 * @param id
	 */
	@SuppressWarnings("deprecation")
	public void toReturn(int userId , String id)
	{
		int bookid = Integer.parseInt(id);
		try
		{
			String other = "已还";
			String date1 = new LogDao().getBookBorrowDate(userId, bookid);
			String date2 = new Date().toLocaleString();
			//判断借还日期差
			if(Judge.isOverdue(date1, date2))
				other = "逾期";
			new BorrowReturnDao().returnBook(userId, bookid, date2,other);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 处理接受借阅请求
	 * @param username
	 * @param bookid
	 */
	@SuppressWarnings("deprecation")
	public void toAccept(int userId,int bookid)
	{
		try
		{
			new BorrowReturnDao().acceptBorrow(userId, bookid , new Date().toLocaleString());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断拒绝借阅请求
	 * @param username
	 * @param bookid
	 */
	public void toRefuse(int userId,int bookid)
	{
		try
		{
			BorrowInfo info = new BorrowInfo(userId, bookid);
			new BorrowReturnDao().refuseBorrow(info);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 显示用户借书列表
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Book> showUsersBorrow(int userId) throws IOException 
	{
		return new BorrowReturnDao().getBorrowBooks(userId,false);
	}
	
	/**
	 * 显示所有用户的借书请求
	 * @return
	 * @throws IOException 
	 */
	public List<BorrowInfo> showAllTempBorrow() throws IOException
	{
		return new BorrowReturnDao().getAllTempBorrow();
	}
}
