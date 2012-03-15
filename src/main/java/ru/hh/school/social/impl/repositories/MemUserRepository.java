package ru.hh.school.social.impl.repositories;

import org.springframework.stereotype.Component;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.exceptions.NoEmailException;
import ru.hh.school.social.exceptions.NoIdException;

import java.util.HashMap;

@Component
public class MemUserRepository extends MemRepository<User> implements UserRepository {
    private final HashMap<String,Long> emailMap = new HashMap<String, Long>();

    public User byEmail(String email) throws NoEmailException {
        if (emailMap.containsKey(email))
            try {
                return byId(emailMap.get(email));
            } catch (NoIdException e) {
                e.printStackTrace();
                return null;
            }
        else
            throw(new NoEmailException(email));
    }

    public Long getId(String email) throws NoEmailException {
        if (emailMap.containsKey(email))
            return emailMap.get(email);
        else
            throw(new NoEmailException(email));
    }

    @Override
    public void put(User user){
        user.setId(++counter);
        emailMap.put(user.getEmail(),user.getId());
        identityMap.put(user.getId(),user);
    }
}
