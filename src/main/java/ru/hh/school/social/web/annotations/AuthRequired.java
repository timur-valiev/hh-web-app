package ru.hh.school.social.web.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 14.01.12
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public @interface AuthRequired {
    String value() default "";
}
