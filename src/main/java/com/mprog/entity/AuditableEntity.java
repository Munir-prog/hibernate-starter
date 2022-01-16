package com.mprog.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class AuditableEntity<T extends Serializable> implements BaseEntity<T>{

    @Column(name = "created_at")
    private String createdBy;

    @Column(name = "created_by")
    private Instant createdAt;

}
