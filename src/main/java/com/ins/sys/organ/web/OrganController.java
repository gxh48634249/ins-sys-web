package com.ins.sys.organ.web;


import com.ins.sys.organ.domain.OrganInfoEntity;
import com.ins.sys.organ.domain.OrganRepository;
import com.ins.sys.organ.domain.QOrganInfoEntity;
import com.ins.sys.tools.*;
import com.ins.sys.user.domain.QSysUserInfoEntity;
import com.ins.sys.user.domain.QUserOrganRelEntity;
import com.ins.sys.user.domain.SysUserInfoEntity;
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
@RequestMapping("organ")
@Api(value = "组织机构管理",tags = "组织机构管理")
public class OrganController {

    @Autowired
    private OrganRepository organRepository;

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }


    @RequestMapping("insertOrgan")
    @ApiOperation(value = "新增组织机构",httpMethod = "POST")
    public Result insertOrgan(@RequestBody OrganInfoEntity organInfoEntity) {
        if (!organInfoEntity.validate()){
            return new Result(Constant.NULL_PARAM);
        }
        QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
        try {
            String name = organInfoEntity.getOrganName();
            String code = organInfoEntity.getOrganCode();
            JPAQuery query = queryFactory.select(qOrganInfoEntity)
                    .from(qOrganInfoEntity)
                    .where(qOrganInfoEntity.organName.eq(name).or(qOrganInfoEntity.organCode.eq(code)));
            if (ListUtill.isnull(query.fetch())) {
                organInfoEntity.setOrganId(MD5.encrypt(code));
                organInfoEntity.setCreateTime(new Date().getTime());
                return new Result(Constant.SUCCESS_STATUE,"新增成功",this.organRepository.save(organInfoEntity));
            }else {
                return new Result(Constant.FAIL_STATUE,"组织名称或者组织编码重复");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("deleteOrgan")
    @ApiOperation(value = "根据主键删除组织机构",httpMethod = "POST")
    public Result deleteOrgan(@RequestBody OrganInfoEntity entity) {
        String organId = entity.getOrganId();
        if(StringTool.isnull(organId)){
            return new Result(Constant.NULL_PARAM);
        }
        try{
            OrganInfoEntity organInfoEntity = new OrganInfoEntity();
            organInfoEntity.setOrganId(organId);
            this.organRepository.delete(organInfoEntity);
            QUserOrganRelEntity qUserOrganRelEntity = QUserOrganRelEntity.userOrganRelEntity;
            queryFactory.delete(qUserOrganRelEntity)
                    .where(qUserOrganRelEntity.organId.eq(organId)).execute();
            return new Result(Constant.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }

    }

    @Transactional
    @RequestMapping("modifyOrgan")
    @ApiOperation(value = "修改组织机构",httpMethod = "POST")
    public Result modifyOrgan(@RequestBody OrganInfoEntity organInfoEntity) {
        String id = organInfoEntity.getOrganId();
        String name = organInfoEntity.getOrganName();
        String code = organInfoEntity.getOrganCode();
        if(StringTool.isnull(id)) {
            return new Result(Constant.NULL_PARAM);
        }
        QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
        try{
            JPAQuery<OrganInfoEntity> query = queryFactory.select(qOrganInfoEntity)
                    .from(qOrganInfoEntity)
                    .where(qOrganInfoEntity.organName.eq(name));
            if(ListUtill.isnull(query.fetch())){
                queryFactory.update(qOrganInfoEntity)
                        .set(qOrganInfoEntity.organName,organInfoEntity.getOrganName())
                        .set(qOrganInfoEntity.organCode,organInfoEntity.getOrganCode())
                        .set(qOrganInfoEntity.organId,organInfoEntity.getOrganId())
                        .set(qOrganInfoEntity.organDesc,organInfoEntity.getOrganDesc())
                        .where(qOrganInfoEntity.organId.eq(id)).execute();
                return new Result(Constant.OK);
            }else {
                return new Result(Constant.FAIL_STATUE,"机构名称重复");
            }
//            if(StringTool.isnull(code)) {
//            }else {
//                JPAQuery<OrganInfoEntity> query = queryFactory.select(qOrganInfoEntity)
//                        .from(qOrganInfoEntity)
//                        .where(qOrganInfoEntity.organName.eq(name).or(qOrganInfoEntity.organCode.eq(code)));
//                if(ListUtill.isnull(query.fetch())) {
//                    organInfoEntity.setOrganId(MD5.encrypt(code));
//                    queryFactory.update(qOrganInfoEntity)
//                            .set(qOrganInfoEntity.organName,organInfoEntity.getOrganName())
//                            .set(qOrganInfoEntity.organCode,organInfoEntity.getOrganCode())
//                            .set(qOrganInfoEntity.organId,organInfoEntity.getOrganId())
//                            .set(qOrganInfoEntity.organDesc,organInfoEntity.getOrganDesc())
//                            .where(qOrganInfoEntity.organId.eq(id)).execute();
//                    return new Result(Constant.OK);
//                }else {
//                    return new Result(Constant.FAIL_STATUE,"机构名称或者编码重复");
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("selectOrgan")
    @ApiOperation(value = "查询机构",httpMethod = "POST")
    public Result selectOrgan(OrganInfoEntity organInfoEntity,PageInfo pageInfo) {
        try {
            QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
            Predicate predicate = qOrganInfoEntity.organName.like("%"+(StringTool.isnull(organInfoEntity.getOrganName())?"%":organInfoEntity.getOrganName())+"%")
                    .and(qOrganInfoEntity.organCode.like("%"+(StringTool.isnull(organInfoEntity.getOrganCode())?"%":organInfoEntity.getOrganCode())+"%"))
                    .and(qOrganInfoEntity.organDesc.like("%"+(StringTool.isnull(organInfoEntity.getOrganDesc())?"%":organInfoEntity.getOrganDesc())+"%"));
            Sort sort = new Sort(Sort.Direction.DESC,"createTime");
            if(pageInfo.vaidate()) {
                PageRequest request = PageRequest.of(pageInfo.getPageNum(),pageInfo.getPageSize(),sort);
                Page<OrganInfoEntity> page = this.organRepository.findAll(predicate,request);
                pageInfo.setTotalInfo(page.getTotalElements());
                pageInfo.setTotalPage(page.getTotalPages());
                return new Result(Constant.SUCCESS_STATUE,"查询成功",page,pageInfo);
            }else {
                return new Result(Constant.SUCCESS_STATUE,"查询成功",this.organRepository.findAll(predicate,sort));
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("selectUser")
    @ApiOperation(value = "查询机构下用户",httpMethod = "POST")
    public Result selectOrganUser(@RequestBody OrganUserWebEntity organUserWebEntity) {
        String organId = organUserWebEntity.getOrganId();
        String userName = organUserWebEntity.getUserName();
        if(StringTool.isnull(organId)) {
            return new Result(Constant.NULL_PARAM);
        }
        try {
            QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            QUserOrganRelEntity qUserOrganRelEntity = QUserOrganRelEntity.userOrganRelEntity;
            JPAQuery<SysUserInfoEntity> query = queryFactory.select(qSysUserInfoEntity)
                    .from(qSysUserInfoEntity)
                    .leftJoin(qUserOrganRelEntity).on(qSysUserInfoEntity.userId.eq(qUserOrganRelEntity.userId))
                    .leftJoin(qOrganInfoEntity).on(qOrganInfoEntity.organId.eq(qUserOrganRelEntity.organId))
                    .where(qOrganInfoEntity.organId.eq(organId));
            if(!StringTool.isnull(userName)) {
                query.where(qSysUserInfoEntity.userName.like(StringTool.sqlLike(userName)));
            }
            query.orderBy(qSysUserInfoEntity.userName.desc());
            query.limit(organUserWebEntity.getPageInfo().getPageSize()).offset(organUserWebEntity.getPageInfo().getPageNum()*organUserWebEntity.getPageInfo().getPageSize());
            PageInfo pageInfo = organUserWebEntity.getPageInfo();
            pageInfo.setTotalInfo(query.fetchCount());
            return new Result(Constant.SUCCESS_STATUE,"查询成功",query.fetch(),pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("selectAllOrgan")
    @ApiOperation(value = "查询全部机构",httpMethod = "POST")
    public Result selectAllOrgan() {
        try {
            Sort sort = new Sort(Sort.Direction.ASC,"createTime");
            return new Result(Constant.SUCCESS_STATUE,"查询成功",this.organRepository.findAll(sort));
        }catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }
}