package com.lcz.myutilapp.entity;

/**
 * desc TODO
 * Created by lcz on 2019/5/17.
 */
public class Contributor {

    public String login;
    public int contributions;

    public Contributor(String login, int contributions) {
        this.login = login;
        this.contributions = contributions;
    }
}
