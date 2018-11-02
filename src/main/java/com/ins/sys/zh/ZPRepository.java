package com.ins.sys.zh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ZPRepository extends JpaRepository<ZP,String>, QuerydslPredicateExecutor {
}
                        