package com.chenar.spring.data.jpa;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Import(ChenarJpaConfiguration.class)
@EnableJpaRepositories(repositoryFactoryBeanClass = ChenarJpaRepositoryFactoryBean.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableChenarJpaRepositories {
}
