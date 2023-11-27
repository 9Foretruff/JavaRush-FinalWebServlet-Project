package com.adventurequest.model.dao;

import com.adventurequest.model.entity.UserEntity;
import com.adventurequest.model.exeption.DatabaseAccessException;
import com.adventurequest.util.ConnectionManager;
import com.adventurequest.util.DefaultGamesCountUtil;
import com.adventurequest.util.DefaultProfileImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<String, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    private static final String FIND_ALL_SQL = """
                SELECT username, password, email, photo, games_played
                FROM adventure_quest_schema.user
            """;

    private static final String FIND_BY_USERNAME_SQL = """
                SELECT username, password, email
                FROM adventure_quest_schema.user
                WHERE username LIKE ?
            """;

    private static final String FIND_BY_USERNAME_AND_EMAIL_SQL = """
                SELECT username, password, email
                FROM adventure_quest_schema.user
                WHERE username LIKE ? OR EMAIL LIKE ?
            """;

    private static final String LOGIN_SQL = """
                SELECT username, password, email , photo , games_played
                FROM adventure_quest_schema.user
                WHERE username LIKE ? AND password = ? AND EMAIL LIKE ?
            """;
    private static final String DELETE_SQL = """
                DELETE
                FROM adventure_quest_schema.user
                WHERE username LIKE ?
            """;
    private static final String UPDATE_SQL = """
                UPDATE adventure_quest_schema.user
                SET username = ? ,
                 password = ? ,
                 email = ?,
                 photo = ?,
            """;
    private static final String SAVE_SQL = """
               INSERT INTO adventure_quest_schema.user(username, password, email,photo, games_played)
               VALUES (? , ? , ? , ? , ?)
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
            LOGGER.error("Error finding all users", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_USERNAME_SQL)) {
            preparedStatement.setObject(1, username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            }
            return Optional.empty();
        } catch (SQLException e) {
            LOGGER.error("Error finding user by username", e);
            throw new DatabaseAccessException("Error finding user by username", e);
        }
    }

    @Override
    public boolean delete(String username) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setObject(1, username);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error deleting user", e);
            throw new DatabaseAccessException("Error deleting user", e);
        }
    }

    @Override
    public boolean update(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setObject(1, entity.getUsername());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, entity.getPhoto());
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Error updating user", e);
            throw new DatabaseAccessException("Error updating user", e);
        }
    }

    @Override
    public boolean save(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL);
             var preparedStatement1 = connection.prepareStatement(FIND_BY_USERNAME_AND_EMAIL_SQL)) {
            preparedStatement1.setObject(1, entity.getUsername());
            preparedStatement1.setObject(2, entity.getEmail());
            var execute = preparedStatement1.executeQuery();
            if (execute.next()) {
                return false;
            }
            preparedStatement.setObject(1, entity.getUsername());
            preparedStatement.setObject(2, entity.getPassword());
            preparedStatement.setObject(3, entity.getEmail());
            preparedStatement.setObject(4, DefaultProfileImageUtil.getDefaultProfileImageBytes());
            preparedStatement.setObject(5, DefaultGamesCountUtil.getDefaultGamesCount());
            var executed = preparedStatement.executeUpdate();
            return executed > 0;
        } catch (SQLException e) {
            LOGGER.error("Error saving user", e);
            throw new DatabaseAccessException("Error saving user", e);
        }
    }

    public Optional<UserEntity> login(UserEntity userEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(LOGIN_SQL)) {
            preparedStatement.setObject(1, userEntity.getUsername());
            preparedStatement.setObject(2, userEntity.getPassword());
            preparedStatement.setObject(3, userEntity.getEmail());
            var execute = preparedStatement.executeQuery();
            if (execute.next()){
                return Optional.of(buildUser(execute));
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Error logging in user", e);
            throw new DatabaseAccessException("Error logging in user", e);
        }
    }

    private UserEntity buildUser(ResultSet resultSet) throws SQLException {
        return new UserEntity(
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("email"),
                resultSet.getBytes("photo"),
                resultSet.getLong("games_played")
        );

    }

}