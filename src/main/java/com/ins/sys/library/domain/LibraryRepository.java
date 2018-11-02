package com.ins.sys.library.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LibraryRepository extends
        JpaRepository<LibraryInfoEntity,String>,QuerydslPredicateExecutor {
}
