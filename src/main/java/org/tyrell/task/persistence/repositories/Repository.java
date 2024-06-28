package org.tyrell.task.persistence.repositories;

import java.util.List;

public interface Repository <T> {

    List<T> findAll();

    T findById(Long id);

    T persist(T entity);

    T update(T entity);

    void delete(Long id);

}
