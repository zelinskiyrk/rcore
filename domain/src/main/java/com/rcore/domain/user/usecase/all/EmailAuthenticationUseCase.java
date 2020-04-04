package com.rcore.domain.user.usecase.all;

import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.entity.TokenPair;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.port.AuthenticationPort;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;

import java.util.Date;

public class EmailAuthenticationUseCase implements AuthenticationPort {
    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenRepository refreshTokenRepository;

    public EmailAuthenticationUseCase(
            UserRepository userRepository,
            PasswordGenerator passwordGenerator,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.createRefreshTokenUseCase = createRefreshTokenUseCase;
        this.createAccessTokenUseCase = createAccessTokenUseCase;
        this.refreshTokenRepository = refreshTokenRepository;
    }


    @Override
    public TokenPair authentication(String email, String password) throws UserNotFoundException, AuthenticationException, UserBlockedException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) throw new UserNotFoundException();

        if (passwordGenerator.check(userEntity.getId(), password, userEntity.getPassword()) == false) {

            userEntity.setLastFailDate(new Date());
            userEntity.setFails( userEntity.getFails() + 1);
            userRepository.save(userEntity);

            throw new AuthenticationException();
        }

        if (userEntity.getUserStatus().equals(UserStatus.ACTIVE) == false) {

            userEntity.setLastFailDate(new Date());
            userEntity.setFails( userEntity.getFails() + 1);
            userRepository.save(userEntity);

            throw new UserBlockedException();
        }

        userEntity.setFails( 0 );
        userRepository.save(userEntity);

        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(userEntity);
        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(userEntity, refreshTokenEntity);

        TokenPair tokenPair = new TokenPair();
        tokenPair.setAccessToken(accessTokenEntity);
        tokenPair.setRefreshToken(refreshTokenEntity);

        return tokenPair;
    }

    @Override
    public TokenPair getNewTokenPairByRefreshToken(RefreshTokenEntity refreshTokenEntity) throws UserNotFoundException, AuthenticationException, UserBlockedException{
        UserEntity userEntity = userRepository.findById(refreshTokenEntity.getUserId());

        if (userEntity == null) throw new UserNotFoundException();

        if (userEntity.getUserStatus().equals(UserStatus.ACTIVE) == false) {
            throw new UserBlockedException();
        }

        RefreshTokenEntity fromRepo = refreshTokenRepository.findById(refreshTokenEntity.getId());
        if(fromRepo == null) throw new AuthenticationException();

        if(fromRepo.isActive() == false) throw new AuthenticationException();

        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.create(userEntity, refreshTokenEntity);

        TokenPair tokenPair = new TokenPair();
        tokenPair.setAccessToken(accessTokenEntity);
        tokenPair.setRefreshToken(refreshTokenEntity);

        return tokenPair;
    }
}