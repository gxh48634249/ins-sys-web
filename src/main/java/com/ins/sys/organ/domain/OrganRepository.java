package com.ins.sys.organ.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OrganRepository extends
        JpaRepository<OrganInfoEntity,String>
        ,QuerydslPredicateExecutor {
}
