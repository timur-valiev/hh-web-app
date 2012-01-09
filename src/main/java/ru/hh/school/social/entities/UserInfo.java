package ru.hh.school.social.entities;

import ru.hh.school.social.repositories.MemRepository;

public class UserInfo {
    private Long id;
    private final String email;
    private String fullName;
    private final MemRepository<Recommendation> selfRecommendations;
    private final MemRepository<Recommendation> inbox;
    private final MemRepository<Recommendation> queries;

    public UserInfo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.fullName = "";
        this.selfRecommendations = new MemRepository<Recommendation>(){};
        this.inbox = new MemRepository<Recommendation>() {};
        this.queries = new MemRepository<Recommendation>() {};
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
