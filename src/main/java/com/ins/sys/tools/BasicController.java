package com.ins.sys.tools;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 核心Controller,初始化EntityManager以及JPAQueryFactory
 */
public class BasicController<T> {

    public JPAQueryFactory queryFactory;

    @Autowired
    public EntityManager entityManager;

    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

}
