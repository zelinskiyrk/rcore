package com.rcore.domain.picture.usecase.secured;

import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureCompressor;
import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.access.AdminPictureUpdateAccess;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;

import java.time.LocalDateTime;

public class PictureUpdateUseCase  extends PictureAdminBaseUseCase {

    private final PictureCompressor pictureCompressor;

    public PictureUpdateUseCase(PictureRepository pictureRepository, PictureCompressor pictureCompressor, AuthorizationByTokenUseCase authorizationByTokenUseCase)throws AuthorizationException {
        super(pictureRepository, new AdminPictureUpdateAccess(), authorizationByTokenUseCase);
        this.pictureCompressor = pictureCompressor;
    }

    public PictureEntity update(PictureEntity pictureEntity) throws FileNotFoundException, AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        PictureEntity oldPicture = pictureRepository.findById(pictureEntity.getId())
                .orElseThrow(() -> new FileNotFoundException());

        oldPicture.setIsPrivate(pictureEntity.getIsPrivate());
        oldPicture.setFileName(pictureEntity.getFileName());
        oldPicture.setUpdatedAt(LocalDateTime.now());

        oldPicture = pictureRepository.save(pictureEntity);
        return oldPicture;
    }

    public PictureEntity addCompressedSize(PictureEntity pictureEntity, Integer width, Double quality) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        pictureEntity.addSize(pictureCompressor.compressImage(pictureEntity, width, quality));
        return pictureRepository.save(pictureEntity);
    }


}