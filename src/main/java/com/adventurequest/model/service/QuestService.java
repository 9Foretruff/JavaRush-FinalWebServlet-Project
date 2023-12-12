package com.adventurequest.model.service;

import com.adventurequest.model.dao.QuestDao;
import com.adventurequest.model.dao.UserDao;
import com.adventurequest.model.dto.UserDto;
import com.adventurequest.model.entity.DifficultyEnum;
import com.adventurequest.model.entity.QuestEntity;
import com.adventurequest.model.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean addQuest(String name, String description, byte[] quest_photo, DifficultyEnum difficulty , String author) {
        QuestEntity quest = new QuestEntity(null,name,description,quest_photo,difficulty,author);
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

}