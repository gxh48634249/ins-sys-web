package com.ins.sys.token;

import com.ins.sys.tools.Constant;
import com.ins.sys.tools.PageInfo;
import com.ins.sys.tools.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("token")
@Api(value = "登录用户管理", tags = "登录用户管理")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping("getAllByUserLike")
    @ApiOperation(value = "根据用户信息检索登录用户信息",httpMethod = "POST")
    public Result getAllByUserLike(Token token, PageInfo pageInfo) {
        try {
            Page<Token> page = tokenService.findByPage(token,pageInfo);
            return new Result(Constant.SUCCESS_STATUE,"查询成功",page.getContent(),pageInfo.getResult(page));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("logoutUser")
    @ApiOperation(value = "强制用户退出",httpMethod = "POST")
    public Result logoutUser(Token token) {
        try {
            return new Result(Constant.SUCCESS_STATUE,"推出成功",tokenService.remove(token));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }
}
