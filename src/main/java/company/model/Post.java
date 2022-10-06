package company.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    private int id;
    private String content;
    private Date created;
    private Date updated;
    private int writer_id;
    private List<Label> labels;

    public Post(String content, Date created, Date updated,int writer_id) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.writer_id = writer_id;
    }

    public Post(int id, String content, Date created, Date updated,int writer_id, List<Label> labels) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.writer_id = writer_id;
        this.labels = labels;
    }

    public Post(String content, Date created, Date updated,int writer_id, List<Label> labels) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.writer_id = writer_id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public int getWriter_id() {
        return writer_id;
    }

    public void setWriter_id(int writer_id) {
        this.writer_id = writer_id;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", writer_id=" + writer_id +
                ", labels=" + labels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getId() == post.getId() && Objects.equals(getContent(), post.getContent()) && Objects.equals(getCreated(), post.getCreated()) && Objects.equals(getUpdated(), post.getUpdated()) && Objects.equals(getLabels(), post.getLabels());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getCreated(), getUpdated(), getLabels());
    }

}
