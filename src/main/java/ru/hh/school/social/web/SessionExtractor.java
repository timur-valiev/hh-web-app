package ru.hh.school.social.web;

import ru.hh.school.social.impl.entities.Session;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 21.01.12
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
public interface SessionExtractor {
    public Session getCurrentSession();
}
