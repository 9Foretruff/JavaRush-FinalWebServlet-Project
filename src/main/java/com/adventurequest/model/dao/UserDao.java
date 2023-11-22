package com.adventurequest.model.dao;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<String, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String FIND_ALL_SQL = """
                SELECT username, password, email
                FROM user
            """;

    private static final String FIND_BY_USERNAME_SQL = """
                SELECT username, password, email
                FROM user
                WHERE username LIKE ?
            """;
    private static final String DELETE_SQL = """
                DELETE 
                FROM "user"
                WHERE username LIKE ? 
            """;
    private static final String UPDATE_SQL = """
                UPDATE "user"
                SET username = ? ,
                 password = ? ,
                 email = ?
            """;

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }


    @Override
    public List<UserEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<UserEntity> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UserEntity buildUser(ResultSet resultSet) throws SQLException {
        return new UserEntity(
                resultSet.getObject("username", String.class),
                resultSet.getObject("password", String.class),
                resultSet.getObject("email", String.class)
        );
    }

    @Override
    public Optional<UserEntity> findByUsername(String Username) {
        return Optional.empty();
    }

    @Override
    public boolean delete(String Username) {
        return false;
    }

    @Override
    public void update(UserEntity entity) {

    }

    @Override
    public UserEntity save(UserEntity entity) {
        return null;
    }
}
