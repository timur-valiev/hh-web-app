package ru.hh.school.social.ddd;

import ru.hh.school.social.ddd.Entity;
import ru.hh.school.social.exceptions.NoIdException;

public interface Repository<T extends Entity> {
    public void put(T entity);
    public T byId(Long id) throws NoIdException;
    public void remove(T entity);
    public void removeById(long id) throws NoIdException;
    public int getCount();
    Iterable<T> all();

}
