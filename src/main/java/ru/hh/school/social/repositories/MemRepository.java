package ru.hh.school.social.repositories;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import ru.hh.school.social.entities.Entity;
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

    public Iterable<T> all() {
        return identityMap.values();
    }
}
