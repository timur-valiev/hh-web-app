package ru.hh.school.social.web.forms;

/**
 * Created by IntelliJ IDEA.
 * User: Тимур
 * Date: 09.01.12
 * Time: 8:47
 * To change this template use File | Settings | File Templates.
 */
public class SearchForm {
    private String name;
    private String mail;
    private String study;
    private String work;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    private String info;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
