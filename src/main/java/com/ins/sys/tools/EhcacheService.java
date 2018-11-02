package com.ins.sys.tools;

import com.ins.sys.permission.domain.PermissionInfoEntity;
import com.ins.sys.permission.domain.PermissionRepository;
import com.ins.sys.permission.domain.QPermissionInfoEntity;
import com.ins.sys.resource.domain.QResourceInfoEntity;
import com.ins.sys.resource.domain.ResourceRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EhcacheService {

    private Ehcache ehcache;

    @Autowired
    private CacheManager cacheManager;

    private JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @PostConstruct
    public void initCache() {
        queryFactory = new JPAQueryFactory(entityManager);
        this.ehcache = cacheManager.getEhcache("per-resource");
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
        JPAQuery<PermissionInfoEntity> query = queryFactory.select(qPermissionInfoEntity)
                .from(qPermissionInfoEntity)
                .where(qPermissionInfoEntity.resourceId.isNotNull());
        List<PermissionInfoEntity> list = query.fetch();
        if(!ListUtill.isnull(list)) {
            list.forEach(m ->{
                Element element = new Element(m.getResourceId(),m.getPermissionCode());
                ehcache.put(element);
            });
        }
    }

    public boolean insert(String id,Object data) {
        if(StringTool.isnull(id)) {
            return false;
        }
        Element element = new Element(id,data);
        ehcache.put(element);
        return true;
    }

    public boolean insertBatch(List<Element> list) {
        if(ListUtill.isnull(list)) {
            return false;
        }
        ehcache.putAll(list);
        return true;
    }

    public boolean delete(Element element){
        return ehcache.removeElement(element);
    }

    public Element modify(Element element){
        return ehcache.replace(element);
    }

    public Element select(String key) {
        return ehcache.get(key);
    }

    public void reLoadCache(){
        this.ehcache.removeAll();
        QPermissionInfoEntity qPermissionInfoEntity = QPermissionInfoEntity.permissionInfoEntity;
        QResourceInfoEntity qResourceInfoEntity = QResourceInfoEntity.resourceInfoEntity;
        JPAQuery<PermissionInfoEntity> query = queryFactory.select(qPermissionInfoEntity)
                .from(qPermissionInfoEntity)
                .where(qPermissionInfoEntity.resourceId.isNotNull());
        List<PermissionInfoEntity> list = query.fetch();
        if(!ListUtill.isnull(list)) {
            list.forEach(m ->{
                Element element = new Element(m.getResourceId(),m.getPermissionCode());
                ehcache.put(element);
            });
        }
    }

    public void clean(){
        if(null!=this.ehcache){
            if(this.ehcache.isDisabled()){
                this.ehcache.removeAll();
            }
        }
    }
}
