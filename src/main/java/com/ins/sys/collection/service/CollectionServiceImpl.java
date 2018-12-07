package com.ins.sys.collection.service;

import com.ins.sys.collection.domain.CollectionInfo;
import com.ins.sys.collection.domain.CollectionRepository;
import com.ins.sys.collection.domain.QCollectionInfo;
import com.ins.sys.subject.domain.QSubjectInfo;
import com.ins.sys.subject.domain.SubjectInfo;
import com.ins.sys.subject.domain.SubjectRepository;
import com.ins.sys.subject.service.SubjectService;
import com.ins.sys.tools.BasicService;
import com.ins.sys.tools.MD5;
import com.ins.sys.tools.PageInfo;
import com.ins.sys.tools.StringTool;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;



@Service
public class CollectionServiceImpl extends BasicService<CollectionInfo> implements   CollectionService{


    @Autowired
    private CollectionRepository collectionRepository;

    @Override
    public CollectionInfo save(CollectionInfo collectionInfo) throws Exception {

        collectionInfo.setId(MD5.id());
        collectionInfo.setCreateTime(new Date());
        return  this.collectionRepository.save(collectionInfo);
    }

    @Override
    public Long remove(CollectionInfo collectionInfo) throws Exception {
        QCollectionInfo qCollectionInfo = QCollectionInfo.collectionInfo;
        if(StringTool.isnull(collectionInfo.getUsrsId())) {
            return null;
        }
        System.out.print("-----------------------"+collectionInfo.getUsrsId());
        return this.queryFactory.delete(qCollectionInfo).where(qCollectionInfo.usrsId.eq(collectionInfo.getUsrsId())).execute();
    }

    @Override
    public CollectionInfo modify(CollectionInfo collectionInfo) throws Exception {
        return null;
    }

    @Override
    public Page<CollectionInfo> findByPage(CollectionInfo collectionInfo, PageInfo pageInfo) throws Exception {
        return null;
    }

    @Override
    public List<CollectionInfo> findAll() throws Exception {
        return null;
    }

    @Override
    public CollectionInfo findById(String id) throws Exception {
        return null;
    }


    @Override
    public List<CollectionInfo> findbyUsrsId(String UsrsId) {
        QCollectionInfo qCollectionInfo = QCollectionInfo.collectionInfo;
        return this.queryFactory.select(qCollectionInfo).from(qCollectionInfo).where(qCollectionInfo.usrsId.eq(UsrsId)).fetch();
    }
}
