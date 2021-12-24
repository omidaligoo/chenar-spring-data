package com.chenar.spring.data.jpa;

import com.chenar.spring.data.common.ChenarSpringDataCommonConfiguration;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(JPAProvider.getTemplates(entityManager), entityManager);
    }
}
