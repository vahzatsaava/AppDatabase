package company.repository.jdbc;

import company.model.Post;
import company.model.Writer;
import company.repository.WriterRepository;
import company.utils.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    public static void main(String[] args) {
        JdbcWriterRepositoryImpl jdbcWriterRepository = new JdbcWriterRepositoryImpl();
        System.out.println(jdbcWriterRepository.getAll());
    }
    @Override
    public Writer getById(Integer id) {
        String sql = "select * from writers INNER join posts p on writers.id = p.writer_id WHERE writers.id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int idFromDb = set.getInt("id");
                String firstName = set.getString("firstName");
                String lastName = set.getString("lastName");

                List<Post> posts = new ArrayList<>();
                String content = set.getString("content");
                Date created = set.getDate("created");
                Date updated = set.getDate("updated");
                int writerId = set.getInt("writerId");
                posts.add(new Post(content,created,updated,writerId));

                return new Writer(idFromDb, firstName, lastName,posts);
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> allWriters = new ArrayList<>();
        String sql = "SELECT * FROM writers INNER JOIN posts ON writers.id = posts.writer_id";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idFromDb = set.getInt("id");
                String firstName = set.getString("firstName");
                String lastName = set.getString("lastName");

                List<Post> posts = new ArrayList<>();
                String content = set.getString("content");
                Date created = set.getDate("created");
                Date updated = set.getDate("updated");
                int writerId = set.getInt("writer_id");
                posts.add(new Post(content,created,updated,writerId));
                allWriters.add(new Writer(idFromDb, firstName, lastName,posts));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWriters;
    }

    @Override
    public Writer save(Writer writer) {
        String insertSql = "INSERT INTO writers(firstName,lastName) VALUES (?,?)";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(insertSql)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        String sql = "UPDATE writers SET firstName = ?,lastName = ?,posts = ? WHERE id = ?";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getPosts().toString());
            statement.setInt(4, writer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM writers WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
