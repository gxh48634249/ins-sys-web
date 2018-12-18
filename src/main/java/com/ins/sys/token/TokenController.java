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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static void main(String[] agrs) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        Date date = new Date(1544428097974L);
        System.out.println(sf.format(date));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        QToken qToken = QToken.token;
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MINUTE, -30);
        System.out.println(calendar.getTime().getTime());
        System.out.println(calendar1.getTime().getTime());
    }
}
