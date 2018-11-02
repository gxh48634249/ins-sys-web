package com.ins.sys.user.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_role_rel", schema = "sys_info", catalog = "")
public class UserRoleRelEntity {
    private String userId;
    private String roleId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleRelEntity that = (UserRoleRelEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, roleId);
    }
}
