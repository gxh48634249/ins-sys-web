package com.ins.sys.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UseRoleRepository extends JpaRepository<UserRoleRelEntity,String>,QuerydslPredicateExecutor {
}
