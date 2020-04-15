package com.rcore.adapter.domain.token.mapper;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.token.entity.AccessTokenEntity;

public class AccessTokenMapper implements ExampleDataMapper<AccessTokenEntity, AccessTokenDTO> {

    @Override
    public AccessTokenDTO map(AccessTokenEntity accessTokenEntity) {
        return AccessTokenDTO.builder()
                .id(accessTokenEntity.getId())
                .createdAt(accessTokenEntity.getCreatedAt())
                .createFromRefreshTokenId(accessTokenEntity.getCreateFromRefreshTokenId())
                .expireAt(accessTokenEntity.getExpireAt())
                .roles(accessTokenEntity.getRoles())
                .sign(accessTokenEntity.getSign())
                .timeZone(accessTokenEntity.getTimeZone())
                .updatedAt(accessTokenEntity.getUpdatedAt())
                .userId(accessTokenEntity.getUserId())
                .build();
    }

    @Override
    public AccessTokenEntity inverseMap(AccessTokenDTO accessTokenDTO) {
        return AccessTokenEntity.builder()
                .id(accessTokenDTO.getId())
                .createdAt(accessTokenDTO.getCreatedAt())
                .createFromRefreshTokenId(accessTokenDTO.getCreateFromRefreshTokenId())
                .expireAt(accessTokenDTO.getExpireAt())
                .roles(accessTokenDTO.getRoles())
                .sign(accessTokenDTO.getSign())
                .timeZone(accessTokenDTO.getTimeZone())
                .updatedAt(accessTokenDTO.getUpdatedAt())
                .userId(accessTokenDTO.getUserId())
                .build();
    }
}
