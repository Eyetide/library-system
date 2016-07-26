package com.lauguobin.www.po;

import java.io.Serializable;

public class Log implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String username;
	private int id;
	private String bookname;
	private String author;
	private String borrowDay;
	private String returnDay;
	private String other;

	public Log(){	}

	public Log(String username, int id, String bookname, String author, String borrowDay, String returnDay,
			String other)
	{
		this.username = username;
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.borrowDay = borrowDay;
		this.returnDay = returnDay;
		this.other = other;
	}

	public Log(int id, String bookname, String author, String borrowDay, String returnDay, String other)
	{
		this.id = id;
		this.bookname = bookname;
		this.author = author;
		this.borrowDay = borrowDay;
		this.returnDay = returnDay;
		this.other = other;
	}

	public Log(int userId, int id)
	{
		super();
		this.userId = userId;
		this.id = id;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getBookname()
	{
		return bookname;
	}
	public void setBookname(String bookname)
	{
		this.bookname = bookname;
	}
	public String getAuthor()
	{
		return author;
	}
	public void setAuthor(String author)
	{
		this.author = author;
	}
	public String getBorrowDay()
	{
		return borrowDay;
	}
	public void setBorrowDay(String borrowDay)
	{
		this.borrowDay = borrowDay;
	}
	public String getReturnDay()
	{
		return returnDay;
	}
	public void setReturnDay(String returnDay)
	{
		this.returnDay = returnDay;
	}
	public String getOther()
	{
		return other;
	}
	public void setOther(String other)
	{
		this.other = other;
	}
}
