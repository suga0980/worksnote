package jp.co.chitose.classes;

import jp.co.chitose.enums.Role;

import java.io.Serializable;

public class Account implements Serializable {

    private Integer id;
    private String name;
    private String mailAddress;
    private String password;
    private Role role;
    private String realName;
    private String realNameRuby;

    public Account() {

    }

    public Account(String name, String mailAddress, String password, Role role, String realName, String realNameRuby) {
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
        this.role = role;
        this.realName = realName;
        this.realNameRuby = realNameRuby;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealNameRuby() {
        return realNameRuby;
    }

    public void setRealNameRuby(String realNameRuby) {
        this.realNameRuby = realNameRuby;
    }
}
