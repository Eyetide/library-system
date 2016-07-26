package com.lauguobin.www.util;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DbUtil
{
/*	private static String jdbcName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://localhost:3306/library?useUnicode=true&useSSL=false";
	private static String dbName = "root";
	private static String dbPassword = "mysql";*/

	/**
	 * 连接数据库
	 * @author GB菌
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws Exception 
	 *
	 */
/*	public static Connection getCon() throws SQLException, ClassNotFoundException 
	{
		Class.forName(jdbcName); //查找并加载Driver类
		Connection connection = DriverManager.getConnection(dbUrl, dbName, dbPassword);
		return connection;
	}*/

	/**关闭数据库连接
	 * 
	 * @param stm 
	 * @param con 
	 * @throws SQLException
	 */
/*	public static void close(PreparedStatement stm,Connection con) throws SQLException
	{
		if(stm != null)
		{
			stm.close();
			if(con != null)
				con.close();
		}
	}*/

	public static SqlSession getSqlSession() throws IOException
	{
		String resource = "conf.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		return new SqlSessionFactoryBuilder().build(reader).openSession(true);
	}
}
