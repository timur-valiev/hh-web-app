package ru.hh.school.social.impl.repositories;

import java.util.LinkedHashMap;
import java.util.Map;

import ru.hh.school.social.ddd.Entity;
import ru.hh.school.social.ddd.Repository;
import ru.hh.school.social.exceptions.NoIdException;

public abstract class MemRepository<T extends Entity> implements Repository<T> {
    protected  final Map<Long, T> identityMap = new LinkedHashMap<Long, T>();
    protected  Long counter = 0L;

    public void put(T entity) {
        entity.setId(++counter);
        identityMap.put(entity.getId(), entity);
    }

    public T byId(Long id) throws NoIdException {
        if (! identityMap.containsKey(id))
            throw new NoIdException(id);
        return identityMap.get(id);
    }

    public void remove(T entity) {
        identityMap.remove(entity.getId());
    }

    public void removeById(long id) throws NoIdException {
        if (identityMap.containsKey(id)) {
            identityMap.remove(id);
        } else {
            throw new NoIdException(id);
        }
    }

    @Override
    public int getCount() {
        return identityMap.size();
    }

    public Iterable<T> all() {
        return identityMap.values();
    }
}
