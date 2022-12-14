package company.repository.jdbc;

import company.model.Label;
import company.repository.LabelRepository;
import company.utils.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    @Override
    public Label getById(Integer id) {
        String sql = "SELECT * FROM labels WHERE id = ?";

        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql))
         {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int idFromDB = set.getInt("id");
                String name = set.getString("name");
                return new Label(idFromDB, name);
            }
            set.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List getAll() {
        List<Label> list = new ArrayList<>();
            String sql = "SELECT * FROM labels";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            ResultSet set = statement.executeQuery("SELECT * FROM labels");
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                list.add(new Label(id, name));
            }
            set.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    @Override
    public Label save(Label label) {
        String sql = "INSERT INTO labels(name) VALUES (?)";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, label.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }


    @Override
    public Label update(Label label) {
        String sql = "UPDATE labels SET name = ? WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setString(1, label.getName());
            statement.setInt(2, label.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return label;
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM labels WHERE id = ?";
        try (PreparedStatement statement = JdbcUtils.getPrepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
