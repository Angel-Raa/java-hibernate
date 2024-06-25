package com.github.angel.repository;

import java.util.List;

public interface GenericRepository <T, ID> {
    List<T> findAll();
    T findById(ID id);
    void save(T entity);
    void deleteById(ID id);
    void update(T entity, ID id);
}
