package com.chenar.chenar.spring.data.jpa;

import com.querydsl.jpa.sql.JPASQLQuery;

public interface JPASQLQueryFactory {

    JPASQLQuery<?> create();

}
