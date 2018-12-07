package com.ins.sys.collection.domain;

import com.ins.sys.subject.domain.SubjectInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CollectionRepository extends JpaRepository<CollectionInfo,String>, QuerydslPredicateExecutor {
}
