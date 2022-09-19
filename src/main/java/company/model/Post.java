package company.model;

import java.sql.Time;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String content;
    private Time start;
    private Time finish;
    private List<Label> labels;

    public Post(String content,Time start, Time finish, List<Label> labels) {
        this.content = content;
        this.start = start;
        this.finish = finish;
        this.labels = labels;
    }

    public Post(int id, String content,Time start,Time finish, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.start = start;
        this.finish = finish;
        this.labels = labels;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getFinish() {
        return finish;
    }

    public void setFinish(Time finish) {
        this.finish = finish;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", start=" + start +
                ", finish=" + finish +
                ", labels=" + labels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getId() == post.getId() && Objects.equals(getContent(), post.getContent()) && Objects.equals(getStart(), post.getStart()) && Objects.equals(getFinish(), post.getFinish()) && Objects.equals(getLabels(), post.getLabels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getStart(), getFinish(), getLabels());
    }

}