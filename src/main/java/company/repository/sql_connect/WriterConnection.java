package company.repository.sql_connect;

import company.controller.WriterController;
import company.model.Label;
import company.model.Writer;
import company.repository.WriterRepository;
import company.utils.ConfigParametersDB;
import company.utils.DatabaseConfigReader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WriterConnection implements WriterRepository {
    @Override
    public Writer getById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "SELECT * FROM writer WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
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
            statement.close();
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Writer> getAll() {
        List<Writer> allWriters = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM writer";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int idWriter = set.getInt("id");
                String firstName = set.getString("firstName");
                String lastName = set.getString("lastName");
                String labString = set.getString("posts");
                List list = List.of(labString);
                allWriters.add(new Writer(idWriter, firstName, lastName, list));
            }
            statement.close();
            set.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return allWriters;
    }

    @Override
    public Writer save(Writer writer) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String insertSql = "INSERT INTO writer(firstName,lastName,posts) VALUES (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertSql);
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getPosts().toString());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "UPDATE writer SET firstName = ?,lastName = ?,posts = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, writer.getFirstName());
            statement.setString(2, writer.getLastName());
            statement.setString(3, writer.getPosts().toString());
            statement.setInt(4, writer.getId());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            String sql = "DELETE FROM writer WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

}
