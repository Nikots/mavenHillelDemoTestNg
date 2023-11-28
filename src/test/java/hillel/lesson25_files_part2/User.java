package hillel.lesson25_files_part2;

import java.util.Objects;

public class User {

    // POJO - Plain Old Java Object
    String username;
    String identifier;
    String firstname;
    String lastname;

    public User(String username, String identifier, String firstname, String lastname) {
        this.username = username;
        this.identifier = identifier;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters
    public String getUsername() {
        return username;
    }

    // setters
    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return username + ";" + identifier + ";" + firstname + ";" + lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(identifier, user.identifier) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, identifier, firstname, lastname);
    }

}
