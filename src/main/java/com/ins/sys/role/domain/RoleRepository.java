package com.ins.sys.role.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RoleRepository extends JpaRepository<RoleInfoEntity,String>,QuerydslPredicateExecutor {
}
