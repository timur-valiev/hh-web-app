package ru.hh.school.social.web.forms;

import ru.hh.school.social.impl.entities.UserInfo;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 12.01.12
 * Time: 12:34
 * To change this template use File | Settings | File Templates.
 */
public class PersonalInfoForm {
    private String fullName;
    private String studyPlaces;
    private String workPlaces;
    private String someInformation;
    public PersonalInfoForm(UserInfo sessionUser) {
        this.fullName = sessionUser.getFullName();
        this.studyPlaces = sessionUser.getStudyPlaces();
        this.workPlaces = sessionUser.getWorkPlaces();
        this.someInformation =sessionUser.getSomeInformation();
    }

    public PersonalInfoForm() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudyPlaces() {
        return studyPlaces;
    }

    public void setStudyPlaces(String studyPlaces) {
        this.studyPlaces = studyPlaces;
    }

    public String getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(String workPlaces) {
        this.workPlaces = workPlaces;
    }

    public String getSomeInformation() {
        return someInformation;
    }

    public void setSomeInformation(String someInformation) {
        this.someInformation = someInformation;
    }
}
