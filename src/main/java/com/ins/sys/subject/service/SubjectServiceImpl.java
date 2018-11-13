package com.ins.sys.subject.service;

import com.ins.sys.subject.domain.QSubjectInfo;
import com.ins.sys.subject.domain.SubjectInfo;
import com.ins.sys.subject.domain.SubjectRepository;
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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class SubjectServiceImpl extends BasicService<SubjectInfo> implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectInfo save(SubjectInfo subjectInfo) throws Exception {
        subjectInfo.setId(MD5.id());
        subjectInfo.setCreateTime(new Date());
        return this.subjectRepository.save(subjectInfo);
    }

    @Transactional
    @Override
    public Long remove(SubjectInfo subjectInfo) throws Exception {
        QSubjectInfo qSubjectInfo = QSubjectInfo.subjectInfo;
        if(StringTool.isnull(subjectInfo.getId())) {
            return null;
        }
        return this.queryFactory.delete(qSubjectInfo).where(qSubjectInfo.id.eq(subjectInfo.getId())).execute();
    }

    @Transactional
    @Override
    public SubjectInfo modify(SubjectInfo subjectInfo) throws Exception {
        QSubjectInfo qSubjectInfo = QSubjectInfo.subjectInfo;
        if(StringTool.isnull(subjectInfo.getId())) {
            return null;
        }
        this.queryFactory.update(qSubjectInfo).set(qSubjectInfo.mustContain,subjectInfo.getMustContain()).set(qSubjectInfo.notContain,subjectInfo.getNotContain()).set(qSubjectInfo.subjectName,subjectInfo.getSubjectName())
                .where(qSubjectInfo.id.eq(subjectInfo.getId()))
                .execute();
        return subjectInfo;
    }

    @Override
    public Page<SubjectInfo> findByPage(SubjectInfo subjectInfo, PageInfo pageInfo) throws Exception {
        QSubjectInfo qSubjectInfo = QSubjectInfo.subjectInfo;
        Predicate query = qSubjectInfo.id.isNotNull();
        if(!StringTool.isnull(subjectInfo.getCreateUser())) {
            query = ExpressionUtils.and(query, qSubjectInfo.createUser.eq(subjectInfo.getCreateUser()));
        }
        if(!StringTool.isnull(subjectInfo.getMustContain())) {
            query = ExpressionUtils.and(query, qSubjectInfo.mustContain.like(StringTool.sqlLike(subjectInfo.getMustContain())));
        }
        if(!StringTool.isnull(subjectInfo.getNotContain())) {
            query = ExpressionUtils.and(query, qSubjectInfo.notContain.like(StringTool.sqlLike(subjectInfo.getNotContain())));
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest request = pageInfo.getPageRequest(sort);
        return subjectRepository.findAll(query, request);
    }

    @Override
    public List<SubjectInfo> findAll() throws Exception {
        return this.subjectRepository.findAll();
    }

    @Override
    public SubjectInfo findById(String id) throws Exception {
        if(StringTool.isnull(id)) {
            return null;
        }
        return subjectRepository.findById(id).get();
    }
}
