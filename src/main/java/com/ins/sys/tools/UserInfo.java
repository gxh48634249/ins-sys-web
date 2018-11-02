package com.ins.sys.tools;

import com.ins.sys.user.domain.SysUserInfoEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 获取当前登录用户信息
 */
public class UserInfo {
    public SysUserInfoEntity getUser() {
        SysUserInfoEntity user = new SysUserInfoEntity();
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) user = (SysUserInfoEntity) auth.getPrincipal();
        return user;
    }
}
