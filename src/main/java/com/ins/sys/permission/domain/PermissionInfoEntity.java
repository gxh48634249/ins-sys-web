package com.ins.sys.permission.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permission_info", schema = "sys_info", catalog = "")
public class PermissionInfoEntity {

    @ApiModelProperty("许可主键")
    private String permissionId;

    @ApiModelProperty("许可名称")
    private String permissionName;

    @ApiModelProperty("许可编码")
    private String permissionCode;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("许可描述")
    private String permissionDesc;

    @ApiModelProperty("资源标识")
    private String resourceId;

    @Id
    @Column(name = "permission_id")
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission_name")
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Basic
    @Column(name = "permission_code")
    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "permission_desc")
    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    @Basic
    @Column(name = "resource_id")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionInfoEntity that = (PermissionInfoEntity) o;
        return Objects.equals(permissionId, that.permissionId) &&
                Objects.equals(permissionName, that.permissionName) &&
                Objects.equals(permissionCode, that.permissionCode) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(permissionDesc, that.permissionDesc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(permissionId, permissionName, permissionCode, createTime, permissionDesc);
    }
}
