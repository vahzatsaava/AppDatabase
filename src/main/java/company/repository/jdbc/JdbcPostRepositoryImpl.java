package company.repository.jdbc;


import company.model.Label;
import company.model.Post;
import company.repository.PostRepository;
import company.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {
    public static void main(String[] args) {
        JdbcPostRepositoryImpl jdbcPostRepository = new JdbcPostRepositoryImpl();
        System.out.println(jdbcPostRepository.getAll());
    }

    @Override
    public Post getById(Integer id) {
        String sql = "SELECT * FROM posts INNER JOIN post_labels ON posts.id = post_labels.post_id INNER JOIN labels ON labels.id = post_labels.label_id WHERE posts.id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String content = set.getString("content");
                Date start = Date.valueOf(String.valueOf(set.getDate("created")));
                Date finish = Date.valueOf(String.valueOf(set.getDate("updated")));
                int writerId = set.getInt("writer_id");
                int labelId = set.getInt("label_id");
                String name = set.getString("name");
                List<Label> labels = new ArrayList<>();
                labels.add(new Label(labelId,name));

                return new Post(content, start, finish,writerId,labels);
            }
            set.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts INNER JOIN post_labels ON posts.id = post_labels.post_id INNER JOIN labels ON labels.id = post_labels.label_id ";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idWriter = set.getInt("id");
                String content = set.getString("content");
                Date start = Date.valueOf(String.valueOf(set.getDate("created")));
                Date finish = Date.valueOf(String.valueOf(set.getDate("updated")));
                int writerId = set.getInt("writer_id");
                int labelId = set.getInt("label_id");
                String name = set.getString("name");
                List<Label> labels = new ArrayList<>();
                labels.add(new Label(labelId,name));
                posts.add(new Post(idWriter,content, start, finish,writerId,labels));
            }
            set.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO posts (content,created,updated,writer_id) VALUES (?,?,?,?)";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, post.getContent());
            statement.setDate(2, post.getCreated());
            statement.setDate(3, post.getUpdated());
            statement.setInt(4,post.getWriter_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        String sql = "UPDATE posts SET content = ?, created = ?,updated = ?, WHERE id = ?";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, post.getContent());
            statement.setDate(2, post.getCreated());
            statement.setDate(3, post.getUpdated());
            statement.setInt(4, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
