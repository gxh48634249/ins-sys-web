package com.ins.sys.esentity.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.text.SimpleDateFormat;
import java.util.Date;

@Document(indexName = "ens_weibo",type = "resource")
public class ENSWeb {

    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Keyword)
    private String nick_name;

    @Field(type = FieldType.Text)
    private String user_url;

    @Field(type = FieldType.Text,analyzer = "ik")
    private String content;

    @Field(type = FieldType.Text)
    private String url;

    @Field(type = FieldType.Text)
    private String weiboId;

    @Field(type = FieldType.Text)
    private String snapshot;

    @Field(type = FieldType.Text)
    private String publish_time;

    @Field(type = FieldType.Text)
    private String spider_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(String snapshot) {
        this.snapshot = snapshot;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getUser_url() {
        return user_url;
    }

    public void setUser_url(String user_url) {
        this.user_url = user_url;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getSpider_time() {
        return spider_time;
    }

    public void setSpider_time(String spider_time) {
        this.spider_time = spider_time;
    }
}
