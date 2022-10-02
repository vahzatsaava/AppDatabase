package company.repository.jdbc;

import company.model.Writer;
import company.repository.WriterRepository;
import company.utils.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcWriterRepositoryImpl implements WriterRepository {
    @Override
    public Writer getById(Integer id) {
        String sql = "SELECT * FROM writer WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int idFromDb = set.getInt("id");
                String firstName = set.getString("firstName");
                String lastName = set.getString("lastName");
                String posts = set.getString("posts");
                List labels = List.of(posts);

                return new Writer(idFromDb, firstName, lastName, labels);
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
        String sql = "SELECT * FROM writer";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idWriter = set.getInt("id");
                String firstName = set.getString("firstName");
                String lastName = set.getString("lastName");
                String labString = set.getString("posts");
                List list = List.of(labString);
                allWriters.add(new Writer(idWriter, firstName, lastName, list));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWriters;
    }

    @Override
    public Writer save(Writer writer) {
        String insertSql = "INSERT INTO writer(firstName,lastName,posts) VALUES (?,?,?)";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(insertSql)) {
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getPosts().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        String sql = "UPDATE writer SET firstName = ?,lastName = ?,posts = ? WHERE id = ?";

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
        String sql = "DELETE FROM writer WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
