package com.ins.sys.collection.web;

import com.ins.sys.collection.domain.CollectionInfo;
import com.ins.sys.collection.service.CollectionService;
import com.ins.sys.library.domain.LibraryInfoEntity;
import com.ins.sys.subject.domain.SubjectInfo;
import com.ins.sys.subject.service.SubjectService;
import com.ins.sys.tools.Constant;
import com.ins.sys.tools.PageInfo;
import com.ins.sys.tools.Result;
import com.ins.sys.tools.StringTool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RequestMapping("collection")
@RestController
@Api(value = "收藏管理", tags = "收藏管理")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @RequestMapping("save")
    @ApiOperation(value = "新增收藏", httpMethod = "POST")
    public Result save(CollectionInfo collectionInfo){
        try {
            return new Result(Constant.SUCCESS_STATUE,"新增成功",this.collectionService.save(collectionInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }


    @Transactional
    @RequestMapping("delete")
    @ApiOperation(value = "删除收藏" ,httpMethod = "POST")
    public Result delete(String usrsId,String articleId) {

        try {
            if(StringTool.isnull(usrsId)) {
                return new Result(Constant.NULL_PARAM);
            }
            CollectionInfo collectionInfo = new CollectionInfo();
            collectionInfo.setArticleId(articleId);
            collectionInfo.setUsrsId(usrsId);
            this.collectionService.remove(collectionInfo);
            return new Result(Constant.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }


    @RequestMapping("selectByUsrsId")
    @ApiOperation(value = "查询收藏" ,httpMethod = "POST")
    public Result select(String UsrsId) {
        return new Result(Constant.SUCCESS_STATUE, "查询成功", this.collectionService.findbyUsrsId(UsrsId));
    }




}
