package com.adventurequest.model.dao;

import com.adventurequest.model.entity.AnswerEntity;

import java.util.List;

public class AnswerDao implements Dao<String, AnswerEntity>{
    @Override
    public List<AnswerEntity> findAll() {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public boolean update(AnswerEntity entity) {
        return false;
    }

    @Override
    public boolean save(AnswerEntity entity) {
        return false;
    }
}