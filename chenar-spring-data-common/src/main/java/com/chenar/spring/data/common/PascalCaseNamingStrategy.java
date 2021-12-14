package com.chenar.spring.data.common;


import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

public class PascalCaseNamingStrategy implements NamingStrategy {

    @Override
    public String getTableName(Class<?> type) {
        return type.getSimpleName();
    }

    @Override
    public String getColumnName(RelationalPersistentProperty property) {
        return property.getName().substring(0, 1).toUpperCase() + property.getName().substring(1);
    }
}
