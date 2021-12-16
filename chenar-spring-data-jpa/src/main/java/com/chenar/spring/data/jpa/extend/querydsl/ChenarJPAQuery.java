package com.chenar.spring.data.jpa.extend.querydsl;

import com.chenar.spring.data.common.extention.ExtendWhere;
import com.chenar.spring.data.upside.querydsl.ExtendedWhere;
import com.querydsl.core.DefaultQueryMetadata;
import com.querydsl.core.QueryMetadata;
import com.querydsl.core.Tuple;
import com.querydsl.core.support.QueryBase;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.AbstractJPAQuery;
import com.querydsl.jpa.impl.JPAProvider;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;

public class ChenarJPAQuery<T>  extends AbstractJPAQuery<T, ChenarJPAQuery<T>> implements ExtendedWhere<T,ChenarJPAQuery<T>> {


        /**
         * Creates a new detached query
         * The query can be attached via the clone method
         */
        public ChenarJPAQuery() {
            super(null, JPQLTemplates.DEFAULT, new DefaultQueryMetadata());
        }

        /**
         * Creates a new EntityManager bound query
         *
         * @param em entity manager
         */
        public ChenarJPAQuery(EntityManager em) {
            super(em, JPAProvider.getTemplates(em), new DefaultQueryMetadata());
        }

        /**
         * Creates a new EntityManager bound query
         *
         * @param em entity manager
         * @param metadata query metadata
         */
        public ChenarJPAQuery(EntityManager em, QueryMetadata metadata) {
            super(em, JPAProvider.getTemplates(em), metadata);
        }

        /**
         * Creates a new query
         *
         * @param em entity manager
         * @param templates templates
         */
        public ChenarJPAQuery(EntityManager em, JPQLTemplates templates) {
            super(em, templates, new DefaultQueryMetadata());
        }

        /**
         * Creates a new query
         *
         * @param em entity manager
         * @param templates templates
         * @param metadata query metadata
         */
        public ChenarJPAQuery(EntityManager em, JPQLTemplates templates, QueryMetadata metadata) {
            super(em, templates, metadata);
        }

        @Override
        public ChenarJPAQuery<T> clone(EntityManager entityManager, JPQLTemplates templates) {
            ChenarJPAQuery<T> q = new ChenarJPAQuery<T>(entityManager, templates, getMetadata().clone());
            q.clone(this);
            return q;
        }

        @Override
        public ChenarJPAQuery<T> clone(EntityManager entityManager) {
            return clone(entityManager, JPAProvider.getTemplates(entityManager));
        }

        @Override
        public <U> ChenarJPAQuery<U> select(Expression<U> expr) {
            queryMixin.setProjection(expr);
            @SuppressWarnings("unchecked") // This is the new type
            ChenarJPAQuery<U> newType = (ChenarJPAQuery<U>) this;
            return newType;
        }

        @Override
        public ChenarJPAQuery<Tuple> select(Expression<?>... exprs) {
            queryMixin.setProjection(exprs);
            @SuppressWarnings("unchecked") // This is the new type
            ChenarJPAQuery<Tuple> newType = (ChenarJPAQuery<Tuple>) this;
            return newType;
        }
}
