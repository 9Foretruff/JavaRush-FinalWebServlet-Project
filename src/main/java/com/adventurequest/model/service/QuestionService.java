package com.adventurequest.model.service;

import com.adventurequest.model.dao.QuestionDao;
import com.adventurequest.model.entity.QuestionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


public class QuestionService {
    private static final QuestionService INSTANCE = new QuestionService();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionService.class);
    private static final boolean ADDING_QUESTION_SUCCESSFUL = true;
    private static final boolean ADDING_QUESTION_FAILED = false;
    private final QuestionDao questionDao = QuestionDao.getInstance();

    private QuestionService() {
    }

    public static QuestionService getInstance() {
        return INSTANCE;
    }

    public boolean addQuestion(Integer numberOfQuestion, Long questId, String text, byte[] backgroundQuestionPhoto, Boolean isLastQuestion) {
        QuestionEntity quest = new QuestionEntity(null, numberOfQuestion, questId, text, backgroundQuestionPhoto, isLastQuestion);
        var save = questionDao.save(quest);
        if (save) {
            LOGGER.info("Question with number of question {} and quest id {} added successfully.", numberOfQuestion, questId);
            return ADDING_QUESTION_SUCCESSFUL;
        } else {
            LOGGER.warn("Question with number of question {} and quest id {} already exists.", numberOfQuestion, questId);
            return ADDING_QUESTION_FAILED;
        }
    }

    public List<QuestionEntity> findAll() {
        return questionDao.findAll();
    }

    public List<QuestionEntity> findQuestionsByAuthor(String author) {
        return questionDao.findByAuthor(author);
    }

    public Optional<QuestionEntity> findQuestionById(Long id){
        return questionDao.findById(id);
    }

}