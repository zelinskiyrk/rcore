package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.usecase.admin.dto.UpdateUserFields;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserSecureAdapter {

    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private final UserConfig userConfig;

    public UserDTO activateUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.activateUseCase()
                .activate(userMapper.inverseMap(userDTO)));
    }

    public UserDTO blockUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.BlockUseCase()
                .block(userMapper.inverseMap(userDTO)));
    }

    public UserDTO createUserByEmail(String email, String password, List<String> roleIds) throws UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.CreateUseCase()
                .createByEmail(email, password, roleIds));
    }

    public Boolean deleteUser(UserDTO userDTO) throws UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
        return userConfig.admin.DeleteUserUseCase()
                .delete(userMapper.inverseMap(userDTO));
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(userMapper.inverseMap(userDTO)));
    }

    public UserDTO update(String userId, UpdateUserFields updateUserFields) throws AuthorizationException, AuthenticationException, UserNotFoundException, TokenExpiredException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(userId, updateUserFields));
    }

}
