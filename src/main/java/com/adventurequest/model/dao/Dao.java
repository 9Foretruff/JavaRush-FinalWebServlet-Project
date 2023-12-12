package com.adventurequest.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> findAll();

    boolean delete(K id);

    boolean update(T entity);

    boolean save(T entity);
}