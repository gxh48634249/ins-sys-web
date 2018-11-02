package com.ins.sys.permission.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PermissionRepository extends JpaRepository<PermissionInfoEntity,String> , QuerydslPredicateExecutor {
}
