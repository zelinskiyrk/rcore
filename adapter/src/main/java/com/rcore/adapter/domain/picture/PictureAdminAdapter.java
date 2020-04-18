package com.rcore.adapter.domain.picture;

import com.rcore.adapter.domain.picture.dto.PictureDTO;
import com.rcore.adapter.domain.picture.mapper.PictureMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.file.exception.FileNotFoundException;
import com.rcore.domain.picture.config.PictureConfig;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.util.Optional;

@RequiredArgsConstructor
public class PictureAdminAdapter {
    private PictureMapper pictureMapper = new PictureMapper();
    private UserMapper userMapper = new UserMapper();
    private final PictureConfig pictureConfig;

    public PictureDTO create(UserDTO actor, InputStream content, String fileName, String contentType, boolean isPrivate) throws AuthorizationException {
        return pictureMapper.map(pictureConfig.admin.createUseCase(userMapper.inverseMap(actor))
                .create(content, fileName, contentType, isPrivate));
    }

    public Boolean delete(UserDTO actor, PictureDTO picture) throws AuthorizationException {
        return pictureConfig.admin.deleteUseCase(userMapper.inverseMap(actor))
                .delete(pictureMapper.inverseMap(picture));
    }

    public PictureDTO update(UserDTO actor, PictureDTO picture) throws AuthorizationException, FileNotFoundException {
        return pictureMapper.map(pictureConfig.admin.updateUseCase(userMapper.inverseMap(actor))
                .update(pictureMapper.inverseMap(picture)));
    }

    public Optional<PictureDTO> findById(UserDTO actor, String id) throws AuthorizationException {
        return pictureConfig.admin.viewUseCase(userMapper.inverseMap(actor))
                .findById(id)
                .map(pictureMapper::map);
    }

}
