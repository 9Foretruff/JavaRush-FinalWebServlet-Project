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
                SELECT username, password, email , photo , games_played
                FROM adventure_quest_schema.user
                WHERE username LIKE ?
            """;

    private static final String FIND_BY_USERNAME_OR_EMAIL_SQL = """
                SELECT username, password, email
                FROM adventure_quest_schema.user
                WHERE username LIKE ? OR EMAIL LIKE ?
            """;

    private static final String FIND_BY_ALL_FIELD_SQL = """
                SELECT
                username,
                password,
                email,
                photo,
                games_played
                FROM adventure_quest_schema.user
                WHERE username = ? AND password = ? AND email = ?
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
                photo = ?
                WHERE username = ?  AND password = ?
            """;

    private static final String UPDATE_EMAIL_SQL = """
            UPDATE adventure_quest_schema.user
            SET email = ?
            WHERE email = ?
            """;
    private static final String UPDATE_PASSWORD_SQL = """
            UPDATE adventure_quest_schema.user
            SET password = ?
            WHERE username = ?
            """;
    private static final String SAVE_SQL = """
               INSERT INTO adventure_quest_schema.user(username, password, email,photo, games_played)
               VALUES (? , ? , ? , ? , ?)
            """;

    private static final String FIND_USER_BY_EMAIL_SQL = """
            SELECT
            username,
            password,
            email,
            photo,
            games_played
            FROM epic_quest_db.adventure_quest_schema.user
            WHERE email = ?
            """;
    private static final String FIND_EMAIL_BY_EMAIL_SQL = """
            SELECT email FROM adventure_quest_schema.user
            WHERE email = ?
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
            throw new DatabaseAccessException("Error , while finding all", e);
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
            throw new DatabaseAccessException("Error , while finding user by username", e);
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
            throw new DatabaseAccessException("Error , while deleting user", e);
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
            throw new DatabaseAccessException("Error , while updating user", e);
        }
    }

    public Optional<UserEntity> updateEmail(UserEntity user, String newEmail) {
        try (var connection = ConnectionManager.get();
             var result = connection.prepareStatement(FIND_USER_BY_EMAIL_SQL);
             var findEmail = connection.prepareStatement(FIND_EMAIL_BY_EMAIL_SQL);
             var update = connection.prepareStatement(UPDATE_EMAIL_SQL)) {

            findEmail.setObject(1, newEmail);
            var resultSet1 = findEmail.executeQuery();
            if (resultSet1.next()) {
                return Optional.empty();
            }

            update.setObject(1, newEmail);
            update.setObject(2, user.getEmail());
            update.executeUpdate();

            result.setObject(1, newEmail);
            var resultSet = result.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating email for user: {}", user.getUsername(), e);
            throw new DatabaseAccessException("Error , while updating email", e);
        }
    }

    public Optional<UserEntity> updatePassword(UserEntity user, String newPassword) {
        try (var connection = ConnectionManager.get();
             var result = connection.prepareStatement(FIND_BY_USERNAME_SQL);
             var update = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {

            update.setObject(1, newPassword);
            update.setObject(2, user.getUsername());
            update.executeUpdate();

            result.setObject(1, user.getUsername());
            var resultSet = result.executeQuery();
            if (resultSet.next()) {
                return Optional.of(buildUser(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Error updating password for user: {}", user.getUsername(), e);
            throw new DatabaseAccessException("Error , while updating password", e);
        }
    }

    @Override
    public boolean save(UserEntity entity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL);
             var preparedStatement1 = connection.prepareStatement(FIND_BY_USERNAME_OR_EMAIL_SQL)) {
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
            throw new DatabaseAccessException("Error , while saving user", e);
        }
    }

    public Optional<UserEntity> login(UserEntity userEntity) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(LOGIN_SQL)) {
            preparedStatement.setObject(1, userEntity.getUsername());
            preparedStatement.setObject(2, userEntity.getPassword());
            preparedStatement.setObject(3, userEntity.getEmail());
            var execute = preparedStatement.executeQuery();
            if (execute.next()) {
                return Optional.of(buildUser(execute));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Error logging in user", e);
            throw new DatabaseAccessException("Error , while logging in user", e);
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