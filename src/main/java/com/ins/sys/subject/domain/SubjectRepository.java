package com.ins.sys.subject.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SubjectRepository extends JpaRepository<SubjectInfo,String>, QuerydslPredicateExecutor {
}
