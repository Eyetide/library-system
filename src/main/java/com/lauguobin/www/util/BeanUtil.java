package com.lauguobin.www.util;

import java.util.List;

import com.github.pagehelper.Page;
import com.lauguobin.www.po.PagedResult;

/**
 * BeanUtil是一个将PageHelper返回的list转成pageResult的工具
 * @author not me
 */
public class BeanUtil 
{  
    public static <T> PagedResult<T> toPagedResult(List<T> datas) {  
        PagedResult<T> result = new PagedResult<T>();  
        if (datas instanceof Page) {  
            Page page = (Page) datas;  
            result.setPageNo(page.getPageNum());  
            result.setPageSize(page.getPageSize());  
            result.setDataList(page.getResult());  
            result.setTotal(page.getTotal());  
            result.setPages(page.getPages());  
        }  
        else {  
            result.setPageNo(1);  
            result.setPageSize(datas.size());  
            result.setDataList(datas);  
            result.setTotal(datas.size());  
        }  
  
        return result;  
    }  
  
}  