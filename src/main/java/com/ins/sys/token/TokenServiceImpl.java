package com.ins.sys.token;

import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.tools.BasicService;
import com.ins.sys.tools.MD5;
import com.ins.sys.tools.PageInfo;
import com.ins.sys.tools.StringTool;
import com.ins.sys.user.domain.SysUserInfoEntity;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "tokenserviceimpl")
public class TokenServiceImpl extends BasicService<Token> implements TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private TokenRepository tokenRepository;

    @Transactional
    @Scheduled(fixedRate = 60000L)
    public void delete(){
        log.info("开始清理过期用户");
        Long date = new Date().getTime();
        Long last = date+30*60*1000;
        Long unuse = date+24*60*60*1000;
        QToken qToken = QToken.token;
        Long num = queryFactory.delete(qToken).where(qToken.lastTime.gt(last).or(qToken.createTime.gt(unuse))).execute();
        log.info("本次共清理:  "+num+"条过期数据");
    }

    @Override
    public Token save(Token token) throws Exception {
        token.setCreateTime(new Date().getTime());
        token.setId(MD5.id());
        token.setLastTime(new Date().getTime());
        return this.tokenRepository.save(token);
    }

    @Override
    public Long remove(Token token) throws Exception {
        QToken qToken = QToken.token;
        return queryFactory.delete(qToken).where(qToken.id.in(token.getId().split(","))).execute();
    }

    @Transactional
    @Override
    public Token modify(Token token) throws Exception {
        QToken qToken = QToken.token;
        queryFactory.update(qToken)
                .set(qToken.lastTime,new Date().getTime())
                .where(qToken.tokenInfo.eq(token.getTokenInfo())).execute();
        return null;
    }

    @Override
    public Page<Token> findByPage(Token token, PageInfo pageInfo) throws Exception {
        QToken qToken = QToken.token;
        Predicate predicate = qToken.id.isNotNull();
        if(null==pageInfo) {
            pageInfo = new PageInfo();
            pageInfo.setPageSize(0);
            pageInfo.setPageNum(10);
        }
        if(!StringTool.isnull(token.getTokenUser())) {
            predicate = ExpressionUtils.and(predicate,qToken.tokenUser.like(StringTool.sqlLike(token.getTokenUser())));
        }
        Sort sort = new Sort(Sort.Direction.DESC,"create_time");
        PageRequest request = pageInfo.getPageRequest(sort);
        return tokenRepository.findAll(predicate,request);
    }

    @Override
    public List<Token> findAll() throws Exception {
        return null;
    }

    @Override
    public Token findById(String id) throws Exception {
        QToken qToken = QToken.token;
        return queryFactory.select(qToken).from(qToken).where(qToken.id.eq(id)).fetchFirst();
    }

    @Override
    public Long getTotal() throws Exception {
        QToken qToken = QToken.token;
        return queryFactory.select(qToken).from(qToken).where(qToken.id.isNotNull()).fetchCount();
    }

    @Override
    public SysUserInfoEntity findUserByToken(String token) throws Exception {
        QToken qToken = QToken.token;
        Map<String ,Class> map = new HashMap<>();
        map.put("per",PermissionInfoEntity.class);
        System.out.println(token.substring(4).length());
        return (SysUserInfoEntity) JSONObject.toBean(JSONObject.fromObject(queryFactory.select(qToken).from(qToken)
                .where(qToken.tokenInfo.eq(token.substring(4))).fetchFirst().getTokenUser()),SysUserInfoEntity.class,map);
    }
}
