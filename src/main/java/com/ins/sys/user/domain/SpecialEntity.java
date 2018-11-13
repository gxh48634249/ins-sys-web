package com.ins.sys.user.domain;

import com.ins.sys.organ.domain.OrganInfoEntity;
import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.tools.ListUtill;
import com.ins.sys.tools.StringTool;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "specialList", schema = "sys_info", catalog = "")
public class SpecialEntity  {

    @ApiModelProperty("专题主键")
    private String special_id;

    @ApiModelProperty("专题名字")
    private String special_name;

    @ApiModelProperty("专题内容")
    private String special_content;

    @Id
    @Column(name = "special_id")
    public String getSpecial_id() {
        return special_id;
    }

    public void setSpecial_id(String special_id) {
        this.special_id = special_id;
    }

    @Basic
    @Column(name = "special_name")
    public String getSpecial_name() {
        return special_name;
    }

    public void setSpecial_name(String special_name) {
        this.special_name = special_name;
    }
    @Basic
    @Column(name = "special_content")
    public String getSpecial_content() {
        return special_content;
    }

    public void setSpecial_content(String special_content) {
        this.special_content = special_content;
    }
}
