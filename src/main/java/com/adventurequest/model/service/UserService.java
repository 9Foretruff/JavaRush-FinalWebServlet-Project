package com.adventurequest.model.service;

import com.adventurequest.model.dao.UserDao;
import com.adventurequest.model.dto.UserDto;
import com.adventurequest.model.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    public static final int PASSWORDS_DO_NOT_MATCH = -1;
    public static final int REGISTRATION_SUCCESSFUL = 1;
    public static final int REGISTRATION_FAILED = 0;
    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }

    private boolean save(UserEntity userEntity) {
        return userDao.save(userEntity);
    }

    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(userEntity -> UserDto.builder()
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .build()
                ).collect(Collectors.toList());
    }

    public boolean login(UserEntity userEntity) {
        return userDao.login(userEntity);
    }

    public int registerUser(String username, String password, String confirmPassword, String email) {
        if (!password.equals(confirmPassword)) {
            return PASSWORDS_DO_NOT_MATCH;
        }
        UserEntity user = new UserEntity(username, password, email);
        var save = userDao.save(user);
        return save ? REGISTRATION_SUCCESSFUL : REGISTRATION_FAILED;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
