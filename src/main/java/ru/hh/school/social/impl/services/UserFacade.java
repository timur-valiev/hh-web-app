package ru.hh.school.social.impl.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.impl.entities.UserInfo;
import ru.hh.school.social.exceptions.NoEmailException;
import ru.hh.school.social.exceptions.NoIdException;
import ru.hh.school.social.impl.repositories.UserRepository;

@Component
public class UserFacade {
    private final UserRepository users;

    @Autowired
    public UserFacade(UserRepository users) {
        this.users = users;
    }

    public Iterable<UserInfo> listUsers() {
        List<UserInfo> users = new ArrayList<UserInfo>();
        for (User user : this.users.all())
            users.add(new UserInfo(user));
        return users;
    }

    public UserInfo getByEmail(String query) throws NoEmailException {
        return users.byEmail(query).getInfo();
    }

    public UserInfo getById(Long id) throws NoIdException {
        return users.byId(id).getInfo();
    }

    public UserInfo byEmail(String email) {
        try {
            return users.byEmail(email).getInfo();
        } catch (NoEmailException e) {
            return null;
        }
    }

    public Iterable<UserInfo> all() {
        ArrayList<UserInfo> info = new ArrayList<UserInfo>();
        for (User user: users.all()) {
            info.add(user.getInfo());
        }
        return info;
    }
}
