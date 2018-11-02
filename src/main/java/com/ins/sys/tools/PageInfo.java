package com.ins.sys.tools;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * 用于封装分页信息
 */
public class PageInfo {

    //每页数量
    @ApiModelProperty("每页數量")
    private Integer pageSize;

    //当前页数
    @ApiModelProperty("当前页数")
    private Integer pageNum;

    //总数量
    @ApiModelProperty("总数量")
    private Long totalInfo;

    //总页数
    @ApiModelProperty("总页数")
    private Integer totalPage;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(Long totalInfo) {
        this.totalInfo = totalInfo;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean vaidate() {
        if(null==this) {
            return false;
        }
        return null!=this.pageNum&&null!=this.pageSize;
    }

    public PageRequest getPageRequest(Sort sort){
        return PageRequest.of(this.pageNum,this.pageSize,sort);
    }

    public PageInfo getResult(Page page){
        this.totalInfo = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        return this;
    }
}
