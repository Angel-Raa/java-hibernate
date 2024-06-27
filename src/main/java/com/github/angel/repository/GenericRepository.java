package com.github.angel.repository;

import java.util.List;

public interface GenericRepository <T, K>  {
    List<T> findAll();
    T findById(K k);
    void save(T entity);
    void deleteById(K k);
    void update(T entity, K k);
}
