package com.lauguobin.www.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.po.Page;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PageInterceptor implements Interceptor 
{
	/**
	 * @param invocation 被拦截下来的对象。
	 */
	@Override
	public Object intercept(Invocation invocation) throws Throwable
	{
		StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY);
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");
		// 配置文件中SQL语句的ID
		String id = mappedStatement.getId();
		if(id.matches(".+ByPage$")) 
		{
			BoundSql boundSql = statementHandler.getBoundSql();
			// 原始的SQL语句
			String sql = boundSql.getSql();
			// 查询总条数的SQL语句
			String countSql = "select count(*) from (" + sql + ")a";
			System.out.println(countSql);
			Connection connection = (Connection)invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			
			Map<?,?> parameter = (Map<?,?>)boundSql.getParameterObject();
			Page page = (Page)parameter.get("page");
			Book book = (Book)parameter.get("book");
			if(rs.next()) 
			{
				page.setTotalNumber(rs.getInt(1));
			}
			String pageSql = sql;
			if(book.getBookid()!=-1)
				pageSql = pageSql + " and id = " + book.getBookid();
			if(book.getAmont()!=-1)
				pageSql = pageSql + " and amont = " + book.getAmont();
			// 改造后带分页查询的SQL语句
			pageSql = pageSql + " limit " + page.getDbIndex() + "," + page.getDbNumber();
			System.out.println(pageSql);
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		
		return invocation.proceed();
	}

	/**
	 * @param 拦截对象
	 */
	@Override
	public Object plugin(Object target)
	{
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}
