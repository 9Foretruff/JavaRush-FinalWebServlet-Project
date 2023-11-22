package com.adventurequest.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> findAll();

    Optional<T> findByUsername(K username);

    boolean delete(K username);

    boolean update(T entity);

    boolean save(T entity);
}