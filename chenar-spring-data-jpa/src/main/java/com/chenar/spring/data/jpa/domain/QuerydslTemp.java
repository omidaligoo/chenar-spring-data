package com.chenar.spring.data.jpa.domain;

import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Nullable;


@Getter
@Setter
public class QuerydslTemp<T> extends EntityPathBase<T> {
    public QuerydslTemp(Class<? extends T> type, String variable) {
        super(type, variable);
    }

    public QuerydslTemp(Class<? extends T> type, PathMetadata metadata) {
        super(type, metadata);
    }

    public QuerydslTemp(Class<? extends T> type, PathMetadata metadata, @Nullable PathInits inits) {
        super(type, metadata, inits);
    }
}
