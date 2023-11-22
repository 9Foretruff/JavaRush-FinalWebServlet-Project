package com.adventurequest.model.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, T> {
    List<T> findAll();

    Optional<T> findByUsername(K Username);

    boolean delete(K Username);

    void update(T entity);

    T save(T entity);
}