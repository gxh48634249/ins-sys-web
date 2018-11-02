package com.ins.sys.tools;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于向前端返回请求信息
 */
public class Result {

    //1:成功，-1：失败，2：权限不够，3：未登陆
    @ApiModelProperty("请求状态")
    private Integer statue;

    //请求结果描述
    @ApiModelProperty("请求描述")
    private String message;

    //请求数据
    @ApiModelProperty("返回数据")
    private Object data;

    //分页信息
    @ApiModelProperty("分页信息")
    private PageInfo pageInfo;

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Result(Integer statue, String message) {
        this.statue = statue;
        this.message = message;
    }

    //1：操作成功/2：服务器异常/3：参数为空
    public Result(Integer statue) {
        if(statue==1){
            this.statue = Constant.SUCCESS_STATUE;
            this.message = "操作成功";
        }
        if(statue==2){
            this.statue = Constant.FAIL_STATUE;
            this.message = "服务器异常，请稍后再试";
        }
        if(statue==3){
            this.statue = Constant.FAIL_STATUE;
            this.message = "参数为空";
        }

    }
    public Result(Integer statue, String message, Object data) {
        this.statue = statue;
        this.message = message;
        this.data = data;
    }

    public Result(Integer statue, String message, Object data, PageInfo pageInfo) {
        this.statue = statue;
        this.message = message;
        this.data = data;
        this.pageInfo = pageInfo;
    }
}
