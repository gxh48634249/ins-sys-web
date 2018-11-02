package com.ins.sys.user.web;

import com.ins.sys.tools.PageInfo;
import com.ins.sys.user.domain.SysUserInfoEntity;

public class UserWeb {

    private SysUserInfoEntity userInfoEntity;

    private PageInfo pageInfo;

    private String permissionIds;

    private String organId;

    private String roleIds;

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public SysUserInfoEntity getUserInfoEntity() {
        return userInfoEntity;
    }

    public void setUserInfoEntity(SysUserInfoEntity userInfoEntity) {
        this.userInfoEntity = userInfoEntity;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
