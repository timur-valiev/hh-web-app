package ru.hh.school.social.impl.repositories;

import ru.hh.school.social.ddd.Repository;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.entities.Session;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 10.01.12
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 */
public interface SessionRepository extends Repository<Session> {
    public Session bySessionId(String sessionId) throws NoIdException;
    public void removeBySessionId(String sessionId) throws NoIdException;
}
