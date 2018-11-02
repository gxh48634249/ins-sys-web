package com.ins.sys.permission.web;


import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.permission.domain.PermissionRepository;
import com.ins.sys.permission.domain.QPermissionInfoEntity;
import com.ins.sys.role.domain.QRolePerRelEntity;
import com.ins.sys.tools.*;
import com.ins.sys.user.domain.QUserPermissionRelEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;

@RestController
@RequestMapping("permission")
@Api(tags = "许可管理")
public class PermissionController{

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Autowired
    private PermissionRepository permissionRepository;

    @ApiOperation(value = "新增许可",httpMethod = "POST")
    @RequestMapping("insertPer")
    public Result insertPer(PermissionInfoEntity permissionInfoEntity){
        if(null==permissionInfoEntity||StringTool.isnull(permissionInfoEntity.getPermissionCode())||
                StringTool.isnull(permissionInfoEntity.getPermissionName())){
            return new Result(Constant.NULL_PARAM);
        }
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        try {
            JPAQuery query = queryFactory.select(qPermissionInfoEntity)
                    .from(qPermissionInfoEntity)
                    .where(qPermissionInfoEntity.permissionCode.eq(permissionInfoEntity.getPermissionCode())
                    .and(qPermissionInfoEntity.permissionName.eq(permissionInfoEntity.getPermissionName())));
            if(ListUtill.isnull(query.fetch())){
                permissionInfoEntity.setCreateTime(new Date().getTime());
                permissionInfoEntity.setPermissionId(MD5.encrypt(permissionInfoEntity.getPermissionCode()));
                return new Result(Constant.SUCCESS_STATUE,"新增成功",this.permissionRepository.save(permissionInfoEntity));
            }else {
                return new Result(Constant.FAIL_STATUE,"许可编码或者名称重复");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @ApiOperation(value = "删除许可",httpMethod = "POST")
    @RequestMapping("deletePer")
    public Result deletePer(String permissionId){
        if(StringTool.isnull(permissionId)) {
            return  new Result(Constant.NULL_PARAM);
        }
        try{
            PermissionInfoEntity permissionInfoEntity = new PermissionInfoEntity();
            permissionInfoEntity.setPermissionId(permissionId);
            this.permissionRepository.delete(permissionInfoEntity);
            QUserPermissionRelEntity qUserPermissionRelEntity = QUserPermissionRelEntity.userPermissionRelEntity;
            QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
            queryFactory.delete(qRolePerRelEntity)
                    .where(qRolePerRelEntity.permissionId.eq(permissionId)).execute();
            queryFactory.delete(qUserPermissionRelEntity)
                    .where(qUserPermissionRelEntity.permissionId.eq(permissionId)).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @ApiOperation(value = "修改许可",httpMethod = "POST")
    @RequestMapping("modifyPer")
    public Result modifyPer(PermissionInfoEntity permissionInfoEntity) {
        String id = permissionInfoEntity.getPermissionId();
        String code = permissionInfoEntity.getPermissionCode();
        if(StringTool.isnull(id)) {
            return new Result(Constant.NULL_PARAM);
        }
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        try {
            boolean codeJ = StringTool.isnull(code);
            if(codeJ) {
                code = "%";
            }
            JPAQuery query = queryFactory.select(qPermissionInfoEntity)
                    .from(qPermissionInfoEntity)
                    .where(qPermissionInfoEntity.permissionName.eq(permissionInfoEntity.getPermissionName())
                            .and(qPermissionInfoEntity.permissionCode.eq(code)));
            if(!ListUtill.isnull(query.fetch())) {
                return new Result(Constant.FAIL_STATUE,"许可名称或者比编码重复");
            }
            if(codeJ) {
                queryFactory.update(qPermissionInfoEntity)
                        .set(qPermissionInfoEntity.permissionCode,permissionInfoEntity.getPermissionCode())
                        .set(qPermissionInfoEntity.permissionDesc,permissionInfoEntity.getPermissionDesc())
                        .set(qPermissionInfoEntity.permissionName,permissionInfoEntity.getPermissionName())
                        .where(qPermissionInfoEntity.permissionId.eq(permissionInfoEntity.getPermissionId()))
                        .execute();
            }else {
                queryFactory.update(qPermissionInfoEntity)
                        .set(qPermissionInfoEntity.permissionCode,permissionInfoEntity.getPermissionCode())
                        .set(qPermissionInfoEntity.permissionDesc,permissionInfoEntity.getPermissionDesc())
                        .set(qPermissionInfoEntity.permissionName,permissionInfoEntity.getPermissionName())
                        .set(qPermissionInfoEntity.permissionId,MD5.encrypt(permissionInfoEntity.getPermissionCode()))
                        .where(qPermissionInfoEntity.permissionId.eq(permissionInfoEntity.getPermissionId()))
                        .execute();
            }
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @ApiOperation(value = "查询许可",httpMethod = "POST")
    @RequestMapping("selectPer")
    public Result selectPer(@RequestBody PerWeb perWeb) {
        PermissionInfoEntity permissionInfoEntity = perWeb.getPermissionInfoEntity();
        PageInfo pageInfo = perWeb.getPageInfo();
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        try {
            Predicate predicate = qPermissionInfoEntity.permissionId.isNotNull();
            String name = permissionInfoEntity.getPermissionName();
            String code = permissionInfoEntity.getPermissionCode();
            String desc = permissionInfoEntity.getPermissionDesc();
            String resourceId = permissionInfoEntity.getResourceId();
            if(!StringTool.isnull(name)){
                predicate = ExpressionUtils.and(predicate,qPermissionInfoEntity.permissionName.like(StringTool.sqlLike(name)));
            }
            if(!StringTool.isnull(code)){
                predicate = ExpressionUtils.and(predicate,qPermissionInfoEntity.permissionCode.like(StringTool.sqlLike(code)));
            }
            if(!StringTool.isnull(desc)) {
                predicate = ExpressionUtils.and(predicate,qPermissionInfoEntity.permissionDesc.like(StringTool.sqlLike(desc)));
            }
            if(!StringTool.isnull(resourceId)) {
                predicate = ExpressionUtils.and(predicate,qPermissionInfoEntity.resourceId.eq(resourceId));
            }
            Sort sort = new Sort(Sort.Direction.DESC,"createTime");
            if(pageInfo.vaidate()) {
                PageRequest request = PageRequest.of(pageInfo.getPageNum(),pageInfo.getPageSize(),sort);
                Page<PermissionInfoEntity> page = this.permissionRepository.findAll(predicate,request);
                pageInfo.setTotalPage(page.getTotalPages());
                pageInfo.setTotalInfo(page.getTotalElements());
                return new Result(Constant.SUCCESS_STATUE,"查询成功",page,pageInfo);
            }else {
                return new Result(Constant.SUCCESS_STATUE,"查询成功",this.permissionRepository.findAll(predicate,sort));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @ApiOperation(value = "查询许可",httpMethod = "POST")
    @RequestMapping("selectPerByRole")
    public Result selectPerByRole(@RequestBody PerWeb perWeb) {
        String roleId = perWeb.getRoleId();
        if(StringTool.isnull(roleId)) {
            return new Result(Constant.NULL_PARAM);
        }
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
        try {
            JPAQuery<PermissionInfoEntity> query = queryFactory.select(qPermissionInfoEntity)
                    .from(qPermissionInfoEntity)
                    .leftJoin(qRolePerRelEntity).on(qPermissionInfoEntity.permissionId.eq(qRolePerRelEntity.permissionId))
                    .where(qRolePerRelEntity.roleId.eq(roleId));
            return new Result(Constant.SUCCESS_STATUE,"查询成功",query.fetch());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }
}
