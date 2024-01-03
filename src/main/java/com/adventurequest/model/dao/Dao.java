package com.adventurequest.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> findAll();

    boolean delete(K id);

    boolean save(T entity);
}