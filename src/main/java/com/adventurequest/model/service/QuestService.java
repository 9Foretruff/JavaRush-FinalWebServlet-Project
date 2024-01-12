package com.adventurequest.model.service;

import com.adventurequest.model.dao.QuestDao;
import com.adventurequest.model.entity.DifficultyEnum;
import com.adventurequest.model.entity.QuestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class QuestService {
    private static final QuestService INSTANCE = new QuestService();
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestService.class);
    private static final boolean ADDING_QUEST_SUCCESSFUL = true;
    private static final boolean ADDING_QUEST_FAILED = false;
    private final QuestDao questDao = QuestDao.getInstance();

    private QuestService() {
    }

    public static QuestService getInstance() {
        return INSTANCE;
    }

    public boolean addQuest(String name, String description, byte[] quest_photo, DifficultyEnum difficulty, String author) {
        QuestEntity quest = new QuestEntity(null, name, description, quest_photo, difficulty, author);
        var save = questDao.save(quest);
        if (save) {
            LOGGER.info("Quest with name {} added successfully.", name);
            return ADDING_QUEST_SUCCESSFUL;
        } else {
            LOGGER.warn("Quest with name {}: already exists.", name);
            return ADDING_QUEST_FAILED;
        }
    }

    public List<QuestEntity> findAll() {
        return questDao.findAll();
    }

    public List<QuestEntity> findQuestsByAuthor(String author) {
        return questDao.findByAuthor(author);
    }

    public List<QuestEntity> findPublishedQuestsWithPage(Long page) {
        Long offset = 0L;
        if (page > 1) {
            offset = page * 10 - 10;
        }
        return questDao.findPublishedWithOffset(offset);
    }

    public Optional<QuestEntity> findQuestById(Long id) {
        return questDao.findById(id);
    }

    public boolean changeQuestName(Long questId, String newName , String author) {
        return questDao.changeName(questId, newName , author);
    }

    public boolean changeQuestDescription(Long questId, String newDescription) {
        return questDao.changeDescription(questId,newDescription);
    }
}