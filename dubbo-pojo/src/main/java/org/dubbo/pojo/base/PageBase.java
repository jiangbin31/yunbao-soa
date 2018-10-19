package org.dubbo.pojo.base;

import java.io.Serializable;

/**
 * Created by Jiangbin on 2017/11/20.
 */
public class PageBase implements Serializable {



    private Integer pageNo=1;
    private Integer pageSize=20;


    private Integer endRow;
    private Integer startRow;


    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        this.startRow=(pageNo-1)*pageSize;
        this.endRow=pageNo*pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        this.startRow=(pageNo-1)*pageSize;
        this.endRow=pageNo*pageSize;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }
}
