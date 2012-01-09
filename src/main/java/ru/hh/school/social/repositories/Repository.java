package ru.hh.school.social.repositories;

import ru.hh.school.social.entities.Entity;
import ru.hh.school.social.exceptions.NoIdException;

public interface Repository<T extends Entity> {
    public void put(T entity);
    public T byId(Long id) throws NoIdException;
    public void remove(T entity);
    Iterable<T> all();

}
