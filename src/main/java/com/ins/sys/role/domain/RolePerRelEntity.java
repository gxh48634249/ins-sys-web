package com.ins.sys.role.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role_per_rel", schema = "sys_info", catalog = "")
public class RolePerRelEntity {
    private String roleId;
    private String permissionId;

    @Id
    @Column(name = "role_id")
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
        RolePerRelEntity that = (RolePerRelEntity) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(permissionId, that.permissionId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, permissionId);
    }
}
