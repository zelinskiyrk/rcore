package com.rcore.domain.auth.credential.entity;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Учетная запись
 * Работает с данными для авторизации и доступами
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class CredentialEntity extends BaseEntity {

    /**
     * Идентификатор
     */
    protected String id;

    /**
     * Имя пользователя для авторизации (login/email)
     *
     */
    protected String username;

    /**
     * Пароль для авторизации
     */
    protected String password;

    /**
     * Номер телефона для 2FA
     */
    protected String phone;

    /**
     * Адрес электронной почты для 2FA
     */
    protected String email;

    /**
     * Пассив ролей учетной записи
     */
    protected List<Role> roles = new ArrayList<>();

    /**
     * Статус учетной записи
     */
    protected Status status = Status.ACTIVE;

    public enum Status {
        ACTIVE, BLOCK
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public static class Role {

        /**
         * Идентификатор роли
         */
        String roleId;

        /**
         * Наименование роли
         */
        String name;

        /**
         * Флаг - роль заблокированная для данной учетной записи
         */
        boolean isBlocked;
    }

    /**
     * Methods
     */

    public boolean isBlocked() {
        return this.status.equals(Status.BLOCK);
    }

}