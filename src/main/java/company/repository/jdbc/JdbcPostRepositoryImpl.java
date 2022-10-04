package company.repository.jdbc;


import company.model.Post;
import company.repository.PostRepository;
import company.utils.JdbcUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPostRepositoryImpl implements PostRepository {

    @Override
    public Post getById(Integer id) {
        String sql = "SELECT * FROM post WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1,id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String content = set.getString("content");
                Date start = Date.valueOf(String.valueOf(set.getTimestamp("startTime")));
                Date finish = Date.valueOf(String.valueOf(set.getTimestamp("finishTime")));
                String posts = set.getString("posts");
                List labels = List.of(posts);
                return new Post(content, start, finish, labels);
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
        String sql = "SELECT * FROM post";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idWriter = set.getInt("id");
                String content = set.getString("content");
                Date start = Date.valueOf(String.valueOf(set.getDate("startTime")));
                Date finish = Date.valueOf(String.valueOf(set.getDate("finishTime")));
                String labels = set.getString("posts");
                List listLabels = List.of(labels);
                posts.add(new Post(idWriter,content, start, finish, listLabels));
            }
            set.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO post(content,startTime,finishTime,posts) VALUES (?,?,?,?)";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, post.getContent());
            statement.setDate(2, post.getStart());
            statement.setDate(3, post.getFinish());
            statement.setString(4, post.getLabels().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        String sql = "UPDATE post SET content = ?, startTime = ?,finishTime = ?,posts = ? WHERE id = ?";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, post.getContent());
            statement.setDate(2, post.getStart());
            statement.setDate(3, post.getFinish());
            statement.setString(4, post.getLabels().toString());
            statement.setInt(5, post.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM post WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
