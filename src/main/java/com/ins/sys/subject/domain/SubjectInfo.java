package com.ins.sys.subject.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "subject_info", schema = "sys_info", catalog = "")
public class SubjectInfo {
    private String id;
    private String mustContain;
    private String notContain;
    private Date createTime;
    private String createUser;
    private String subjectName;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "must_contain")
    public String getMustContain() {
        return mustContain;
    }

    public void setMustContain(String mustContain) {
        this.mustContain = mustContain;
    }

    @Basic
    @Column(name = "not_contain")
    public String getNotContain() {
        return notContain;
    }

    public void setNotContain(String notContain) {
        this.notContain = notContain;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "create_user")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "subject_name")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectInfo that = (SubjectInfo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(mustContain, that.mustContain) &&
                Objects.equals(notContain, that.notContain) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(createUser, that.createUser) &&
                Objects.equals(subjectName, that.subjectName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mustContain, notContain, createTime, createUser, subjectName);
    }
}
