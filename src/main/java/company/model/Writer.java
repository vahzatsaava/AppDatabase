package company.model;

import java.util.List;
import java.util.Objects;

public class Writer {
    private Integer id;
    private final String firstName;
    private final String lastName;
    private List<Post> posts;

    public Writer(Integer id, String firstName, String lastName, List<Post> posts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
    }

    public Writer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                " firstName='" + firstName + '\'' +
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
