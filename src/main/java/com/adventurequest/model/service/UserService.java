package com.adventurequest.model.service;

import com.adventurequest.model.dao.UserDao;
import com.adventurequest.model.dto.UserDto;
import com.adventurequest.model.entity.UserEntity;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    private UserService() {
    }
    public List<UserDto> findAll() {
        return userDao.findAll().stream()
                .map(userEntity -> UserDto.builder()
                        .username(userEntity.getUsername())
                        .email(userEntity.getEmail())
                        .build()
                ).collect(Collectors.toList());
    }

    public boolean save(UserEntity userEntity) {
        return userDao.save(userEntity);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
