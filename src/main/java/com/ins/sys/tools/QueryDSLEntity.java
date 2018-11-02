package com.ins.sys.tools;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;

import java.util.ArrayList;
import java.util.List;

public class QueryDSLEntity {

    private List<Expression> expressions;

    private List<EntityPath> entityPaths;

    private List<Predicate> predicates;

    private List<OrderSpecifier> orderSpecifiers;

    public QueryDSLEntity() {
        this.expressions = new ArrayList<>();
        this.entityPaths = new ArrayList<>();
        this.predicates = new ArrayList<>();
        this.orderSpecifiers = new ArrayList<>();
    }

    public void add(Expression expression) {
        this.expressions.add(expression);
    }

    public void add(EntityPath entityPath) {
        this.entityPaths.add(entityPath);
    }

    public void add(Predicate predicate) {
        this.predicates.add(predicate);
    }

    public void add(OrderSpecifier orderSpecifier) {
        this.orderSpecifiers.add(orderSpecifier);
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public List<EntityPath> getEntityPaths() {
        return entityPaths;
    }

    public void setEntityPaths(List<EntityPath> entityPaths) {
        this.entityPaths = entityPaths;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public List<OrderSpecifier> getOrderSpecifiers() {
        return orderSpecifiers;
    }

    public void setOrderSpecifiers(List<OrderSpecifier> orderSpecifiers) {
        this.orderSpecifiers = orderSpecifiers;
    }

    public Expression[] expressionToArray() {
        return expressions.toArray(new Expression[expressions.size()]);
    }

    public EntityPath[] entityPathToArray() {
        return entityPaths.toArray(new EntityPath[entityPaths.size()]);
    }

    public Predicate[] predicatesToArray() {
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public OrderSpecifier[] orderSpecifiersToArray() {
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

}
