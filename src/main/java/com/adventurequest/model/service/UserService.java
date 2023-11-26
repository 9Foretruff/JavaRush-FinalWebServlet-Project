package com.adventurequest.model.service;

import com.adventurequest.controller.servlet.RegistrationServlet;
import com.adventurequest.model.dao.UserDao;
import com.adventurequest.model.dto.UserDto;
import com.adventurequest.model.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    public static final int PASSWORDS_DO_NOT_MATCH = -1;
    public static final int REGISTRATION_SUCCESSFUL = 1;
    public static final int REGISTRATION_FAILED = 0;
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

    public Optional<UserEntity> login(UserEntity userEntity) {
        var result = userDao.login(userEntity);
        if (result.isPresent()) {
            LOGGER.info("User {} logged in successfully.", userEntity.getUsername());
        } else {
            LOGGER.warn("Failed login attempt for user {}.", userEntity.getUsername());
        }
        return result;
    }

    public int registerUser(String username, String password, String confirmPassword, String email) {
        if (!password.equals(confirmPassword)) {
            LOGGER.warn("Registration failed for user {}: Passwords do not match.", username);
            return PASSWORDS_DO_NOT_MATCH;
        }

        UserEntity user = new UserEntity(username, password, email,null,null);
        var save = userDao.save(user);
        if (save) {
            LOGGER.info("User {} registered successfully.", username);
            return REGISTRATION_SUCCESSFUL;
        } else {
            LOGGER.warn("Registration failed for user {}: User already exists.", username);
            return REGISTRATION_FAILED;
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
