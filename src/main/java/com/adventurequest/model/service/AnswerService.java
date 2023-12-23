package com.adventurequest.model.service;

import com.adventurequest.model.dao.AnswerDao;
import com.adventurequest.model.entity.AnswerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AnswerService {
    private static final AnswerService INSTANCE = new AnswerService();
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerService.class);
    private static final boolean ADDING_ANSWER_SUCCESSFUL = true;
    private static final boolean ADDING_ANSWER_FAILED = false;
    private final AnswerDao answerDao = AnswerDao.getInstance();

    private AnswerService() {
    }

    public static AnswerService getInstance() {
        return INSTANCE;
    }

    public boolean addAnswer(Integer questionId, String text,Boolean isCorrect) {
        AnswerEntity answer = new AnswerEntity(null, questionId, text, isCorrect);
        var save = answerDao.save(answer);
        if (save) {
            LOGGER.info("Answer with question id: {} and text: {} added successfully.", questionId, text);
            return ADDING_ANSWER_SUCCESSFUL;
        } else {
            LOGGER.warn("Failed to add answer for question id: {} and text: {}", questionId, text);
            return ADDING_ANSWER_FAILED;
        }
    }

    public List<AnswerEntity> findAll() {
        return answerDao.findAll();
    }

}