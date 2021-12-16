package com.chenar.spring.data.jpa;

import com.chenar.spring.data.jpa.extend.querydsl.ChenarJPAQueryFactory;
import com.querydsl.core.types.EntityPath;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryComposition;
import org.springframework.data.repository.core.support.RepositoryFragment;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class ChenarJpaRepositoryFactory extends JpaRepositoryFactory {

    private final JPASQLQueryFactory jpaSQLQueryFactory;
    private final EntityManager entityManager;
    private final EntityPathResolver entityPathResolver;
    private final ChenarJPAQueryFactory chenarJpaQueryFactory;



    public ChenarJpaRepositoryFactory(EntityManager entityManager,
                                                JPASQLQueryFactory jpaSqlFactory,
                                                EntityPathResolver entityPathResolver,
                                                ChenarJPAQueryFactory chenarJpaQueryFactory) {
        super(entityManager);
        this.entityManager = entityManager;
        this.jpaSQLQueryFactory = jpaSqlFactory;
        this.entityPathResolver = entityPathResolver;
        this.chenarJpaQueryFactory = chenarJpaQueryFactory;
    }

    @Override
    protected RepositoryComposition.RepositoryFragments getRepositoryFragments(RepositoryMetadata metadata) {

        RepositoryComposition.RepositoryFragments fragments = super.getRepositoryFragments(metadata);
        JpaEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType());
        EntityPath<?> path = entityPathResolver.createPath(entityInformation.getJavaType());
        Object simpleJPAQuerydslFragment = getTargetRepositoryViaReflection(SimpleQuerydslJpaFragment.class,
                path,
                chenarJpaQueryFactory,
                jpaSQLQueryFactory,
                entityManager);
        RepositoryFragment<Object> fragment = RepositoryFragment.implemented(simpleJPAQuerydslFragment);
        return fragments.append(fragment);
    }
}
