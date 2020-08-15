package com.huanghao.server.dto;

import java.util.List;

/**
 * @author HuangHao
 * @date 2020/8/15 23:49
 */
public class PageDto<T> {

    /**
     * 当前页码
     */
    protected int page;

    /**
     * 每条页数
     */
    protected int size;

    /**
     * 总条数
     */
    protected long total;

    protected List<T> list;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
