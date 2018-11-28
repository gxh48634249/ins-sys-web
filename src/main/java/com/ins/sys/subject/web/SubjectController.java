package com.ins.sys.subject.web;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("subject")
@RestController
@Api(value = "专题管理", tags = "专题管理")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("save")
    @ApiOperation(value = "新增专题", httpMethod = "POST")
    public Result save(SubjectInfo subjectInfo){
        try {
            return new Result(Constant.SUCCESS_STATUE,"新增成功",this.subjectService.save(subjectInfo));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("delete")
    @ApiOperation(value = "删除专题" ,httpMethod = "POST")
    public Result delete(String id) {
        try {
            if(StringTool.isnull(id)) {
                return new Result(Constant.NULL_PARAM);
            }
            SubjectInfo subjectInfo = new SubjectInfo();
            subjectInfo.setId(id);
            this.subjectService.remove(subjectInfo);
            return new Result(Constant.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @Transactional
    @RequestMapping("modify")
    @ApiOperation(value = "修改专题" ,httpMethod = "POST")
    public Result modify(SubjectInfo subjectInfo) {
        try {
            if(StringTool.isnull(subjectInfo.getId())) {
                return new Result(Constant.NULL_PARAM);
            }
            this.subjectService.modify(subjectInfo);
            return new Result(Constant.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("select")
    @ApiOperation(value = "查询专题" ,httpMethod = "POST")
    public Result select(SubjectInfo subjectInfo, PageInfo pageInfo) {
        try {
            Page<SubjectInfo> subjectInfoPage = this.subjectService.findByPage(subjectInfo==null?new SubjectInfo():subjectInfo,pageInfo);
            return new Result(Constant.SUCCESS_STATUE, "查询成功", subjectInfoPage.getContent(),pageInfo.getResult(subjectInfoPage));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

    @RequestMapping("selectById")
    @ApiOperation(value = "查询专题详情" ,httpMethod = "POST")
    public Result selectById(String id) {
        try {
            return new Result(Constant.SUCCESS_STATUE, "查询成功", this.subjectService.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(Constant.SERVICE_ERROR);
        }
    }

}
