package com.lauguobin.www.po;

import java.io.Serializable;

public class Book implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bookid;
	private String bookName;
	private String author;
	private int amont;
	private String borrowDate;
	private String returnDate;
	
	public Book(int bookid, String bookName, String author, int amont)
	{
		super();
		this.bookid = bookid;
		this.bookName = bookName;
		this.author = author;
		this.amont = amont;
	}
	
	public Book(int bookid, String bookName, String author)
	{
		super();
		this.bookid = bookid;
		this.bookName = bookName;
		this.author = author;
	}

	public Book(int bookid, String bookName, String author, String borrowDate, String returnDate)
	{
		super();
		this.bookid = bookid;
		this.bookName = bookName;
		this.author = author;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
	}

	public Book(){	}

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

	public int getAmont()
	{
		return amont;
	}

	public void setAmont(int amont)
	{
		this.amont = amont;
	}

	public String getBorrowDate()
	{
		return borrowDate;
	}

	public void setBorrowDate(String borrowDate)
	{
		this.borrowDate = borrowDate;
	}

	public String getReturnDate()
	{
		return returnDate;
	}

	public void setReturnDate(String returnDate)
	{
		this.returnDate = returnDate;
	}

	public int getBookid()
	{
		return bookid;
	}

	public void setBookid(int bookid)
	{
		this.bookid = bookid;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null)
		{
			if (other.author != null)
				return false;
		}
		else if (!author.equals(other.author))
			return false;
		if (bookName == null)
		{
			if (other.bookName != null)
				return false;
		}
		else if (!bookName.equals(other.bookName))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Book [bookid=" + bookid + ", bookName=" + bookName + ", author=" + author + ", amont=" + amont + "]";
	}
	
	
}
