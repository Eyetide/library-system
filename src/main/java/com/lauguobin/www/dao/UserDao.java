package com.lauguobin.www.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.lauguobin.www.po.User;

@Repository
public interface UserDao
{
	/**
	 * 获取所有用户
	 * @param isAll
	 * @return
	 */
	public List<User> getExistUser() ;
	
	/**
	 * 获取搜索到的用户
	 * @param sql
	 * @return
	 */
	public List<User> getSearchUser(String sql);

	/**
	 *  添加一个用户
	 * @param user
	 * @param isManager
	 * @return
	 */
	public int addUser(User user);
	
	/**
	 * 拒绝注册，删除临时用户
	 * @param username
	 * @param isTempUser
	 */
	public int deleteUser(String username);

	/**
	 * 通过审核，升级成正式用户。
	 * @param username
	 * @return
	 */
	public int updateRoot(User user);
	
	/**
	 * 获取所有请求注册的用户
	 * @return
	 */
	public List<User> getTempUsers();

	/**
	 *获取一个用户的唯一标识
	 * @param username
	 * @return
	 */
	public int getUserId(String username);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public User isFormalUser(User user);
}
