package com.ins.sys.user.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_permission_rel", schema = "sys_info", catalog = "")
public class UserPermissionRelEntity {
    private String userId;
    private String permissionId;

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "permission_id")
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissionRelEntity that = (UserPermissionRelEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, permissionId);
    }
}
