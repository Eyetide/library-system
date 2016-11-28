package com.lauguobin.www.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.lauguobin.www.po.Book;

public class BookDaoImpl implements BookDao
{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int addBook(Book book)
	{
		sqlSessionTemplate.insert("com.lauguobin.www.dao.BookDao.addBook", book);
		return 0;
	}

	@Override
	public int updateBook(Book book)
	{
		sqlSessionTemplate.update("updateBook", book);
		return 0;
	}

	@Override
	public int deleteBook(int bookid)
	{
		sqlSessionTemplate.delete("deleteBook", bookid);
		return 0;
	}

	@Override
	public List<Book> getExistBooks()
	{
		sqlSessionTemplate.selectList("com.lauguobin.www.dao.BookDao.getExistBook");
		return null;
	}

	@Override
	public List<Book> getSearchBooksByPage(Map<?, ?> map)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBookCount()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
