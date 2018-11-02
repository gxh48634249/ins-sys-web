package com.ins.sys.role.web;

import com.ins.sys.role.domain.RoleInfoEntity;
import com.ins.sys.tools.PageInfo;

public class RoleWeb {

    private RoleInfoEntity roleInfoEntity;

    private PageInfo pageInfo;

    private String permissionId;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public RoleInfoEntity getRoleInfoEntity() {
        return roleInfoEntity;
    }

    public void setRoleInfoEntity(RoleInfoEntity roleInfoEntity) {
        this.roleInfoEntity = roleInfoEntity;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
