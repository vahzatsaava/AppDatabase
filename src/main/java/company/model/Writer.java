package company.model;

import java.util.List;
import java.util.Objects;

public class Writer {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<Label> posts;

    public Writer(Integer id, String firstName, String lastName, List<Label> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(String firstName, String lastName, List<Label> posts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Label> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", posts=" + posts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Writer)) return false;
        Writer writer = (Writer) o;
        return Objects.equals(getId(), writer.getId()) && Objects.equals(getFirstName(), writer.getFirstName()) && Objects.equals(getLastName(), writer.getLastName()) && Objects.equals(getPosts(), writer.getPosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPosts());
    }
}
