package ru.hh.school.social.impl.repositories;

import org.springframework.stereotype.Component;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.entities.Session;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 10.01.12
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
@Component
public class MemSessionRepository extends MemRepository<Session> implements SessionRepository{
    private final Map<String, Long> sessionIdentityMap = new LinkedHashMap<String, Long>();


    @Override
    public Session bySessionId(String sessionId) throws NoIdException {
        if (!sessionIdentityMap.containsKey(sessionId))
            throw new NoIdException(sessionId);
        return this.byId(sessionIdentityMap.get(sessionId));
    }

    @Override
    public void removeBySessionId(String sessionId) throws NoIdException {
        if (!sessionIdentityMap.containsKey(sessionId))
            throw new NoIdException(sessionId);
        this.removeById(sessionIdentityMap.get(sessionId));
    }
    
    @Override public void put(Session session) {
        session.setId(++counter);
        identityMap.put(session.getId(), session);
        SecureRandom random = new SecureRandom();
        session.setSessionId(new BigInteger(130, random).toString(32));
        sessionIdentityMap.put(session.getSessionId(),session.getId());
    }
}
