package com.chenar.spring.data.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public class ChenarJpaRepositoryFactoryBean <T extends Repository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    private EntityManager entityManager;
    private EntityPathResolver entityPathResolver;
    private EscapeCharacter escapeCharacter;
    private JpaQueryMethodFactory queryMethodFactory;
    private JPAQueryFactory chenarJpaQueryFactory;
    private JPASQLQueryFactory jpaSqlQueryFactory;

    public ChenarJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
        this.entityManager = entityManager;
    }

    @Autowired
    public void setEntityPathResolver(ObjectProvider<EntityPathResolver> resolver) {
        super.setEntityPathResolver(resolver);
        this.entityPathResolver = resolver.getIfAvailable(() -> SimpleEntityPathResolver.INSTANCE);
    }

    @Autowired
    public void setQueryMethodFactory(@Nullable JpaQueryMethodFactory factory) {
        super.setQueryMethodFactory(factory);
        if (factory != null) {
            this.queryMethodFactory = factory;
        }
    }

    @Autowired
    public void setJPAQueryFactory(JPAQueryFactory chenarJpaQueryFactory) {
        this.chenarJpaQueryFactory = chenarJpaQueryFactory;
    }

    @Autowired
    public void setJpaSqlQueryFactory(JPASQLQueryFactory jpaSqlQueryFactory) {
        this.jpaSqlQueryFactory = jpaSqlQueryFactory;
    }

    @Override
    protected RepositoryFactorySupport doCreateRepositoryFactory() {

        ChenarJpaRepositoryFactory factory = new ChenarJpaRepositoryFactory(entityManager,
                jpaSqlQueryFactory,
                entityPathResolver,
                chenarJpaQueryFactory);
        factory.setEntityPathResolver(this.entityPathResolver);
        factory.setEscapeCharacter(this.escapeCharacter);
        if (this.queryMethodFactory != null) {
            factory.setQueryMethodFactory(this.queryMethodFactory);
        }

        return factory;
    }

    public void setEscapeCharacter(char escapeCharacter) {
        super.setEscapeCharacter(escapeCharacter);
        this.escapeCharacter = EscapeCharacter.of(escapeCharacter);
    }
}
