package com.ins.sys.library.web;

import com.ins.sys.library.domain.LibraryInfoEntity;
import com.ins.sys.library.domain.LibraryRepository;
import com.ins.sys.library.domain.QLibraryInfoEntity;
import com.ins.sys.tools.*;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/library")
@Api(value = "数据字典管理",tags = "数据字典管理",protocols = "1.0-Beta")
public class LibraryController{

    @Autowired
    private LibraryRepository libraryRepository;

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @ApiOperation(value = "新增数据字典",httpMethod = "POST")
    @PostMapping("/insertLibrary")
    public Result insertLibrary(@RequestBody LibraryInfoEntity libraryInfoEntity) {
        if(!libraryInfoEntity.validate()){
            return new Result(Constant.NULL_PARAM);
        }
        QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
        try {
            libraryInfoEntity.setLibraryId(MD5.encrypt(libraryInfoEntity.getLibraryCode()));
            JPAQuery query = queryFactory.select(qLibraryInfoEntity)
                    .from(qLibraryInfoEntity)
                    .where(qLibraryInfoEntity.libraryName.eq(libraryInfoEntity.getLibraryName()).or(qLibraryInfoEntity.libraryCode.eq(libraryInfoEntity.getLibraryCode())));
            if(!ListUtill.isnull(query.fetch())) {
                return new Result(Constant.FAIL_STATUE,"名称或者编码重复");
            }
            long time = new Date().getTime();
            libraryInfoEntity.setCreateTime(new Date().getTime());
            LibraryInfoEntity entity = this.libraryRepository.save(libraryInfoEntity);
            return new Result(Constant.SUCCESS_STATUE,"新增成功",entity);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("/deleteLibrary")
    @ApiOperation(value = "删除数据字典",httpMethod = "POST")
    public Result deleteLibrary(@RequestBody LibraryInfoEntity libraryInfoEntity) {
        String libraryCode = libraryInfoEntity.getLibraryCode();
        if(StringTool.isnull(libraryCode)) {
            return new Result(Constant.NULL_PARAM);
        }
        try {
            QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
            queryFactory.delete(qLibraryInfoEntity).where(qLibraryInfoEntity.libraryCode.eq(libraryCode)).execute();
            return new Result(Constant.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("/modifyLibrary")
    @ApiOperation(value = "修改数据字典",httpMethod = "POST")
    public Result modifyLibrary(@RequestBody LibraryInfoEntity libraryInfoEntity) {
        String libraryId = libraryInfoEntity.getLibraryId();
        String libraryCode = libraryInfoEntity.getLibraryCode();
        if(StringTool.isnull(libraryCode)) {
            return new Result(Constant.NULL_PARAM);
        }
        QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
        try {
            JPAQuery query = queryFactory.select(qLibraryInfoEntity)
                    .from(qLibraryInfoEntity)
                    .where(qLibraryInfoEntity.libraryName.eq(libraryInfoEntity.getLibraryName()));
            if(!ListUtill.isnull(query.fetch())) {
                return new Result(Constant.FAIL_STATUE,"名称重复");
            }
            queryFactory.update(qLibraryInfoEntity)
                    .set(qLibraryInfoEntity.libraryName,libraryInfoEntity.getLibraryName())
                    .where(qLibraryInfoEntity.libraryCode.eq(libraryCode))
                    .execute();
            return new Result(Constant.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("/findByParentCode")
    @ApiOperation(value = "根据编码查询数据字典",httpMethod = "POST")
    public Result findByParentCode(@RequestBody LibraryInfoEntity libraryInfoEntity) {
        String parentCode = libraryInfoEntity.getParentCode();
        if(StringTool.isnull(parentCode)) {
            parentCode = "0";
        }
        QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
        try {
                JPAQuery<LibraryInfoEntity> jpaQuery = queryFactory.select(qLibraryInfoEntity)
                        .from(qLibraryInfoEntity)
                        .where(qLibraryInfoEntity.parentCode.eq(parentCode))
                        .orderBy(qLibraryInfoEntity.libraryName.desc());
            return new Result(Constant.SUCCESS_STATUE,"查询成功",jpaQuery.fetch());
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("/findAll")
    @ApiOperation(value = "查询全部数据字典",httpMethod = "POST")
    public Result findAll() {
        QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
        try {
            Sort sort = new Sort(Sort.Direction.DESC,"createTime");
            return new Result(Constant.SUCCESS_STATUE,"查询成功",this.libraryRepository.findAll(sort));
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("/getLibraryMap")
    @ApiOperation(value = "查询数据字典（共子系统使用）",httpMethod = "POST")
    public Result getLibraryMap(@RequestBody LibraryInfoEntity libraryInfoEntity) {
        QLibraryInfoEntity qLibraryInfoEntity = QLibraryInfoEntity.libraryInfoEntity;
        String parentCode = libraryInfoEntity.getParentCode();
        if(StringTool.isnull(parentCode)) {
            parentCode = "0";
        }
        try {
            JPAQuery query = queryFactory.select(qLibraryInfoEntity)
                    .from(qLibraryInfoEntity)
                    .where(qLibraryInfoEntity.parentCode.eq(parentCode));
            List<LibraryInfoEntity> libraryInfoEntityList = query.fetch();
            JSONObject object = new JSONObject();
            if(!ListUtill.isnull(libraryInfoEntityList)) {
                libraryInfoEntityList.forEach(item -> {
                    object.put(item.getLibraryCode(),item.getLibraryName());
                });
            }
            return new Result(Constant.SUCCESS_STATUE,"查询成功",object);
        }
        catch (Exception e){
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }
}
