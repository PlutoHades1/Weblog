package com.zh.common.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 分页的参数
 */
public class PageBean<E> implements Serializable {
    private int pageNum = 1;    //当前页号
    private int pageTotal;  //总页数
    private int size;   //每页记录数
    private long total;  //总记录数
    private List<E> rows;   //此页的数据

    public PageBean(){}

    public PageBean(int pageNum, int size) {
        this.pageNum = pageNum;
        this.size = size;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getRows() {
        return rows;
    }

    public void setRows(List<E> rows) {
        this.rows = rows;
    }
}
