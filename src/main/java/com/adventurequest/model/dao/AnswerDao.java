package com.adventurequest.model.dao;

import com.adventurequest.model.entity.AnswerEntity;
import com.adventurequest.model.exeption.DatabaseAccessException;
import com.adventurequest.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao implements Dao<String, AnswerEntity> {
    private static final AnswerDao INSTANCE = new AnswerDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerDao.class);
    private static final String FIND_ALL_SQL = """
                SELECT
                id,
                question_id,
                text,
                is_correct
                FROM adventure_quest_schema.answer
            """;
    private static final String DELETE_SQL = """
                DELETE
                FROM adventure_quest_schema.answer
                WHERE id = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO adventure_quest_schema.answer(question_id, text, is_correct)
                VALUES (? , ? , ?)
            """;
    private static final String FIND_ANSWER_BY_QUESTION_ID_AND_TEXT_SQL = """
                SELECT
                id,
                question_id,
                text,
                is_correct
                FROM adventure_quest_schema.answer
                WHERE question_id = ? AND  text ILIKE ?
            """;
    private static final String FIND_QUESTION_BY_ID = """
                SELECT
                id,
                number_of_question,
                quest_id,
                text,
                background_question_photo,
                is_last_question
                FROM adventure_quest_schema.question
                WHERE id = ?
            """;

    private AnswerDao() {
    }

    public static AnswerDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<AnswerEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<AnswerEntity> answers = new ArrayList<>();
            while (resultSet.next()) {
                answers.add(buildAnswer(resultSet));
            }
            return answers;
        } catch (SQLException e) {
            LOGGER.error("Error while finding all answers", e);
            throw new DatabaseAccessException("Error while finding all answers", e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to delete answer with id = {} due to database error", id, e);
            throw new DatabaseAccessException("Error while deleting answer", e);
        }
    }

    @Override
    public boolean save(AnswerEntity entity) {
        try (var connection = ConnectionManager.get();
             var save = connection.prepareStatement(SAVE_SQL);
             var findAnswer = connection.prepareStatement(FIND_ANSWER_BY_QUESTION_ID_AND_TEXT_SQL);
             var findQuestion = connection.prepareStatement(FIND_QUESTION_BY_ID)) {
            findQuestion.setObject(1,entity.getQuestionId());
            var question = findQuestion.executeQuery();
            LOGGER.debug("Checking if question with id : {} exists", entity.getQuestionId());
            if (!question.next()){
                LOGGER.debug("Question not exists - returning");
                return false;
            }

            findAnswer.setObject(1, entity.getQuestionId());
            findAnswer.setObject(2, entity.getText());
            var answer = findAnswer.executeQuery();
            LOGGER.debug("Checking if answer already exists with text {} and question ID {}", entity.getText(), entity.getQuestionId());
            if (answer.next()) {
                LOGGER.debug("Answer already exists - returning");
                return false;
            }

            save.setObject(1, entity.getQuestionId());
            save.setObject(2, entity.getText());
            save.setObject(3, entity.getIsCorrect());
            return save.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Error while saving answer", e);
            throw new DatabaseAccessException("Error while saving answer", e);
        }
    }

    private AnswerEntity buildAnswer(ResultSet resultSet) throws SQLException {
        return new AnswerEntity(
                resultSet.getLong("id"),
                resultSet.getInt("question_id"),
                resultSet.getString("text"),
                resultSet.getBoolean("is_correct")
        );
    }
}