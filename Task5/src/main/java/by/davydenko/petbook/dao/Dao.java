package by.davydenko.petbook.dao;

import by.davydenko.petbook.entity.Entity;

public interface Dao<T extends Entity> {
    Integer create(T entity);

    T read(Integer identity);

    void update(T entity);

    void delete(Integer identity);
}
