package com.ins.sys.user.service;

import com.ins.sys.organ.domain.OrganInfoEntity;
import com.ins.sys.organ.domain.QOrganInfoEntity;
import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.permission.domain.PermissionRepository;
import com.ins.sys.permission.domain.QPermissionInfoEntity;
import com.ins.sys.role.domain.*;
import com.ins.sys.tools.ListUtill;
import com.ins.sys.user.domain.*;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);


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
    private UserPerRepository userPerRepository;

    @Autowired
    private UseRoleRepository useRoleRepository;

    @Autowired
    private RolePerRepository rolePerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public SysUserInfoEntity loadUserByUsername(String s) throws UsernameNotFoundException {
        QSysUserInfoEntity qSysUserInfoEntity = QSysUserInfoEntity.sysUserInfoEntity;
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        QRoleInfoEntity qRoleInfoEntity = QRoleInfoEntity.roleInfoEntity;
        QRolePerRelEntity qRolePerRelEntity = QRolePerRelEntity.rolePerRelEntity;
        QUserRoleRelEntity qUserRoleRelEntity = QUserRoleRelEntity.userRoleRelEntity;
        QUserPermissionRelEntity qUserPermissionRelEntity = QUserPermissionRelEntity.userPermissionRelEntity;
        JPAQuery query = queryFactory.select(qSysUserInfoEntity)
                .from(qSysUserInfoEntity)
                .where(qSysUserInfoEntity.userAccount.eq(s));
        List<SysUserInfoEntity> list = query.fetch();
        if(ListUtill.isnull(list)) {
            throw new UsernameNotFoundException("未查询到"+s+"相关信息");
        }else {
            SysUserInfoEntity sysUserInfoEntity = list.get(0);
            logger.info("SELECT USER "+sysUserInfoEntity.getUserAccount());
            List<PermissionInfoEntity> perList = new ArrayList<>();
            List<RoleInfoEntity> roleList = new ArrayList<>();
            perList = queryFactory.select(qPermissionInfoEntity)
                    .from(qPermissionInfoEntity)
                    .leftJoin(qUserPermissionRelEntity).on(qPermissionInfoEntity.permissionId.eq(qUserPermissionRelEntity.permissionId))
                    .where(qUserPermissionRelEntity.userId.eq(sysUserInfoEntity.getUserId())).fetch();
            roleList = queryFactory.select(qRoleInfoEntity)
                    .from(qRoleInfoEntity)
                    .leftJoin(qUserRoleRelEntity).on(qUserRoleRelEntity.roleId.eq(qUserRoleRelEntity.roleId))
                    .where(qUserRoleRelEntity.userId.eq(sysUserInfoEntity.getUserId())).fetch();
            if(!ListUtill.isnull(roleList)) {
                List<String> roleIds = new ArrayList<>();
                roleList.forEach(m ->{
                    roleIds.add(m.getRoleId());
                });
                List<PermissionInfoEntity> rolePer = new ArrayList<>();
                rolePer = queryFactory.select(qPermissionInfoEntity)
                        .from(qPermissionInfoEntity)
                        .leftJoin(qRolePerRelEntity).on(qRolePerRelEntity.permissionId.eq(qPermissionInfoEntity.permissionId))
                        .where(qRolePerRelEntity.roleId.in(roleIds)).fetch();
                perList.addAll(rolePer);
            }
            logger.info("USER "+ s+ " HAVE PERS:"+JSONArray.fromObject(perList));
            sysUserInfoEntity.setPer(perList);
            List<OrganInfoEntity> organInfoEntities = new ArrayList<>();
            QOrganInfoEntity qOrganInfoEntity = QOrganInfoEntity.organInfoEntity;
            QUserOrganRelEntity qUserOrganRelEntity = QUserOrganRelEntity.userOrganRelEntity;
            organInfoEntities = queryFactory.select(qOrganInfoEntity).from(qOrganInfoEntity)
                    .leftJoin(qUserOrganRelEntity).on(qUserOrganRelEntity.organId.eq(qOrganInfoEntity.organId))
                    .where(qUserOrganRelEntity.userId.eq(sysUserInfoEntity.getUserId())).fetch();
            if(!ListUtill.isnull(organInfoEntities)) {
                sysUserInfoEntity.setOrganInfoEntity(organInfoEntities.get(0));
            }
            return sysUserInfoEntity;
        }
    }
}
