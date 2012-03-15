package ru.hh.school.social.impl.entities;

public class UserInfo {
    private Long id;
    private final String email;
    private String fullName;
    private String studyPlaces;
    private String workPlaces;
    private String someInformation;

    public UserInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = "";
        this.studyPlaces = "";
        this.workPlaces = "";
        this.someInformation = "";
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
