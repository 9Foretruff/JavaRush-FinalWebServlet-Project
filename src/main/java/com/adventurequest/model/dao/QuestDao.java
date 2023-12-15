package com.adventurequest.model.dao;

import com.adventurequest.model.entity.DifficultyEnum;
import com.adventurequest.model.entity.QuestEntity;
import com.adventurequest.model.exeption.DatabaseAccessException;
import com.adventurequest.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class QuestDao implements Dao<String, QuestEntity> {
    private static final QuestDao INSTANCE = new QuestDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestDao.class);
    private static final String FIND_ALL_SQL = """
                SELECT id , name , description , quest_photo  ,difficulty , author
                FROM adventure_quest_schema.quest
            """;
    private static final String DELETE_SQL = """
                DELETE
                FROM adventure_quest_schema.quest
                WHERE id = ?
            """;
    private static final String SAVE_SQL = """
               INSERT INTO adventure_quest_schema.quest(name, description, quest_photo, difficulty, author)
               VALUES (? , ? , ? , ? , ?)
            """;

    private static final String FIND_QUEST_BY_NAME_AND_AUTHOR_SQL = """
                SELECT id , name , description , quest_photo , difficulty , author
                FROM adventure_quest_schema.quest
                WHERE name LIKE ? AND author LIKE ?
            """;

    private QuestDao() {
    }

    public static QuestDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<QuestEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<QuestEntity> quests = new ArrayList<>();
            while (resultSet.next()) {
                quests.add(buildQuest(resultSet));
            }
            return quests;
        } catch (SQLException e) {
            LOGGER.error("Error , while finding all quests", e);
            throw new DatabaseAccessException("Error , while finding all quests", e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setObject(1, id);
            var i = preparedStatement.executeUpdate();
            return i > 0;
        } catch (SQLException e) {
            LOGGER.error("Error , while finding all quests", e);
            throw new DatabaseAccessException("Error , while finding all quests", e);
        }
    }

    @Override
    public boolean update(QuestEntity entity) {
        return false;
    }

    @Override
    public boolean save(QuestEntity entity) {
        try (var connection = ConnectionManager.get();
             var save = connection.prepareStatement(SAVE_SQL);
             var findQuest = connection.prepareStatement(FIND_QUEST_BY_NAME_AND_AUTHOR_SQL)) {
            findQuest.setObject(1, entity.getName());
            findQuest.setObject(2, entity.getAuthor());
            var quest = findQuest.executeQuery();
            if (quest.next()) {
                return false;
            }
            save.setObject(1, entity.getName());
            save.setObject(2, entity.getDescription());
            save.setObject(3, entity.getQuestPhoto());
            save.setObject(4, entity.getDifficulty() , Types.OTHER);
            save.setObject(5, entity.getAuthor());
            var executed = save.executeUpdate();
            return executed > 0;
        } catch (SQLException e) {
            LOGGER.error("Error , while saving quest", e);
            throw new DatabaseAccessException("Error , while saving quest", e);
        }
    }

    private QuestEntity buildQuest(ResultSet resultSet) throws SQLException {
        return new QuestEntity(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getBytes("quest_photo"),
                DifficultyEnum.valueOf(resultSet.getObject("difficulty", String.class)),
                resultSet.getString("author")
        );
    }
}
