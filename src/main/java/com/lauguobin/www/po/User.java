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
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identify == null) ? 0 : identify.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (identify == null)
		{
			if (other.identify != null)
				return false;
		}
		else if (!identify.equals(other.identify))
			return false;
		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals(other.password))
			return false;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals(other.username))
			return false;
		return true;
	}
}
