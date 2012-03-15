package ru.hh.school.social.impl.entities;

import ru.hh.school.social.ddd.Entity;

public class User extends Entity {
    private String email;
    private String password;
    private UserInfo info;
    
    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.info = new UserInfo(this);
        info.setFullName(fullName);
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.info = new UserInfo(this);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo getInfo() {
        return info;
    }
    @Override
    public void setId(Long id){
        this.id = id;
        this.info.setId(id);
    }

    public Boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
