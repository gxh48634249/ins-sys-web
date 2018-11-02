package com.ins.sys.resource.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "resource_info", schema = "sys_info", catalog = "")
public class ResourceInfoEntity {

    @ApiModelProperty("资源主键")
    private String resourceId;

    @ApiModelProperty("资源名称")
    private String resourceName;

    @ApiModelProperty("资源编码")
    private String resourceCode;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("资源描述")
    private String resourceDes;

    @ApiModelProperty("资源路径")
    private String resourcePath;

    @ApiModelProperty("父资源编码")
    private String resourceParentCode;

    @ApiModelProperty("资源类型")
    private String resourceType;

    @Id
    @Column(name = "resource_id")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "resource_name")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "resource_code")
    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
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
    @Column(name = "resource_des")
    public String getResourceDes() {
        return resourceDes;
    }

    public void setResourceDes(String resourceDes) {
        this.resourceDes = resourceDes;
    }

    @Basic
    @Column(name = "resource_path")
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "resource_parent_code")
    public String getResourceParentCode() {
        return resourceParentCode;
    }

    public void setResourceParentCode(String resourceParentCode) {
        this.resourceParentCode = resourceParentCode;
    }

    @Basic
    @Column(name = "resource_type")
    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResourceInfoEntity that = (ResourceInfoEntity) o;
        return Objects.equals(resourceId, that.resourceId) &&
                Objects.equals(resourceName, that.resourceName) &&
                Objects.equals(resourceCode, that.resourceCode) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(resourceDes, that.resourceDes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(resourceId, resourceName, resourceCode, createTime, resourceDes);
    }
}
