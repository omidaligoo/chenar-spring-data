package com.chenar.chenar.spring.data.jpa;

import org.springframework.boot.autoconfigure.data.AbstractRepositoryConfigurationSourceSupport;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.config.BootstrapMode;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Locale;

public class ChenarJpaRepositoriesRegistrar extends AbstractRepositoryConfigurationSourceSupport {

    private BootstrapMode bootstrapMode = null;


    protected Class<? extends Annotation> getAnnotation() {
        return EnableJpaRepositories.class;
    }

    protected Class<?> getConfiguration() {
        return EnableJpaRepositoriesConfiguration.class;
    }

    protected RepositoryConfigurationExtension getRepositoryConfigurationExtension() {
        return new ChenarJpaRepositoryConfigExtension();
    }

    @Override
    protected BootstrapMode getBootstrapMode() {
        return (this.bootstrapMode == null) ? BootstrapMode.DEFAULT : this.bootstrapMode;
    }

    @Override
    public void setEnvironment(Environment environment) {
        super.setEnvironment(environment);
        configureBootstrapMode(environment);
    }

    private void configureBootstrapMode(Environment environment) {
        String property = environment.getProperty("spring.data.jpa.repositories.bootstrap-mode");
        if (StringUtils.hasText(property)) {
            this.bootstrapMode = BootstrapMode.valueOf(property.toUpperCase(Locale.ENGLISH));
        }
    }

    @EnableChenarJpaRepositories
    private static class EnableJpaRepositoriesConfiguration {

    }
}
