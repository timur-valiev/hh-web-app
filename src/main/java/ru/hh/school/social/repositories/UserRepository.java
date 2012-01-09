package ru.hh.school.social.repositories;

import ru.hh.school.social.entities.User;
import ru.hh.school.social.exceptions.NoEmailException;

public interface UserRepository extends Repository<User> {
    User byEmail(String email) throws NoEmailException;
    Long getId(String email) throws NoEmailException;

}
