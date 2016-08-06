package com.lauguobin.www.po;

import java.util.List;

/** 
 */  
public class PagedResult<T>
{  
      
    /*serialVersionUID*/  
  
    private List<T> dataList;//数据
      
    private long pageNo = 0;//当前页（从第x条开始显示）
      
    private long pageSize = 10;//条数  
      
    private long total;//总条数  
      
    private long pages;//总页面数目  
  
    public PagedResult(long pageNo, long pageSize)
	{
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
    
	public PagedResult()
	{	}

	public List<T> getDataList() 
    {  
        return dataList;  
    }  
  
    public void setDataList(List<T> dataList) 
    {  
        this.dataList = dataList;  
    }  
  
    public long getPageNo() 
    {  
        return pageNo;  
    }  
  
    public void setPageNo(long pageNo) 
    {  
        this.pageNo = pageNo;  
    }
  
    public long getPageSize() 
    {  
        return pageSize;  
    }  
  
    public void setPageSize(long pageSize) 
    {  
        this.pageSize = pageSize;  
    }  
  
    public long getTotal() 
    {  
        return total;  
    }  
  
    public void setTotal(long total) 
    {  
        this.total = total;  
    }  
  
    public long getPages() 
    {  
        return pages;  
    }  
  
    public void setPages(long pages) 
    {  
        this.pages = pages;  
    }  
}  