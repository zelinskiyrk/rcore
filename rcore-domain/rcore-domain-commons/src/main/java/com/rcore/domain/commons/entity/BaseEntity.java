package com.rcore.domain.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public abstract class BaseEntity {
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt = LocalDateTime.now();

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


}
