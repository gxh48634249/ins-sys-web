package com.ins.sys.user.web;


import com.ins.sys.organ.domain.QOrganInfoEntity;
import com.ins.sys.tools.*;
import com.ins.sys.user.domain.*;
import com.ins.sys.user.service.UserInfoService;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Random;

@Api(tags = "用户管理")
@RequestMapping("user")
@RestController
public class UserController {

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Autowired
    private UserInfoService userInfoService;

    @Transactional
    @RequestMapping("insertUser")
    @ApiOperation(value = "新增用户",httpMethod = "POST")
    public Result insertUser(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity sysUserInfoEntity = userWeb.getUserInfoEntity();
        try{
            sysUserInfoEntity.setUserStatue(2);
            String account = sysUserInfoEntity.getUserAccount();
            Result result = userInfoService.insert(sysUserInfoEntity);
            sysUserInfoEntity = (SysUserInfoEntity) result.getData();
            String id = sysUserInfoEntity.getUserId();
            userInfoService.insertUserOrgan(id,userWeb.getOrganId());
            userInfoService.insertUserPer(id,userWeb.getPermissionIds());
            userInfoService.insertUserRole(id,userWeb.getRoleIds());
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("deleteUser")
    @ApiOperation(value = "删除用户",httpMethod = "POST")
    public Result deleteUser(@RequestBody UserWeb userWeb) {
        String userId = userWeb.getUserInfoEntity().getUserId();
        if(StringTool.isnull(userId)) {
            return new Result(Constant.NULL_PARAM);
        }
        try{
            return userInfoService.deleteUser(userId);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("modifyUser")
    @ApiOperation(value = "修改用户信息",httpMethod = "POST")
    public Result modifyUser(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity userInfoEntity = userWeb.getUserInfoEntity();
        Long phone = userInfoEntity.getPhone();
        String id = userInfoEntity.getUserId();
        String userName = userInfoEntity.getUserName();
        if(StringTool.isnull(id)) {
            return new Result(Constant.NULL_PARAM);
        }
        QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
        JPAQuery query = queryFactory.select(qSysUserInfoEntity)
                .from(qSysUserInfoEntity)
                .where(qSysUserInfoEntity.phone.eq(phone)
                        .and(qSysUserInfoEntity.userName.eq(userName)));
        try{
            if(ListUtill.isnull(query.fetch())) {
                this.userInfoService.insertUserRole(id,userWeb.getRoleIds());
                this.userInfoService.insertUserPer(id,userWeb.getPermissionIds());
                this.userInfoService.insertUserOrgan(id,userWeb.getOrganId());
                this.userInfoService.modifyUser(userInfoEntity);
                return new Result(Constant.OK);
            }else return new Result(Constant.FAIL_STATUE,"账户，邮箱，身份证号，微信，手机号已存在");
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("selectUser")
    @ApiOperation(value = "查询用户",httpMethod = "POST")
    public Result selectUser(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity sysUserInfoEntity = userWeb.getUserInfoEntity()==null?new SysUserInfoEntity():userWeb.getUserInfoEntity();
        PageInfo pageInfo = userWeb.getPageInfo();
        if(pageInfo==null) {
            return new Result(Constant.NULL_PARAM);
        }
        try{
            return this.userInfoService.selectUser(sysUserInfoEntity,userWeb.getPageInfo());
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("bindOrgan")
    @ApiOperation(value = "用户绑定机构",httpMethod = "POST")
    public Result bindOrgan(@RequestBody UserOrganRelEntity entity) {
        try{
            if(entity.validate()) {
                String userId = entity.getUserId();
                String organId = entity.getOrganId();
                this.userInfoService.insertUserOrgan(userId,organId);
                return new Result(Constant.OK);
            }return new Result(Constant.NULL_PARAM);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("bindPer")
    @ApiOperation(value = "用户绑定许可",httpMethod = "POST")
    public Result bindPer(@RequestBody String userId,@RequestBody String permissionIds) {
        if(StringTool.isnull(userId)) {
            return new Result(Constant.NULL_PARAM);
        }
        try{
            this.userInfoService.insertUserPer(userId,permissionIds);
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("initPwd")
    @ApiOperation(value = "初始化用户密码（随机8位）",httpMethod = "POST")
    public Result initPwd(@RequestBody String userId) {
        try{
            String pwd = getStringRandom(8);
            QSysUserInfoEntity userInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            queryFactory.update(userInfoEntity)
                    .set(userInfoEntity.userPwd,MD5.encrypt(pwd))
                    .where(userInfoEntity.userId.eq(userId))
                    .execute();
            return new Result(Constant.SUCCESS_STATUE,"初始化成功",pwd);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("bindRole")
    @ApiOperation(value = "绑定角色",httpMethod = "POST")
    public Result bindRole(@RequestBody String userId,@RequestBody String roleIds) {
        try{
            if(StringTool.isnull(userId)) {
                return new Result(Constant.NULL_PARAM);
            }
            this.userInfoService.insertUserRole(userId,roleIds);
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    private String getStringRandom(int length) {

        String val = "";
        Random random = new Random();
        //length为几位密码
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }
    @Transactional
    @RequestMapping("lockOpertion")
    @ApiOperation(value = "用户锁定操作",httpMethod = "POST")
    public Result lockOpertion(@RequestBody String userId,@RequestBody Integer statue) {
        if(StringTool.isnull(userId)) {
            return new Result(Constant.NULL_PARAM);
        }
        try{
            Integer temp = 0;
            if(null==statue) {
                temp = 1;
            }else {
                temp = statue;
            }
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            queryFactory.update(qSysUserInfoEntity)
                    .set(qSysUserInfoEntity.userStatue,temp)
                    .where(qSysUserInfoEntity.userId.eq(userId)).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("selectUserEasy")
    @ApiOperation(value = "查询用户(简版)",httpMethod = "POST")
    public Result selectUserEasy(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity sysUserInfoEntity = userWeb.getUserInfoEntity()==null?new SysUserInfoEntity():userWeb.getUserInfoEntity();
        PageInfo pageInfo = userWeb.getPageInfo();
        if(pageInfo==null) {
            return new Result(Constant.NULL_PARAM);
        }
        String userName = sysUserInfoEntity.getUserName();
        if(StringTool.isnull(userName)) {
            userName = "";
        }
        Integer userstatue = sysUserInfoEntity.getUserStatue();
        try{
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            if (null!=userstatue&&userstatue>-1) {
                JPAQuery query = queryFactory.select(qSysUserInfoEntity).from(qSysUserInfoEntity)
                        .where(qSysUserInfoEntity.userName.like(StringTool.sqlLike(userName)).or(qSysUserInfoEntity.userAccount.like(StringTool.sqlLike(userName))).and(qSysUserInfoEntity.userStatue.eq(userstatue)))
                        .orderBy(qSysUserInfoEntity.createTime.asc())
                        .limit(pageInfo.getPageSize())
                        .offset(pageInfo.getPageNum()*pageInfo.getPageSize());
                pageInfo.setTotalInfo(query.fetchCount());
                return new Result(Constant.SUCCESS_STATUE,"查询成功", query.fetch(),pageInfo);
            }else {
                JPAQuery query = queryFactory.select(qSysUserInfoEntity).from(qSysUserInfoEntity)
                        .where(qSysUserInfoEntity.userName.like(StringTool.sqlLike(userName)).or(qSysUserInfoEntity.userAccount.like(StringTool.sqlLike(userName))))
                        .orderBy(qSysUserInfoEntity.createTime.asc())
                        .limit(pageInfo.getPageSize())
                        .offset(pageInfo.getPageNum()*pageInfo.getPageSize());
                pageInfo.setTotalInfo(query.fetchCount());
                return new Result(Constant.SUCCESS_STATUE,"查询成功", query.fetch(),pageInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("changeUserStatue")
    @ApiOperation(value = "拉黑/锁定/激活 用户",httpMethod = "POST")
    public Result changeUserStatue(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity sysUserInfoEntity = userWeb.getUserInfoEntity()==null?new SysUserInfoEntity():userWeb.getUserInfoEntity();
        String userId = sysUserInfoEntity.getUserId();
        if(StringTool.isnull(userId)) {
            return new Result(Constant.NULL_PARAM);
        }
        Integer userStatue = sysUserInfoEntity.getUserStatue();
        if(userStatue==null){
            userStatue = 1;
        }
        try{
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            queryFactory.update(qSysUserInfoEntity)
                    .set(qSysUserInfoEntity.userStatue,userStatue)
                    .where(qSysUserInfoEntity.userId.eq(userId)).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("selectUserOrganInfo")
    @ApiOperation(value = "查询用户信息(包含机构)",httpMethod = "POST")
    public Result selectUserOrganInfo(@RequestBody UserWeb userWeb) {
        SysUserInfoEntity sysUserInfoEntity = userWeb.getUserInfoEntity()==null?new SysUserInfoEntity():userWeb.getUserInfoEntity();
        Integer userStatue = sysUserInfoEntity.getUserStatue();
        PageInfo pageInfo = userWeb.getPageInfo();
        if(userStatue==null){
            userStatue = 1;
        }
        if(null==pageInfo){
            pageInfo = new PageInfo();
            pageInfo.setPageNum(0);
            pageInfo.setPageSize(10);
        }
        String userName = sysUserInfoEntity.getUserName();
        try{
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
//            QueryDSLEntity queryDSLEntity = new QueryDSLEntity();
//            queryDSLEntity.add(qSysUserInfoEntity.userId);
//            queryDSLEntity.add(qSysUserInfoEntity.userName);
//            queryDSLEntity.add(qSysUserInfoEntity.userAccount);
//            queryDSLEntity.add(qSysUserInfoEntity.phone);
//            queryDSLEntity.add(qSysUserInfoEntity.userStatue);
//            queryDSLEntity.add(qSysUserInfoEntity.address);
//            queryDSLEntity.add(qSysUserInfoEntity.createTime);
//            queryDSLEntity.add(qSysUserInfoEntity.expiredTime);
//            queryDSLEntity.add(qSysUserInfoEntity.gender);
//            queryDSLEntity.add(qOrganInfoEntity.organId);
//            queryDSLEntity.add(qOrganInfoEntity.organName);
//            queryDSLEntity.add(qOrganInfoEntity.organCode);
//            queryDSLEntity.add(qOrganInfoEntity.parentCode);
//            queryDSLEntity.add(qOrganInfoEntity);
//            queryDSLEntity.add(qSysUserInfoEntity);
//            if(!StringTool.isnull(userName)) {
//                queryDSLEntity.add(qSysUserInfoEntity.userName.like(StringTool.sqlLike(userName)).or(qSysUserInfoEntity.userAccount.like(StringTool.sqlLike(userName))).or(qOrganInfoEntity.organName.like(StringTool.sqlLike(userName))));
//            }
//            queryDSLEntity.add(qSysUserInfoEntity.createTime.asc());
            QUserOrganRelEntity qUserOrganRelEntity  = QUserOrganRelEntity.userOrganRelEntity;
            JPAQuery query = queryFactory.select(qSysUserInfoEntity,QOrganInfoEntity.organInfoEntity)
                    .from(qSysUserInfoEntity).leftJoin(qUserOrganRelEntity).on(qOrganInfoEntity.organId.eq(qUserOrganRelEntity.userId))
                    .leftJoin(qOrganInfoEntity).on(qOrganInfoEntity.organId.eq(qUserOrganRelEntity.organId))
                    .where(qSysUserInfoEntity.userAccount.like(StringTool.sqlLike(userName)).or(qSysUserInfoEntity.userName.like(StringTool.sqlLike(userName))).or(qOrganInfoEntity.organName.like(StringTool.sqlLike(userName))));
            return new Result(Constant.SUCCESS_STATUE,"查询成功",query.fetch());
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

}
