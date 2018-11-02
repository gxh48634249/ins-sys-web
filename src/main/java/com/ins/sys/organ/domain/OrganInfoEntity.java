package com.ins.sys.organ.domain;

import com.ins.sys.tools.StringTool;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "organ_info", schema = "sys_info", catalog = "")
public class OrganInfoEntity {

    @ApiModelProperty("组织结构主键")
    private String organId;

    @ApiModelProperty("组织机构名称")
    private String organName;

    @ApiModelProperty("组织机构编码")
    private String organCode;

    @ApiModelProperty("组织机构描述")
    private String organDesc;

    @ApiModelProperty("创建时间")
    private Long createTime;

    @ApiModelProperty("父机构编码")
    private String parentCode;

    @Id
    @Column(name = "organ_id")
    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    @Basic
    @Column(name = "organ_name")
    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    @Basic
    @Column(name = "organ_code")
    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    @Basic
    @Column(name = "organ_desc")
    public String getOrganDesc() {
        return organDesc;
    }

    public void setOrganDesc(String organDesc) {
        this.organDesc = organDesc;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long cvreateTime) {
        this.createTime = cvreateTime;
    }

    @Basic
    @Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganInfoEntity that = (OrganInfoEntity) o;
        return Objects.equals(organId, that.organId) &&
                Objects.equals(organName, that.organName) &&
                Objects.equals(organCode, that.organCode) &&
                Objects.equals(organDesc, that.organDesc) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(parentCode, that.parentCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(organId, organName, organCode, organDesc, createTime, parentCode);
    }
    public boolean validate() {
        if (this==null) return  false;
        return !StringTool.isnull(this.organCode)&&
                !StringTool.isnull(this.organName) &&
                !StringTool.isnull(this.parentCode);
    }
}
