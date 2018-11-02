package com.ins.sys.zh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ZKRepository extends JpaRepository<Zk,String>,QuerydslPredicateExecutor {
}
