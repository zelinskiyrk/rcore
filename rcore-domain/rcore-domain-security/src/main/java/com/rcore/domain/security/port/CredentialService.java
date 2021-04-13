package com.rcore.domain.security.port;

import com.rcore.domain.security.exceptions.AuthenticatedCredentialIsBlockedException;
import com.rcore.domain.security.exceptions.CredentialNotFoundException;
import com.rcore.domain.security.model.CredentialDetails;

/**
 * Интерфейс, содержищий методы проверки доступов для авторизованных учетных данных
 */
public interface CredentialService {

    CredentialDetails getCredentialById(String id) throws AuthenticatedCredentialIsBlockedException, CredentialNotFoundException;
}
