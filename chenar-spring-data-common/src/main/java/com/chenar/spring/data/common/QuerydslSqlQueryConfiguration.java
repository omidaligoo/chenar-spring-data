package com.chenar.spring.data.common;

import com.querydsl.sql.SQLQueryFactory;
import com.querydsl.sql.SQLTemplates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.relational.core.mapping.NamingStrategy;

import javax.sql.DataSource;

@Import(ChenarSpringDataCommonConfiguration.class)
@Configuration
public class QuerydslSqlQueryConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public NamingStrategy pascalCaseNamingStrategy() {
        return new PascalCaseNamingStrategy();
    }

    @ConditionalOnMissingBean
    @Bean
    public com.querydsl.sql.Configuration querydslSqlConfiguration(SQLTemplates sqlTemplates) {
        return new com.querydsl.sql.Configuration(sqlTemplates);
    }

    @ConditionalOnMissingBean
    @Bean
    public SQLQueryFactory sqlQueryFactory(com.querydsl.sql.Configuration querydslSqlConfiguration,
                                           DataSource dataSource) {
        return new SQLQueryFactory(querydslSqlConfiguration, dataSource);
    }
}
