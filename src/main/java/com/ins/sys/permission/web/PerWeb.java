package com.ins.sys.permission.web;

import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.tools.PageInfo;

public class PerWeb {

    private PermissionInfoEntity permissionInfoEntity;

    private PageInfo pageInfo;

    private String roleId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public PermissionInfoEntity getPermissionInfoEntity() {
        return permissionInfoEntity;
    }

    public void setPermissionInfoEntity(PermissionInfoEntity permissionInfoEntity) {
        this.permissionInfoEntity = permissionInfoEntity;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
