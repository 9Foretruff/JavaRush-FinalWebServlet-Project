package com.adventurequest.model.dao;

import com.adventurequest.model.entity.QuestionEntity;

import java.util.List;

public class QuestionDao implements Dao<String, QuestionEntity>{
    @Override
    public List<QuestionEntity> findAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(QuestionEntity entity) {
        return false;
    }

    @Override
    public boolean save(QuestionEntity entity) {
        return false;
    }
}
