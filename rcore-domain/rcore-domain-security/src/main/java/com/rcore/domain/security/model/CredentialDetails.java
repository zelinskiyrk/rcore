package com.rcore.domain.security.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Модель с доступами учетной записи
 */
@EqualsAndHashCode
@AllArgsConstructor
@Getter
public class CredentialDetails {

    /**
     * Идентификатор учетной записи
     */
    private String id;

    /**
     * Массив ролей учетной записи
     */
    private List<Role> roles;

    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Role {
        private String name;
    }
}
