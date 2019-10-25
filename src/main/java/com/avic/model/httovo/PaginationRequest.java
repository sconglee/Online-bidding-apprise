package com.avic.model.httovo;

/**
 * @ClassName PaginationRequest
 * @Description 接收分页请求数据
 * @Author xulei
 * @Date 2019/10/25/025 8:58
 * @Version 1.0
 **/
public class PaginationRequest {

    /**
     需要查询的页码
    **/
    private Integer page;

    /**
     每页的数据条数
    **/
    private Integer columns;

    /**
     计算得出开始数值
    **/
    private Integer startNumber;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    @Override
    public String toString() {
        return "PaginationRequest{" +
                "page=" + page +
                ", columns=" + columns +
                ", startNumber=" + startNumber +
                '}';
    }


}
