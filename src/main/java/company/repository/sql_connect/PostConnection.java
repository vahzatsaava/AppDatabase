package company.repository.sql_connect;

import company.model.Label;
import company.model.Post;
import company.repository.PostRepository;
import company.utils.ConfigParametersDB;
import company.utils.DatabaseConfigReader;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostConnection implements PostRepository {
    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        Time time1 = Time.valueOf(LocalTime.now());
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Gregor"));
        System.out.println(time.format(formatter));
        PostConnection connection = new PostConnection();

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        Time time2 = Time.valueOf(LocalTime.now());
        System.out.println(connection.getAll());
    }

    @Override
    public Post getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "SELECT * FROM post WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String content = set.getString("content");
                Time start = set.getTime("startTime");
                Time finish = set.getTime("finishTime");
                String posts = set.getString("posts");
                List labels = List.of(posts);
                return new Post(content, start, finish, labels);
            }
            set.close();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "SELECT * FROM post";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idWriter = set.getInt("id");
                String content = set.getString("content");
                Time start = set.getTime("startTime");
                Time finish = set.getTime("finishTime");
                String labels = set.getString("posts");
                List listLabels = List.of(labels);
                posts.add(new Post(idWriter,content, start, finish, listLabels));
            }
            statement.close();
            set.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post save(Post post) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "INSERT INTO post(content,startTime,finishTime,posts) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getContent());
            statement.setTime(2, post.getStart());
            statement.setTime(3, post.getFinish());
            statement.setString(4, post.getLabels().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public Post update(Post post) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "UPDATE post SET content = ?, startTime = ?,finishTime = ?,posts = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, post.getContent());
            statement.setTime(2, post.getStart());
            statement.setTime(3, post.getFinish());
            statement.setString(4, post.getLabels().toString());
            statement.setInt(5, post.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "DELETE FROM post WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
