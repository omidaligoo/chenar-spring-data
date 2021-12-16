package com.chenar.spring.data.jpa;

import com.chenar.spring.data.common.ChenarSpringDataCommonConfiguration;
import com.chenar.spring.data.jpa.extend.querydsl.ChenarJPAQueryFactory;
import com.querydsl.jpa.impl.JPAProvider;
import com.querydsl.jpa.sql.JPASQLQuery;
import com.querydsl.sql.SQLTemplates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;

@Import(ChenarSpringDataCommonConfiguration.class)
@Configuration
public class ChenarJpaConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public JPASQLQueryFactory jpaSqlQueryFactory(EntityManager entityManager, SQLTemplates sqlTemplates) {
        return () -> new JPASQLQuery<>(entityManager, sqlTemplates);
    }

    @ConditionalOnMissingBean
    @Bean
    public ChenarJPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new ChenarJPAQueryFactory(JPAProvider.getTemplates(entityManager), entityManager);
    }
}
