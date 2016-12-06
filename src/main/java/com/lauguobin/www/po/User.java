package com.lauguobin.www.po;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int userId;
	private String username;
	private String password;
	private String identify;
	private boolean isReal;
	private String date;

	public User(String username, String password, String identify, boolean isReal, String date)
	{
		super();
		this.username = username;
		this.password = password;
		this.identify = identify;
		this.isReal = isReal;
		this.date = date;
	}

	public User(String username, boolean isReal)
	{
		this.username = username;
		this.isReal = isReal;
	}

	public User(String username, String password, String identify)
	{
		this.username = username;
		this.password = password;
		this.identify = identify;
	}

	public User()	{	}

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getIdentify()
	{
		return identify;
	}

	public void setIdentify(String identify)
	{
		this.identify = identify;
	}

	public boolean getIsReal()
	{
		return isReal;
	}

	public void setIsReal(boolean b)
	{
		this.isReal = b;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (userId != user.userId) return false;
		if (isReal != user.isReal) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		return identify != null ? identify.equals(user.identify) : user.identify == null;
	}

	@Override
	public int hashCode() {
		int result = userId;
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (identify != null ? identify.hashCode() : 0);
		result = 31 * result + (isReal ? 1 : 0);
		return result;
	}
}
