package ru.hh.school.social.impl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.exceptions.EmailAlreadyBoundException;
import ru.hh.school.social.exceptions.IncorrectPasswordException;
import ru.hh.school.social.exceptions.NoEmailException;
import ru.hh.school.social.impl.repositories.UserRepository;

@Component
public class UserService {

    private final UserRepository users;

    @Autowired
    public UserService(UserRepository users) {
        this.users = users;
    }

    public User registerUser(String email, String password, String fullName) throws EmailAlreadyBoundException {
        User existing = null;
        try {
            existing = users.byEmail(email);
            throw new EmailAlreadyBoundException(email);
        } catch (NoEmailException e) {
            User user = new User(email, password, fullName);
            users.put(user);
            return user;
        }
    }

    public boolean checkPassword(String email, String password) throws NoEmailException, IncorrectPasswordException {
        if( !(users.byEmail(email).checkPassword(password)))
            throw (new IncorrectPasswordException(email,password) );
        return true;
    }
}
