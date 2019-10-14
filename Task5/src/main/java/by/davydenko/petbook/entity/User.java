package by.davydenko.petbook.entity;

import static by.davydenko.petbook.entity.Role.USER;

public class User extends Person {


    private String login;
    private String password;
    private Role role;

    public User() {
        role=USER;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

}
