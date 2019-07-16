package by.davydenko.petbook.entity;

public class User extends Person {

    private String email;
    private int phoneNumber;
    private String password;
    private String login;

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
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


    public String getNamee() {

        return "asdasd ";
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

    @Override
    public String toString() {
        String s = new String();
        s = getName() + getLogin();
        return s;
    }
}
