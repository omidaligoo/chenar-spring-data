package com.chenar.spring.data.jpa.extend.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.*;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.util.function.Supplier;

public class ChenarJPAQueryFactory implements JPQLQueryFactory {

    @Nullable
    private final JPQLTemplates templates;

    private final Supplier<EntityManager> entityManager;

    public ChenarJPAQueryFactory(final EntityManager entityManager) {
        this.entityManager = () -> entityManager;
        this.templates = null;
    }

    public ChenarJPAQueryFactory(JPQLTemplates templates, final EntityManager entityManager) {
        this.entityManager = () -> entityManager;
        this.templates = templates;
    }

    public ChenarJPAQueryFactory(Supplier<EntityManager> entityManager) {
        this.entityManager = entityManager;
        this.templates = null;
    }

    public ChenarJPAQueryFactory(JPQLTemplates templates, Supplier<EntityManager> entityManager) {
        this.entityManager = entityManager;
        this.templates = templates;
    }




    @Override
    public JPADeleteClause delete(EntityPath<?> path) {
        if (templates != null) {
            return new JPADeleteClause(entityManager.get(), path, templates);
        } else {
            return new JPADeleteClause(entityManager.get(), path);
        }
    }

    @Override
    public <T> ChenarJPAQuery<T> select(Expression<T> expr) {
        return query().select(expr);
    }

    @Override
    public ChenarJPAQuery<Tuple> select(Expression<?>... exprs) {
        return query().select(exprs);
    }

    @Override
    public <T> ChenarJPAQuery<T> selectDistinct(Expression<T> expr) {
        return select(expr).distinct();
    }

    @Override
    public ChenarJPAQuery<Tuple> selectDistinct(Expression<?>... exprs) {
        return select(exprs).distinct();
    }

    @Override
    public ChenarJPAQuery<Integer> selectOne() {
        return select(Expressions.ONE);
    }

    @Override
    public ChenarJPAQuery<Integer> selectZero() {
        return select(Expressions.ZERO);
    }

    @Override
    public <T> ChenarJPAQuery<T> selectFrom(EntityPath<T> from) {
        return select(from).from(from);
    }

    @Override
    public ChenarJPAQuery<?> from(EntityPath<?> from) {
        return query().from(from);
    }

    @Override
    public ChenarJPAQuery<?> from(EntityPath<?>... from) {
        return query().from(from);
    }

    @Override
    public JPAUpdateClause update(EntityPath<?> path) {
        if (templates != null) {
            return new JPAUpdateClause(entityManager.get(), path, templates);
        } else {
            return new JPAUpdateClause(entityManager.get(), path);
        }
    }

    @Override
    public JPAInsertClause insert(EntityPath<?> path) {
        if (templates != null) {
            return new JPAInsertClause(entityManager.get(), path, templates);
        } else {
            return new JPAInsertClause(entityManager.get(), path);
        }
    }

    @Override
    public ChenarJPAQuery<?> query() {
        if (templates != null) {
            return new ChenarJPAQuery<Void>(entityManager.get(), templates);
        } else {
            return new ChenarJPAQuery<Void>(entityManager.get());
        }
    }
}
