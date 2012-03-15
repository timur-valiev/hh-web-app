package ru.hh.school.social.impl.repositories;

import ru.hh.school.social.ddd.Repository;
import ru.hh.school.social.impl.entities.User;
import ru.hh.school.social.exceptions.NoEmailException;

public interface UserRepository extends Repository<User> {
    User byEmail(String email) throws NoEmailException;
    Long getId(String email) throws NoEmailException;
}
