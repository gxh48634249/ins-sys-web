//package com.ins.sys.esentity.domain;
//
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
//public class ContentEntity {
//    @Field(type = FieldType.Keyword)
//    private String url;
//
//    @Field(type = FieldType.Text,analyzer = "ik")
//    private String title;
//
//    @Field(type = FieldType.Text)
//    private String content;
//
//    @Field(type = FieldType.Date)
//    private Date publishTime;
//
//    @Field(type = FieldType.Date)
//    private Date spiderTime;
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    public Date getPublishTime() {
//        return publishTime;
//    }
//
//    public void setPublishTime(Date publishTime) {
//        this.publishTime = publishTime;
//    }
//
//    public Date getSpiderTime() {
//        return spiderTime;
//    }
//
//    public void setSpiderTime(Date spiderTime) {
//        this.spiderTime = spiderTime;
//    }
//}
