package company.repository.sql_connect;

import company.model.Label;
import company.repository.LabelRepository;
import company.utils.ConfigParametersDB;
import company.utils.DatabaseConfigReader;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LabelConnection implements LabelRepository {

    @Override
    public Label getById(Integer id) {

        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD));
        ) {
            String sql = "SELECT * FROM label WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int idFromDB = set.getInt("id");
                String name = set.getString("name");
                return new Label(idFromDB, name);
            }
            statement.close();
            set.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List getAll() {
        List<Label> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM label");
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                list.add(new Label(id, name));
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public Label save(Label label) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO label(name) VALUES (?)");
            statement.setString(1, label.getName());
            statement.executeUpdate();

            statement.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return label;
    }


    @Override
    public Label update(Label label) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            PreparedStatement statement = connection.prepareStatement("UPDATE label SET name = ? WHERE id = ?");
            statement.setString(1, label.getName());
            statement.setInt(2, label.getId());
            statement.executeUpdate();

            statement.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        try (Connection connection = DriverManager.getConnection(DatabaseConfigReader.getConfig(ConfigParametersDB.DATABASE), DatabaseConfigReader.getConfig(ConfigParametersDB.USER), DatabaseConfigReader.getConfig(ConfigParametersDB.PASSWORD))) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM label WHERE id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
