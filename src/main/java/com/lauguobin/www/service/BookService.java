package com.lauguobin.www.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Service;

import com.lauguobin.www.dao.BookDao;
import com.lauguobin.www.po.Book;

@Service
public class BookService
{
	public boolean addBookHandler(Book book,String fileName,FileItem item,String path) throws Exception
	{
		BookDao bd = new BookDao();
		List<Book> list = bd.getExistBooks();
		for(Book b : list)
			if((b.getBookid() == book.getBookid()) || book.equals(b))
					return false;
		int i = fileName.indexOf(".");
		fileName = fileName.substring(i);
		bd.addBook(book, fileName, item, path);

		return true;
	}
	
	/**
	 * 更新图书信息并更新图片
	 * @param book
	 * @param fileName
	 * @param item
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean updateBookHandler(Book book,String fileName,FileItem item,String path) throws Exception
	{
		BookDao bd = new BookDao();
		List<Book> list = bd.getExistBooks();
		for(Book b : list)
			if((b.getBookid() == book.getBookid()))
			{
				int i = fileName.indexOf(".");
				fileName = fileName.substring(i);
				bd.updateBook(book, fileName, item, path);
				return true;
			}
		return false;
	}
	
	/**
	 * 仅仅更新图书信息
	 * @param book
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public boolean updateBookHandler(Book book) throws Exception
	{
		BookDao bd = new BookDao();
		List<Book> list = bd.getExistBooks();
		for(Book b : list)
			if((b.getBookid() == book.getBookid()))
			{
				bd.updateBook(book);
				return true;
			}
		return false;
	}
	
	/**
	 * 删除图书
	 * @param bookid
	 * @param filePath
	 */
	public void deleteBookHandler(String bookid,String filePath)
	{
		try
		{
			int id = Integer.parseInt(bookid);
			new BookDao().deleteBook(id, filePath);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 显示图书
	 * @return
	 * @throws Exception
	 */
	public List<Book> showBooks() throws Exception
	{
		return new BookDao().getExistBooks();
	}
	
	/**
	 * 显示搜索出的图书，service用于判断搜索条件的多少
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public List<Book> showSearchBooks(String[] str) throws Exception
	{
		String sql = "SELECT * FROM book WHERE bookName LIKE '%"+ str[0] +"%' OR author LIKE '%"+str[0] +"%'  OR id = '"  + str[0] +"'";
		if(str.length == 2)
		{
			sql = "select * from book where bookName like '%"+str[0]+"%' and author like '%"+str[1]+"%' "
					+ "or id = '"+str[0]+"' and bookName like '%"+str[1]+"%' "
					+ "or id = '"+str[0]+"' and author like '%"+str[1]+"%'";
		}
		return new BookDao().getSearchBooks(sql);
	}
}
