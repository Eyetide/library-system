package com.lauguobin.www.dao;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import com.lauguobin.www.po.Book;
import com.lauguobin.www.util.DbUtil;

public class BookDao
{
	/**
	 * 添加一本书进数据库并且写入图片
	 * @param book
	 * @param fileName
	 * @param item
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int addBook(Book book,String fileName,FileItem item,String path) throws IOException 
	{
		//生成的文件以id命名作为索引，格式保持上传图片的格式不变
        File file = new File(path + book.getBookid() + fileName);
        SqlSession session = DbUtil.getSqlSession();
        String statement = "bookMapper.addBook";
        int result = session.insert(statement, book);
        session.close();
        try
		{
			item.write(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新一本书的信息（包括图片）
	 * @param book
	 * @param fileName
	 * @param item
	 * @param path
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateBook(Book book,String fileName,FileItem item,String path) throws  IOException
	{
        File file = new File(path + book.getBookid() + fileName);
		SqlSession session = DbUtil.getSqlSession();
		String statement = "bookMapper.updateBook";
		
		int result = session.update(statement, book);
		session.close();
        try
		{
			item.write(file);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return result;
	}
	
	/**
	 * 仅仅更新一本书的文字信息
	 * @param book
	 * @throws IOException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int updateBook(Book book) throws IOException, SQLException, ClassNotFoundException
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "bookMapper.updateBook";
		
		int result = session.update(statement, book);
		session.close();
		return result;
	}
	
	/**
	 * 删除一本书，并且删除图片
	 * @param bookid
	 * @param filePath 获取当前图片的路径（项目下路径）
	 * @throws IOException 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int deleteBook(int bookid,String filePath) throws IOException 
	{
        File file = new File(filePath + bookid + ".jpg");
        
		SqlSession session = DbUtil.getSqlSession();
		String statement = "bookMapper.deleteBook";
		
		int result = session.delete(statement, bookid);
		
		session.close();
        try
		{
			file.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
        return result;
	}
	
	/**
	 * 获取所有已经上架的书籍
	 * @return
	 * @throws Exception
	 */
	public List<Book> getExistBooks() throws Exception
	{
		SqlSession session = DbUtil.getSqlSession();
		String statement = "bookMapper.allBooks";
		
		List<Book> list = session.selectList(statement);
		session.close();
		return list;
	}
	
	/**
	 *  获取搜索到的书籍
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Book> getSearchBooks(String sql) throws IOException
	{
		//TODO 

		
		
		
		
		
		return new ArrayList<Book>();
	}
}
