package com.lauguobin.www.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.BorrowInfo;

@Repository
public interface BorrowReturnDao
{
	/**
	 * 存储临时借阅信息
	 * @param info
	 */
	public int insertRequest(BorrowInfo info);

	/**
	 * 同意借书，临时信息转换为正式信息
	 * @param username
	 * @param bookid
	 * @param date
	 */
	public int acceptBorrow(int userId ,int bookid,String date);

	/**
	 * 拒绝借书，直接删除临时借阅信息数据
	 * @param username
	 * @param bookid
	 */
	public int deleteRequest(BorrowInfo info);

	/**
	 * 访问数据库，还书
	 * @param username
	 * @param bookid
	 * @param returnDay
	 * @param other
	 */
	public void returnBook(int userId , int bookid,String returnDay,String other);
	
	/**
	 * 判断库存是不是0
	 * @param id
	 * @return
	 * @throws IOException 
	 */
	public int booksAmont(int id);
	
	/**
	 * 用于打印出审核借阅列表
	 * @return
	 * @throws IOException 
	 */
	public List<BorrowInfo> getAllTempBorrow();
	
	/**
	 * 可以获取某个用户所有申请借阅的书籍和实际借到的书籍
	 * @param isAll true包含待借阅 false实际借阅
	 * @return
	 */
	public List<Book> getUserBooks(int userId);
	
	public List<Book> getUserHave(int userId);
	
	/**
	 * 获取用户的借书总数
	 * @param username
	 * @return
	 */
	public int getUserBooksCount(int userId);
}
