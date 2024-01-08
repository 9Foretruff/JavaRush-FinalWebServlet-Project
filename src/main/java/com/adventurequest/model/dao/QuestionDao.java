package com.adventurequest.model.dao;

import com.adventurequest.model.entity.QuestionEntity;
import com.adventurequest.model.exeption.DatabaseAccessException;
import com.adventurequest.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuestionDao implements Dao<String, QuestionEntity> {
    private static final QuestionDao INSTANCE = new QuestionDao();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionDao.class);
    private static final String FIND_ALL_SQL = """
                SELECT
                id,
                number_of_question,
                quest_id,
                text,
                background_question_photo,
                is_last_question
                FROM adventure_quest_schema.question
            """;
    private static final String DELETE_SQL = """
                DELETE
                FROM adventure_quest_schema.question
                WHERE id = ?
            """;
    private static final String SAVE_SQL = """
                INSERT INTO adventure_quest_schema.question(number_of_question, quest_id, text, background_question_photo, is_last_question)
                VALUES (? , ? , ? , ? , ?)
            """;
    private static final String FIND_QUESTION_BY_NUMBER_OF_QUESTION_AND_QUESTION_ID_SQL = """
                SELECT
                id,
                number_of_question,
                quest_id,
                text,
                background_question_photo,
                is_last_question
                FROM adventure_quest_schema.question
                WHERE number_of_question = ? AND  quest_id = ?
            """;
    private static final String CHECK_IF_QUESTION_HAVE_FINAL_QUESTION = """
                SELECT
                id,
                number_of_question,
                quest_id,
                text,
                background_question_photo,
                is_last_question
                FROM adventure_quest_schema.question
                WHERE is_last_question = true AND quest_id = ?
            """;
    private static final String FIND_QUESTION_BY_AUTHOR_SQL = """
                SELECT
                question.id,
                question.number_of_question,
                question.quest_id,
                question.text,
                question.background_question_photo,
                question.is_last_question
                FROM adventure_quest_schema.question
                JOIN adventure_quest_schema.quest quest ON quest.id = question.quest_id
                WHERE author LIKE ?
            """;
    private static final String FIND_QUESTION_BY_ID_SQL = """
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

    private QuestionDao() {
    }

    public static QuestionDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<QuestionEntity> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            var resultSet = preparedStatement.executeQuery();
            List<QuestionEntity> questions = new ArrayList<>();
            while (resultSet.next()) {
                questions.add(buildQuestion(resultSet));
            }
            return questions;
        } catch (SQLException e) {
            LOGGER.error("Error while finding all questions", e);
            throw new DatabaseAccessException("Error while finding all questions", e);
        }
    }

    public List<QuestionEntity> findByAuthor(String author) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_QUESTION_BY_AUTHOR_SQL)) {
            preparedStatement.setObject(1, author);
            var resultSet = preparedStatement.executeQuery();
            List<QuestionEntity> questions = new ArrayList<>();
            while (resultSet.next()) {
                questions.add(buildQuestion(resultSet));
            }
            return questions;
        } catch (SQLException e) {
            LOGGER.error("Error while finding questions by author", e);
            throw new DatabaseAccessException("Error while finding questions by author", e);
        }
    }

    public Optional<QuestionEntity> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_QUESTION_BY_ID_SQL)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                LOGGER.info("Found question with id :{}", id);
                return Optional.of(buildQuestion(resultSet));
            }

            return Optional.empty();

        } catch (SQLException e) {
            LOGGER.error("Error while finding questions by author", e);
            throw new DatabaseAccessException("Error while finding questions by author", e);
        }
    }

    @Override
    public boolean delete(String id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setObject(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error("Failed to delete question with id = {} due to database error", id, e);
            throw new DatabaseAccessException("Error while delete question", e);
        }
    }

    @Override
    public boolean save(QuestionEntity entity) {
        try (var connection = ConnectionManager.get();
             var save = connection.prepareStatement(SAVE_SQL);
             var findQuestion = connection.prepareStatement(FIND_QUESTION_BY_NUMBER_OF_QUESTION_AND_QUESTION_ID_SQL);
             var check = connection.prepareStatement(CHECK_IF_QUESTION_HAVE_FINAL_QUESTION)) {
            findQuestion.setObject(1, entity.getNumberOfQuestion());
            findQuestion.setObject(2, entity.getQuestId());
            var question = findQuestion.executeQuery();
            LOGGER.debug("Checking if question already exists with number {} and quest ID {}", entity.getNumberOfQuestion(), entity.getQuestId());
            if (question.next()) {
                LOGGER.debug("Question already exists - returning");
                return false;
            }
            LOGGER.debug("Checking that is no last questions already with quest ID {}", entity.getQuestId());

            check.setObject(1, entity.getQuestId());
            var findLast = check.executeQuery();
            if (findLast.next()) {
                var questionEntity = buildQuestion(findLast);
                if (entity.getIsLastQuestion()) {
                    LOGGER.debug("Last question already exists - returning");
                    return false;
                } else if (entity.getNumberOfQuestion() > questionEntity.getNumberOfQuestion()) {
                    LOGGER.debug("Question before last question - returning");
                    return false;
                }
            }
            save.setObject(1, entity.getNumberOfQuestion());
            save.setObject(2, entity.getQuestId());
            save.setObject(3, entity.getText());
            save.setObject(4, entity.getBackgroundQuestionPhoto());
            save.setObject(5, entity.getIsLastQuestion());
            var executed = save.executeUpdate();
            return executed > 0;
        } catch (SQLException e) {
            LOGGER.error("Error while saving question", e);
            throw new DatabaseAccessException("Error while saving question", e);
        }
    }

    private QuestionEntity buildQuestion(ResultSet resultSet) throws SQLException {
        return new QuestionEntity(
                resultSet.getLong("id"),
                resultSet.getInt("number_of_question"),
                resultSet.getLong("quest_id"),
                resultSet.getString("text"),
                resultSet.getBytes("background_question_photo"),
                resultSet.getBoolean("is_last_question")
        );
    }
}
