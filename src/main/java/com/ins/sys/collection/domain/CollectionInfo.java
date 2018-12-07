package com.ins.sys.collection.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "collection_info", schema = "sys_info", catalog = "")

public class CollectionInfo {

    private String id;
    private String articleId;
    private String usrsId;
    private Date createTime;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "article_id")
    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    @Basic
    @Column(name = "usrs_id")
    public String getUsrsId() {
        return usrsId;
    }

    public void setUsrsId(String usrsId) {
        this.usrsId = usrsId;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionInfo that = (CollectionInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(articleId, that.articleId) &&
                Objects.equals(usrsId, that.usrsId) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, articleId, usrsId, createTime);
    }
}
