package com.ins.sys.token;

import com.ins.sys.user.domain.SysUserInfoEntity;

import javax.persistence.*;

@Entity
@Table(name = "token_info", schema = "sys_info", catalog = "")
public class Token {

    private String id;

    private String tokenUser;

    private String tokenInfo;

    private Long lastTime;

    private Long createTime;

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_time")
    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "token_user")
    public String getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(String tokenUser) {
        this.tokenUser = tokenUser;
    }

    @Basic
    @Column(name = "token_info")
    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }
}
