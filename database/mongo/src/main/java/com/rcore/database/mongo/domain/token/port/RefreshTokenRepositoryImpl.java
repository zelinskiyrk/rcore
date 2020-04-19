package com.rcore.database.mongo.domain.token.port;


import com.rcore.database.mongo.common.query.AbstractModifyQuery;
import com.rcore.database.mongo.domain.token.port.model.RefreshTokenDoc;
import com.rcore.database.mongo.domain.token.port.query.ExpireRefreshTokenQuery;
import com.rcore.database.mongo.domain.token.port.query.FindAllActiveByUserId;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public void expireRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        AbstractModifyQuery modifyQuery = ExpireRefreshTokenQuery.of(refreshTokenEntity.getId());
        mongoTemplate.findAndModify(modifyQuery.getQuery(), modifyQuery.getUpdate(), modifyQuery.getModifyOptions(), RefreshTokenDoc.class);
    }

    @Override
    public List<RefreshTokenEntity> findAllActiveByUserId(String userId) {
        return mongoTemplate.find(FindAllActiveByUserId.of(userId).getQuery(), RefreshTokenEntity.class);
    }

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity object) {
        return mongoTemplate.save(object);
    }

    @Override
    public Boolean delete(RefreshTokenEntity object) {
        long deletedCount = mongoTemplate.remove(object).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Boolean deleteById(String id) {
        long deletedCount = mongoTemplate.remove(Query.query(Criteria.where("id").is(id)), RefreshTokenEntity.class).getDeletedCount();
        return deletedCount > 0 ? true : false;
    }

    @Override
    public Optional<RefreshTokenEntity> findById(String id) {
        return Optional.ofNullable(mongoTemplate.findById(id, RefreshTokenEntity.class));
    }

    @Override
    public SearchResult<RefreshTokenEntity> find(Long size, Long skip) {
        return SearchResult.withItemsAndCount(
                mongoTemplate.find(new Query().limit(size.intValue()).skip(skip), RefreshTokenEntity.class),
                count()
        );
    }

    @Override
    public Long count() {
        return mongoTemplate.count(new Query(), RefreshTokenEntity.class);
    }
}