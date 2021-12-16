package com.chenar.spring.data.upside.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.util.Date;
@Setter
@Getter
@MappedSuperclass
public abstract class GeneralField<ID> {

    /*
     * This document creation date
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date systemCreationDate;
    /*
     * This document modified date
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date systemModifiedDate;
    /*
     * Who create this document.
     */
    protected ID creatorId;
    /*
     * Whose is this document.
     */
    protected ID ownerId;

    protected ID externalId;

    /*
     * Shard number of this document.
     */
    protected Integer shardKey;

    /*
     * These fields used for logic delete of document.
     */
    protected Boolean deleted;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    protected Date deleteDate;
    protected String deleteBy;
}
