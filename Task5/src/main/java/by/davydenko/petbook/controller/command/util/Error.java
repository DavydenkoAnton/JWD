package by.davydenko.petbook.controller.command.util;

public final class Error {
    private static final Error instance = new Error();
    private String login;
    private String password;
    private String passwordCheck;

    private Error() {
    }

    public static Error getInstance() {
        return instance;
    }

    public void clean() {
        login = null;
        password = null;
        passwordCheck = null;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
