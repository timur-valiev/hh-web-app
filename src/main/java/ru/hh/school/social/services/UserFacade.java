package ru.hh.school.social.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hh.school.social.entities.User;
import ru.hh.school.social.entities.UserInfo;
import ru.hh.school.social.exceptions.EmailAlreadyBoundException;
import ru.hh.school.social.exceptions.IncorrectPasswordException;
import ru.hh.school.social.exceptions.NoEmailException;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.repositories.UserRepository;

@Component
public class UserFacade {
    private final UserRepository users;
    private final UserService userService;

    @Autowired
    public UserFacade(UserRepository users, UserService userService) {
        this.users = users;
        this.userService = userService;
    }

    public Iterable<UserInfo> listUsers() {
        List<UserInfo> users = new ArrayList<UserInfo>();
        for (User user : this.users.all())
            users.add(new UserInfo(user));
        return users;
    }
    
    public UserInfo getInfoByEmail(String email) throws NoEmailException {
        return new UserInfo(users.byEmail(email));
    }

    public UserInfo getByEmail(String query) throws NoEmailException {
        return users.byEmail(query).getInfo();
    }

    public UserInfo getById(Long id) throws NoIdException {
        return users.byId(id).getInfo();
    }
}
