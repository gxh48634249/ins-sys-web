package com.ins.sys.resource.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ResourceRepository extends JpaRepository<ResourceInfoEntity,String>,QuerydslPredicateExecutor {
}
