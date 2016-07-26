package com.lauguobin.www.po;

public class Favourate
{
	private int userId;
	private int id;
	
	public Favourate(int userId, int id)
	{
		super();
		this.userId = userId;
		this.id = id;
	}

	public Favourate()
	{
		super();
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

}
