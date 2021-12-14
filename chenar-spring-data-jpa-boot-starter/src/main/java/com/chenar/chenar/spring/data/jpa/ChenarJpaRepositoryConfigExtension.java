package com.chenar.chenar.spring.data.jpa;

import com.chenar.chenar.spring.data.jpa.ChenarJpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

public class ChenarJpaRepositoryConfigExtension extends JpaRepositoryConfigExtension {

    @Override
    public String getRepositoryFactoryBeanClassName() {
        return ChenarJpaRepositoryFactoryBean.class.getName();
    }
}
