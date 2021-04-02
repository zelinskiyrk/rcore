package com.rcore.domain.token.port.filters;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class AccessTokenFilters extends SearchRequest {
    private String userId;
    private String createFromRefreshTokenId;
    private RefreshTokenEntity.Status status;
}
