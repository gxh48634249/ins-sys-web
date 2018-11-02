package com.ins.sys.user.service;

import com.ins.sys.tools.PageInfo;
import com.ins.sys.tools.Result;
import com.ins.sys.user.domain.SysUserInfoEntity;

public interface UserInfoService {

    public Result insert(SysUserInfoEntity userInfoEntity)throws Exception;

    public Result insertUserRole(String userId,String roleIds)throws Exception;

    public Result insertUserPer(String userId,String permissionIds)throws Exception;

    public Result insertUserOrgan(String userId,String organId)throws Exception;

    public Result deleteUser(String userId)throws Exception;

    public Result modifyUser(SysUserInfoEntity userInfoEntity)throws Exception;

    public Result selectUser(SysUserInfoEntity userInfoEntity,PageInfo pageInfo)throws Exception;
}
