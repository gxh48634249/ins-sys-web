package com.ins.sys.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserPerRepository extends JpaRepository<UserPermissionRelEntity,String>,QuerydslPredicateExecutor {
}
