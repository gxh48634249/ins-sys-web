package com.ins.sys.user.service;


import com.ins.sys.tools.*;
import com.ins.sys.user.domain.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.models.auth.In;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserOrganRepository userOrganRepository;

    @Autowired
    private UserPerRepository userPerRepository;

    @Autowired
    private UseRoleRepository useRoleRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Result insert(SysUserInfoEntity userInfoEntity) throws Exception {
        String account = userInfoEntity.getUserAccount();
        String pwd = userInfoEntity.getUserPwd();
        Long phone = userInfoEntity.getPhone();
        String mail = userInfoEntity.getMail();
        String weChat = userInfoEntity.getWechat();
        String idCard = userInfoEntity.getIdCard();
        String address = userInfoEntity.getAddress();
        String statue = userInfoEntity.getUserStatue();
        if(StringTool.isnull(statue)) {
            userInfoEntity.setUserStatue("UserStatue_1");
        }
        if(StringTool.isnull(account)||StringTool.isnull(pwd)||null==phone||phone.toString().length()<8) {
            return new Result(Constant.NULL_PARAM);
        }
        QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
        JPAQuery query = queryFactory.select(qSysUserInfoEntity)
                .from(qSysUserInfoEntity)
                .where(qSysUserInfoEntity.phone.eq(phone)
                        .and(qSysUserInfoEntity.userAccount.eq(account))
                        .and(qSysUserInfoEntity.mail.eq(StringTool.isnull(mail)?"%":mail))
                        .and(qSysUserInfoEntity.wechat.eq(StringTool.isnull(weChat)?"%":weChat))
                        .and(qSysUserInfoEntity.idCard.eq(StringTool.isnull(idCard)?"%":idCard)));
        try{
            if(ListUtill.isnull(query.fetch())) {
                String id = MD5.encrypt(account);
                userInfoEntity.setCreateTime(new Date().getTime());
                userInfoEntity.setUserId(id);
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(8);
                userInfoEntity.setUserPwd(encoder.encode(pwd));
                userInfoEntity.initParams();
                if(null==userInfoEntity.getGender()){
                    userInfoEntity.setGender("gender_1");
                }
                return new Result(Constant.SUCCESS_STATUE,"",this.userRepository.save(userInfoEntity));
            }else return new Result(Constant.FAIL_STATUE,"账户，邮箱，身份证号，微信，手机号已存在");
        }catch (Exception e){
            e.printStackTrace();
            return  new Result(Constant.SERVICE_ERROR);
        }
    }

    @Override
    public Result insertUserRole(String userId, String roleIds) throws Exception {
        QUserRoleRelEntity qUserRoleRelEntity = QUserRoleRelEntity.userRoleRelEntity;
        queryFactory.delete(qUserRoleRelEntity)
                .where(qUserRoleRelEntity.userId.eq(userId)).execute();
        StringBuilder sb = new StringBuilder("insert into user_role_rel(user_id,role_id) values ");
        if(!StringTool.isnull(roleIds)) {
            String sql = ListUtill.append(sb,roleIds,userId);
            jdbcTemplate.batchUpdate(sql);
        }
        return new Result(Constant.OK);
    }

    @Override
    public Result insertUserPer(String userId, String permissionIds) throws Exception {
        QUserPermissionRelEntity qUserPermissionRelEntity = QUserPermissionRelEntity.userPermissionRelEntity;
        queryFactory.delete(qUserPermissionRelEntity)
                .where(qUserPermissionRelEntity.userId.eq(userId)).execute();
        StringBuilder sb = new StringBuilder("insert into user_permission_rel(user_id,permission_id) values ");
        if(!StringTool.isnull(permissionIds)) {
            jdbcTemplate.batchUpdate(ListUtill.append(sb,permissionIds,userId));
        }
        return new Result(Constant.OK);
    }

    @Override
    public Result insertUserOrgan(String userId, String organId) throws Exception {
        QUserOrganRelEntity qUserOrganRelEntity = QUserOrganRelEntity.userOrganRelEntity;
        queryFactory.delete(qUserOrganRelEntity)
                .where(qUserOrganRelEntity.userId.eq(userId)).execute();
        if(!StringTool.isnull(organId)) {
            UserOrganRelEntity userOrganRelEntity = new UserOrganRelEntity();
            userOrganRelEntity.setUserId(userId);
            userOrganRelEntity.setOrganId(organId);
            this.userOrganRepository.save(userOrganRelEntity);
        }
        return new Result(Constant.OK);
    }

    @Override
    public Result deleteUser(String userId) throws Exception {
        List<String> ids = Arrays.asList(userId.split(","));
        ids.forEach(m -> {
            QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
            queryFactory.delete(qSysUserInfoEntity)
                    .where(qSysUserInfoEntity.userId.eq(m)).execute();
            QUserOrganRelEntity qUserOrganRelEntity = QUserOrganRelEntity.userOrganRelEntity;
            QUserPermissionRelEntity qUserPermissionRelEntity = QUserPermissionRelEntity.userPermissionRelEntity;
            queryFactory.delete(qUserOrganRelEntity)
                    .where(qUserOrganRelEntity.userId.eq(m)).execute();
            queryFactory.delete(qUserPermissionRelEntity)
                    .where(qUserPermissionRelEntity.userId.eq(m)).execute();
        });
        return new Result(Constant.OK);
    }

    @Override
    public Result modifyUser(SysUserInfoEntity userInfoEntity) throws Exception {
        Long phone = userInfoEntity.getPhone();
        String userName = userInfoEntity.getUserName();
        QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
        queryFactory.update(qSysUserInfoEntity)
                .set(qSysUserInfoEntity.userName,userName)
                .set(qSysUserInfoEntity.phone,phone)
                .where(qSysUserInfoEntity.userId.eq(userInfoEntity.getUserId()))
                .execute();
        return new Result(Constant.OK);
    }

    @Override
    public Result selectUser(SysUserInfoEntity userInfoEntity,PageInfo pageInfo) throws Exception {
        Long phone = userInfoEntity.getPhone();
        String mail = userInfoEntity.getMail();
        String weChat = userInfoEntity.getWechat();
        String idCard = userInfoEntity.getIdCard();
        String address = userInfoEntity.getAddress();
        String statue = userInfoEntity.getUserStatue();
        Integer qq = userInfoEntity.getQq();
        Integer education = userInfoEntity.getEducation();
        Long birth = userInfoEntity.getBirth();
        String gender = userInfoEntity.getGender();
        Long expiredTime = userInfoEntity.getExpiredTime();
        QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
        Predicate predicate = qSysUserInfoEntity.userId.isNotNull();
        if(null!=phone) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.phone.eq(phone));
        }
        if(!StringTool.isnull(mail)) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.mail.like(StringTool.sqlLike(mail)));
        }
        if(!StringTool.isnull(weChat)) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.wechat.like(StringTool.sqlLike(weChat)));
        }
        if(!StringTool.isnull(idCard)) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.idCard.like(StringTool.sqlLike(idCard)));
        }
        if(!StringTool.isnull(address)) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.address.like(StringTool.sqlLike(address)));
        }
        if(null!=statue) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.userStatue.eq(statue));
        }
        if(null!=qq) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.qq.eq(qq));
        }
        if(null!=education) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.education.eq(education));
        }
        if(null!=birth) {
        predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.birth.eq(birth));
        }
        if(null!=gender) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.gender.eq(gender));
        }
        if(null!=expiredTime) {
            predicate = ExpressionUtils.and(predicate,qSysUserInfoEntity.expiredTime.eq(expiredTime));
        }
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        if(pageInfo.vaidate()) {
            PageRequest request = PageRequest.of(pageInfo.getPageNum(),pageInfo.getPageSize(),sort);
            Page<SysUserInfoEntity> page = this.userRepository.findAll(predicate,request);
            pageInfo.setTotalInfo(page.getTotalElements());
            pageInfo.setTotalPage(page.getTotalPages());
            return new Result(Constant.SUCCESS_STATUE,"查询成功",page.getContent(),pageInfo);
        }else {
            return new Result(Constant.SUCCESS_STATUE,"查询成功",this.userRepository.findAll(predicate,sort));
        }
    }
}
