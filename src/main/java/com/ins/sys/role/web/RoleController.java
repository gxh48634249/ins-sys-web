package com.ins.sys.role.web;

import com.ins.sys.role.domain.*;
import com.ins.sys.tools.*;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("role")
@Api(tags = "角色管理")
public class RoleController {

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Autowired
    private RolePerRepository rolePerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @RequestMapping("insertRole")
    @ApiOperation(value = "新增角色",httpMethod = "POST")
    public Result insertRole(@RequestBody RoleWeb roleWeb){
        RoleInfoEntity roleInfoEntity = roleWeb.getRoleInfoEntity();
        String permissionId = roleWeb.getPermissionId();
        String code = roleInfoEntity.getRoleCode();
        String name = roleInfoEntity.getRoleName();
        if(StringTool.isnull(name)||StringTool.isnull(code)) {
            return  new Result(Constant.NULL_PARAM);
        }
        try{
            QRoleInfoEntity qRoleInfoEntity = QRoleInfoEntity.roleInfoEntity;
            JPAQuery query = queryFactory.select(qRoleInfoEntity)
                    .from(qRoleInfoEntity)
                    .where(qRoleInfoEntity.roleCode.eq(roleInfoEntity.getRoleCode()));
            if(ListUtill.isnull(query.fetch())) {
                roleInfoEntity.setCreateTime(new Date().getTime());
                roleInfoEntity.setRoleId(MD5.encrypt(roleInfoEntity.getRoleCode()));
                roleInfoEntity = this.roleRepository.save(roleInfoEntity);
                RolePerRelEntity rolePerRelEntity = new RolePerRelEntity();
                rolePerRelEntity.setRoleId(roleInfoEntity.getRoleId());
                rolePerRelEntity.setPermissionId(permissionId);
                this.roleBindPer(rolePerRelEntity);
                return new Result(Constant.SUCCESS_STATUE,"新增成功",roleInfoEntity);
            }else return new Result(Constant.FAIL_STATUE,"角色编码重复");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("deleteRole")
    @ApiOperation(value = "删除角色",httpMethod = "POST")
    public Result deleteRole(@RequestBody RoleInfoEntity roleInfoEntity){
        String roleId = roleInfoEntity.getRoleId();
        try {
            QRoleInfoEntity qRoleInfoEntity = QRoleInfoEntity.roleInfoEntity;
            QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
            queryFactory.delete(qRolePerRelEntity)
                    .where(qRolePerRelEntity.roleId.eq(roleId));
            queryFactory.delete(qRoleInfoEntity)
                    .where(qRoleInfoEntity.roleId.eq(roleId)).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("modifyRole")
    @ApiOperation(value = "修改角色",httpMethod = "POST")
    public Result modifyRole(@RequestBody RoleWeb roleWeb){
        RoleInfoEntity roleInfoEntity = roleWeb.getRoleInfoEntity();
        String perId = roleWeb.getPermissionId();
        String code = roleInfoEntity.getRoleCode();
        String name = roleInfoEntity.getRoleName();
        String desc = roleInfoEntity.getRoleDesc();
        if(StringTool.isnull(name)||StringTool.isnull(code)) {
            return  new Result(Constant.NULL_PARAM);
        }
        try{
            QRoleInfoEntity qRoleInfoEntity = QRoleInfoEntity.roleInfoEntity;
            if(true) {
                queryFactory.update(qRoleInfoEntity)
                        .set(qRoleInfoEntity.roleName,name)
                        .set(qRoleInfoEntity.roleDesc,desc)
                        .where(qRoleInfoEntity.roleId.eq(roleInfoEntity.getRoleId()))
                        .execute();
                RolePerRelEntity rolePerRelEntity = new RolePerRelEntity();
                rolePerRelEntity.setRoleId(roleInfoEntity.getRoleId());
                rolePerRelEntity.setPermissionId(perId);
                this.roleBindPer(rolePerRelEntity);
                return new Result(Constant.OK);
            }else return new Result(Constant.FAIL_STATUE,"角色编码重复");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("selectRole")
    @ApiOperation(value = "查询角色",httpMethod = "POST")
    public Result selectRole(@RequestBody RoleWeb roleWeb){
        RoleInfoEntity roleInfoEntity = roleWeb.getRoleInfoEntity();
        PageInfo pageInfo = roleWeb.getPageInfo();
        String code = roleInfoEntity.getRoleCode();
        String name = roleInfoEntity.getRoleName();
        try {
            QRoleInfoEntity qRoleInfoEntity = QRoleInfoEntity.roleInfoEntity;
            Predicate predicate = qRoleInfoEntity.roleCode.like("%"+(StringTool.isnull(code)?"%":code)+"%")
                    .and(qRoleInfoEntity.roleName.like("%"+(StringTool.isnull(name)?"%":name)+"%"));
            Sort sort = new Sort(Sort.Direction.ASC,"createTime");
            if(pageInfo.vaidate()) {
                PageRequest request = PageRequest.of(pageInfo.getPageNum(),pageInfo.getPageSize(),sort);
                Page<RoleInfoEntity> page = this.roleRepository.findAll(predicate,request);
                pageInfo.setTotalPage(page.getTotalPages());
                pageInfo.setTotalInfo(page.getTotalElements());
                return new Result(Constant.SUCCESS_STATUE,"查询成功",page,pageInfo);
            }else return new Result(Constant.SUCCESS_STATUE,"查询成功",this.roleRepository.findAll(predicate,sort));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }


    @Transactional
    @RequestMapping("roleBindPer")
    @ApiOperation(value = "角色绑定许可",httpMethod = "POST")
    public Result roleBindPer(@RequestBody RolePerRelEntity rolePerRelEntity){
        String roleId = rolePerRelEntity.getRoleId();
        String perIds = rolePerRelEntity.getPermissionId();
        QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
        if(StringTool.isnull(roleId)) {
            return  new Result(Constant.NULL_PARAM);
        }
        try{
            queryFactory.delete(qRolePerRelEntity)
                    .where(qRolePerRelEntity.roleId.eq(roleId)).execute();
            if(StringTool.isnull(perIds)) {
                return new Result(Constant.OK);
            }else {
                List<String> perId = Arrays.asList(perIds.split(","));
                String sql = "insert into role_per_rel (permission_id, role_id) values";
                StringBuilder sb = new StringBuilder(sql);
                if(!ListUtill.isnull(perId)){
                    perId.forEach(m ->{
                        if(!StringTool.isnull(m)) {
                            sb.append("('"+m+"','"+roleId+"'),");
                        }
                    });
                    String result = sb.substring(0,sb.length()-1);
                    System.out.print(">>>"+result+"<<<");
                    jdbcTemplate.batchUpdate(result);
                }
                return new Result(Constant.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }


}
