package com.ins.sys.zh;

import com.ins.sys.tools.BasicController;
import com.ins.sys.tools.Constant;
import com.ins.sys.tools.Result;
import com.ins.sys.tools.StringTool;
import com.querydsl.jpa.impl.JPAQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zh")
@Api(value = "土地决策平台", tags = "土地决策平台")
public class ZHController extends BasicController<ZH> {

    @Autowired
    private ZHRepository zhRepository;

    @Autowired
    private ZPRepository zpRepository;

    @RequestMapping("findBuildingInfo")
    @ApiOperation(value = "查询楼盘信息", httpMethod = "POST")
    public Result findBuildingInfo(String address) {
        QZH qzh = QZH.zH;
        JPAQuery<ZH> query = queryFactory.select(qzh).from(qzh)
                .where(qzh.lpName.like(StringTool.sqlLike(address)));
        return new Result(Constant.SUCCESS_STATUE,"查询成功",query.fetch());
    }

    @RequestMapping("findLandInfo")
    @ApiOperation(value = "查询土地招拍挂信息", httpMethod = "POST")
    public Result findLandInfo(String shiming) {
        QZP qzp = QZP.zP;
        return new Result(Constant.SUCCESS_STATUE,"查询成功",queryFactory.select(qzp).from(qzp).where(qzp.shiming.like(StringTool.sqlLike(shiming))).fetch());
    }

    @RequestMapping("findAreaInfo")
    @ApiOperation(value = "区域宏观信息查询", httpMethod = "POST")
    public Result findAreaInfo(String info) {
        QZk qZk = QZk.zk;
        JPAQuery<Zk> query = queryFactory.select(qZk).from(qZk)
                .where(qZk.id.like(StringTool.sqlLike(info)));
        return new Result(Constant.SUCCESS_STATUE,"查询成功",query.fetch());
    }
}
