package com.ins.sys.user.domain;

import com.ins.sys.tools.StringTool;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_organ_rel", schema = "sys_info", catalog = "")
public class UserOrganRelEntity {
    private String userId;
    private String organId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "organ_id")
    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrganRelEntity that = (UserOrganRelEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(organId, that.organId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, organId);
    }

    public boolean validate() {
        return !StringTool.isnull(this.userId)&&!StringTool.isnull(this.organId);
    }
}
