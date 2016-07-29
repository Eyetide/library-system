package com.lauguobin.www.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lauguobin.www.po.Book;

@Repository
public interface BookDao
{
	/**
	 * 添加一本书进数据库并且写入图片
	 * @param book
	 */
	public int addBook(Book book);
	
	/**
	 * 仅仅更新一本书的文字信息
	 * @param book
	 */
	public int updateBook(Book book);
	
	/**
	 * 删除一本书，并且删除图片
	 * @param bookid
	 */
	public int deleteBook(int bookid);
	
	/**
	 * 获取所有已经上架的书籍
	 * @return
	 */
	public List<Book> getExistBooks();
	
	/**
	 *  获取搜索到的书籍
	 * @param sql
	 * @return
	 */
	public List<Book> getSearchBooks(Book book);
}
