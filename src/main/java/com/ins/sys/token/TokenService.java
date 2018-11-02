package com.ins.sys.token;

import com.ins.sys.tools.SimpleService;
import com.ins.sys.user.domain.SysUserInfoEntity;

public interface TokenService extends SimpleService<Token> {

    Long getTotal() throws  Exception;

    SysUserInfoEntity findUserByToken(String token) throws Exception;
}
