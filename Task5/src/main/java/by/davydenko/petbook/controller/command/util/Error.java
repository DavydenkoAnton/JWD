package by.davydenko.petbook.controller.command.util;

public final class Error {
    private static final Error instance = new Error();
    private String login;
    private String newLogin;
    private String newLoginRepeat;
    private String password;
    private String newPassword;
    private String newPasswordRepeat;

    private Error() {
    }

    public static Error getInstance() {
        return instance;
    }

    public void clean() {
        login = null;
        newLogin = null;
        newLoginRepeat = null;
        password = null;
        newPassword = null;
        newPasswordRepeat = null;
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

    public String getNewPasswordRepeat() {
        return newPasswordRepeat;
    }

    public void setNewPasswordRepeat(String newPasswordRepeat) {
        this.newPasswordRepeat = newPasswordRepeat;
    }

    public String getNewLogin() {
        return newLogin;
    }

    public void setNewLogin(String newLogin) {
        this.newLogin = newLogin;
    }

    public String getNewLoginRepeat() {
        return newLoginRepeat;
    }

    public void setNewLoginRepeat(String newLoginRepeat) {
        this.newLoginRepeat = newLoginRepeat;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}

