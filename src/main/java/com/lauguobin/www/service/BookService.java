package com.lauguobin.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.BookDao;
import com.lauguobin.www.po.Book;
import com.lauguobin.www.util.Judge;

@Service
public class BookService
{
	@Autowired
	private BookDao bookDao;
	
	public boolean addBookHandler(Book book)
	{
		List<Book> list = bookDao.getExistBooks();
		for(Book b : list)
			if((b.getBookid() == book.getBookid()) || book.equals(b))
				return false;
		bookDao.addBook(book);

		return true;
	}
	
	/**
	 * 仅仅更新图书信息
	 * @param book
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean updateBookHandler(Book book)
	{
		List<Book> list = bookDao.getExistBooks();
		for(Book b : list)
			if((b.getBookid() == book.getBookid()))
			{
				bookDao.updateBook(book);
				return true;
			}
		return false;
	}
	
	/**
	 * 删除图书
	 * @param bookid
	 * @param filePath
	 */
	public void deleteBookHandler(String bookid)
	{
		int id = Integer.parseInt(bookid);
		bookDao.deleteBook(id);
	}

	/**
	 * 显示图书
	 * @return
	 * @throws Exception
	 */
	public List<Book> showBooks()
	{
		return bookDao.getExistBooks();
	}
	
	/**
	 * 显示搜索出的图书，service用于判断搜索条件的多少
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<Book> showSearchBooks(String bookid,String bookName,String author,String amont)
	{
		if(!Judge.isInteger(bookid)||"".equals(bookid))
			bookid="-1";
		if(!Judge.isInteger(amont)||"".equals(amont))
			amont="-1";
		
		bookName="%"+bookName+"%";
		author="%"+author+"%";
		
		Book book = new Book(Integer.parseInt(bookid), bookName, author, Integer.parseInt(amont));
		System.out.println(book);
		return bookDao.getSearchBooks(book);
	}
}
