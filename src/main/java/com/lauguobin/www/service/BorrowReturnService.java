package com.lauguobin.www.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.BorrowReturnDao;
import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;
import com.lauguobin.www.util.Judge;

@Service
public class BorrowReturnService
{
	@Autowired
	private BorrowReturnDao brd;
	
	@Autowired
	private LogService logService;
	/**
	 * 判断发出借书请求
	 * @param username
	 * @param id
	 * @param bookname
	 * @param author
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public boolean toBorrow(BorrowInfo info)
	{
		info.setDate(new Date().toLocaleString());
		List<Book> list = brd.getUserBooks(info.getUserId());
		
		//防止刷新重复提交数据判断，不能重复借书（重要）
		for(Book b : list)
			if(b.getBookid() == info.getBookid())
				return false ; 
		
		//判断是否超过借书总数(因为不确定管理员什么时候会管理请求所以不用session)
		if(brd.getUserBooksCount(info.getUserId())>3)
			return false;
		
		brd.insertRequest(info);
		return true;
	}

	public List<Book> showAllBorrow(int userId)
	{
		 return brd.getUserBooks(userId);
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
			String date1 = logService.getBookBorrowDate(userId, bookid);
			String date2 = new Date().toLocaleString();
			//判断借还日期差
			if(Judge.isOverdue(date1, date2))
				other = "逾期";
			brd.returnBook(userId, bookid, date2,other);
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
		brd.acceptBorrow(userId, bookid , new Date().toLocaleString());
	}
	
	/**
	 * 判断拒绝借阅请求
	 * @param username
	 * @param bookid
	 */
	public void toRefuse(int userId,int bookid)
	{
		BorrowInfo info = new BorrowInfo(userId, bookid);
		brd.deleteRequest(info);
	}
	
	/**
	 * 显示用户借书列表
	 * @param username
	 * @return
	 * @throws IOException 
	 */
	public List<Book> showUsersBorrow(int userId)
	{
		return brd.getUserHave(userId);
	}
	
	/**
	 * 显示所有用户的借书请求
	 * @return
	 * @throws IOException 
	 */
	public List<BorrowInfo> showAllTempBorrow()
	{
		return brd.getAllTempBorrow();
	}
}
