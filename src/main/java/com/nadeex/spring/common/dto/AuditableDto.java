package com.nadeex.spring.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * Base DTO with audit fields for all entities that track creation and modification.
 * Extend this for any DTO that represents an auditable domain object.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AuditableDto extends BaseDto {

    private String createdBy;
    private Instant createdAt;
    private String updatedBy;
    private Instant updatedAt;
}

