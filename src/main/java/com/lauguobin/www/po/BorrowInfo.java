package com.lauguobin.www.po;

import java.io.Serializable;

public class BorrowInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String username;
	private int bookid;
	private String bookName;
	private String author;
	private String date;
	private boolean isAccepted = false;
	
	public BorrowInfo(int userId,  int bookid, String bookName, String author, String date)
	{
		super();
		this.userId = userId;
		this.bookid = bookid;
		this.bookName = bookName;
		this.author = author;
		this.date = date;
	}

	public BorrowInfo(int userId, int bookid, String date)
	{
		super();
		this.userId = userId;
		this.bookid = bookid;
		this.date = date;
	}

	public BorrowInfo(int userId, int bookid)
	{
		super();
		this.userId = userId;
		this.bookid = bookid;
	}

	public BorrowInfo()	{	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public boolean isAccepted()
	{
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted)
	{
		this.isAccepted = isAccepted;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public int getBookid()
	{
		return bookid;
	}

	public void setBookid(int bookid)
	{
		this.bookid = bookid;
	}

	public String getBookName()
	{
		return bookName;
	}

	public void setBookName(String bookName)
	{
		this.bookName = bookName;
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}
}
