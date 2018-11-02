package com.ins.sys.resource.web;

import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.permission.domain.PermissionRepository;
import com.ins.sys.permission.domain.QPermissionInfoEntity;
import com.ins.sys.resource.domain.QResourceInfoEntity;
import com.ins.sys.resource.domain.ResourceInfoEntity;
import com.ins.sys.resource.domain.ResourceRepository;
import com.ins.sys.role.domain.QRolePerRelEntity;
import com.ins.sys.tools.*;
import com.ins.sys.user.domain.QUserPermissionRelEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@RestController
@RequestMapping("resource")
@Api(tags = "资源管理")
public class ResourceController {


    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    @RequestMapping("insertResource")
    @ApiOperation(value = "新增资源",httpMethod = "POST")
    public Result insertResource(@RequestBody ResourceInfoEntity resourceInfoEntity) {
        try {
            String resourceCode = resourceInfoEntity.getResourceCode();
            if(StringTool.isnull(resourceCode)) {
                return new Result(Constant.NULL_PARAM);
            }
            QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
            JPAQuery<ResourceInfoEntity> query = queryFactory.select(qResourceInfoEntity)
                    .from(qResourceInfoEntity)
                    .where(qResourceInfoEntity.resourceId.eq(resourceCode));
            if(!ListUtill.isnull(query.fetch())) {
                return new Result(Constant.FAIL_STATUE,"资源编码重复");
            }
            PermissionInfoEntity permissionInfoEntity = new PermissionInfoEntity();
            permissionInfoEntity.setCreateTime(new Date().getTime());
            permissionInfoEntity.setPermissionId(MD5.encrypt(resourceCode));
            permissionInfoEntity.setPermissionCode(resourceCode.toUpperCase());
            permissionInfoEntity.setPermissionDesc("新增资源时由系统自动创建");
            permissionInfoEntity.setPermissionName("资源"+"["+resourceCode.toUpperCase()+"]"+"许可");
            permissionInfoEntity.setResourceId(resourceCode);
            this.permissionRepository.save(permissionInfoEntity);
            resourceInfoEntity.setResourceId(resourceCode);
            resourceInfoEntity.setCreateTime(new Date().getTime());
            return new Result(Constant.SUCCESS_STATUE,"新增成功",this.resourceRepository.save(resourceInfoEntity));
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("deleteResource")
    @ApiOperation(value = "删除资源",httpMethod = "POST")
    public Result deleteResource(@RequestBody ResourceInfoEntity resourceInfoEntity) {
        String resourceId = resourceInfoEntity.getResourceId();
        try {
            if(StringTool.isnull(resourceId)) {
                return new Result(Constant.NULL_PARAM);
            }
            QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
            QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
            QUserPermissionRelEntity qUserPermissionRelEntity = QUserPermissionRelEntity.userPermissionRelEntity;
            QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
            queryFactory.delete(qResourceInfoEntity)
                    .where(qResourceInfoEntity.resourceId.eq(resourceId)).execute();
            queryFactory.delete(qPermissionInfoEntity)
                    .where(qPermissionInfoEntity.resourceId.eq(resourceId)).execute();
            queryFactory.delete(qRolePerRelEntity)
                    .where(qRolePerRelEntity.permissionId.eq(MD5.encrypt(resourceId))).execute();
            queryFactory.delete(qUserPermissionRelEntity)
                    .where(qUserPermissionRelEntity.permissionId.eq(MD5.encrypt(resourceId))).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("modifyResource")
    @ApiOperation(value = "修改资源",httpMethod = "POST")
    public Result modifyResource(@RequestBody ResourceInfoEntity resourceInfoEntity) {
        try {
            String resourceCode = resourceInfoEntity.getResourceCode();
            if(StringTool.isnull(resourceCode)) {
                return new Result(Constant.NULL_PARAM);
            }
            String name = resourceInfoEntity.getResourceName();
            String des = resourceInfoEntity.getResourceDes();
            QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
            JPAUpdateClause clause = queryFactory.update(qResourceInfoEntity);
            if(!StringTool.isnull(name)){
                clause = clause.set(qResourceInfoEntity.resourceName,name);
            }if(!StringTool.isnull(des)){
                clause = clause.set(qResourceInfoEntity.resourceDes,des);
            }
            clause.where(qResourceInfoEntity.resourceCode.eq(resourceCode)).execute();
            return new Result(Constant.SUCCESS_STATUE,"修改成功",resourceInfoEntity);
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }


    @Transactional
    @RequestMapping("selectResource")
    @ApiOperation(value = "查询资源",httpMethod = "POST")
    public Result selectResource(@RequestBody ResourceInfoEntity resourceInfoEntity, Long startTime, Long endTime, PageInfo pageInfo) {
        String resourceId = resourceInfoEntity.getResourceId();
        String resourceName = resourceInfoEntity.getResourceName();
        String resourceCode = resourceInfoEntity.getResourceCode();
        String resourceDes = resourceInfoEntity.getResourceDes();
        try {
            QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
            Predicate predicate = qResourceInfoEntity.resourceId.isNotNull();
            if(!StringTool.isnull(resourceId)){
                predicate = ExpressionUtils.and(predicate,qResourceInfoEntity.resourceId.eq(resourceId));
            }if(!StringTool.isnull(resourceName)){
                predicate = ExpressionUtils.and(predicate,qResourceInfoEntity.resourceName.like(resourceName));
            }if(!StringTool.isnull(resourceCode)){
                predicate = ExpressionUtils.and(predicate,qResourceInfoEntity.resourceCode.like(resourceCode));
            }if(!StringTool.isnull(resourceDes)){
                predicate = ExpressionUtils.and(predicate,qResourceInfoEntity.resourceDes.like(resourceDes));
            }if(null!=startTime&&null!=endTime&&endTime>startTime){
                predicate = ExpressionUtils.and(predicate,qResourceInfoEntity.createTime.between(startTime,endTime));
            }
            Sort sort = new Sort(Sort.Direction.DESC,"createTime");
            if(pageInfo.vaidate()) {
                PageRequest request = pageInfo.getPageRequest(sort);
                Page<ResourceInfoEntity> page = this.resourceRepository.findAll(predicate,request);
                return new Result(Constant.SUCCESS_STATUE,"查询成功",page.getContent(),pageInfo.getResult(page));
            }
            return new Result(Constant.SUCCESS_STATUE,"查询成功",this.resourceRepository.findAll(sort));
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Autowired
    private WebApplicationContext applicationContext;

    @RequestMapping("selectAllSysResource")
    @ApiOperation(value = "查询系统全部资源",httpMethod = "POST")
    public List<String> selectAllSysResource(){
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo,HandlerMethod> map = mapping.getHandlerMethods();
        List<String> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()){
            //获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            for (String url : patterns){
                urlList.add(url);
            }
        }
        return urlList;
    }


    @Autowired
    private EhcacheService ehcacheService;

    @RequestMapping("reloadResourcePer")
    @ApiOperation(value = "刷新系统权限信息",httpMethod = "POST")
    public void reloadResourcePer(){
        ehcacheService.reLoadCache();
    }

    @RequestMapping("removeResourcePer")
    @ApiOperation(value = "清空系统权限信息",httpMethod = "POST")
    public void removeResourcePer(){
        ehcacheService.clean();
    }



}
