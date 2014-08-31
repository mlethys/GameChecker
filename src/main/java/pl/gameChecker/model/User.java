package pl.gameChecker.model;

/**
 *
 * @author mlethys
 */
public class User {
    private String name;
    private String password;
    private String registerDate;
    private String mail;
    private final String DATE_OF_BIRTH;

    public User(String name, String password, String mail, String dateOfBirth) {
        this.name = name;
        this.password = password;
        this.DATE_OF_BIRTH = dateOfBirth;
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDateOfBirth() {
        return DATE_OF_BIRTH;
    }
    

}
