package com.nadeex.spring.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Abstract base class for all DTOs in the nadeex ecosystem.
 * Provides JSON null-exclusion and serialization support.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 1L;
}

