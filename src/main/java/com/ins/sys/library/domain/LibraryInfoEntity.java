package com.ins.sys.library.domain;

import com.ins.sys.tools.StringTool;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "library_info", schema = "sys_info", catalog = "")
public class LibraryInfoEntity {

    @ApiModelProperty("数据字典主健")
    private String libraryId;

    @ApiModelProperty("数据字典名称")
    private String libraryName;

    @ApiModelProperty("数据字典编码")
    private String libraryCode;

    @ApiModelProperty("数据字典父级编码（没有请填写0）")
    private String parentCode;

    @ApiModelProperty("数据字典创建时间")
    private Long createTime;

    @Id
    @Column(name = "library_id")
    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    @Basic
    @Column(name = "library_name")
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    @Basic
    @Column(name = "library_code")
    public String getLibraryCode() {
        return libraryCode;
    }

    public void setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
    }

    @Basic
    @Column(name = "parent_code")
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Basic
    @Column(name = "create_time")
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryInfoEntity that = (LibraryInfoEntity) o;
        return Objects.equals(libraryId, that.libraryId) &&
                Objects.equals(libraryName, that.libraryName) &&
                Objects.equals(libraryCode, that.libraryCode) &&
                Objects.equals(parentCode, that.parentCode) &&
                Objects.equals(createTime, that.createTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(libraryId, libraryName, libraryCode, parentCode, createTime);
    }

    public boolean validate(){
        if(this ==null) return false;
        return !StringTool.isnull(libraryCode)&&
                !StringTool.isnull(libraryName)&&
                !StringTool.isnull(parentCode);
    }
}
