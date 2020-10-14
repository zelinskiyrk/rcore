package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureIdGenerator;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
import com.rcore.domain.picture.access.AdminPictureCreateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;

import java.io.InputStream;


public class PictureCreateUseCase extends PictureAdminBaseUseCase {
    private final PictureIdGenerator idGenerator;
    private final PictureStorage pictureStorage;

    public PictureCreateUseCase(PictureRepository pictureRepository, PictureIdGenerator idGenerator, PictureStorage pictureStorage, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(pictureRepository, new AdminPictureCreateAccess(), authorizationByTokenUseCase);
        this.idGenerator = idGenerator;
        this.pictureStorage = pictureStorage;
    }

    public PictureEntity create(InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setId(idGenerator.generate());
        pictureEntity.setIsPrivate(isPrivate);
        pictureEntity.setFileName(fileName);
        pictureEntity.setIsUsed(false);
        pictureEntity.setFilePath(pictureStorage.store(content, fileName, contentType));

        pictureEntity = pictureRepository.save(pictureEntity);
        return pictureEntity;
    }


}