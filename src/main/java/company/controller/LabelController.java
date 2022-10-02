package company.controller;

import company.model.Label;
import company.repository.jdbc.JdbcLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private  JdbcLabelRepositoryImpl jdbcLabelRepositoryImpl;

    public LabelController() {
        jdbcLabelRepositoryImpl = new JdbcLabelRepositoryImpl();
    }

    public Label createLabel(Label label) {
        return jdbcLabelRepositoryImpl.save(new Label(label.getName()));
    }

    public List<Label> getLabels() {
        return jdbcLabelRepositoryImpl.getAll();
    }

    public Label update(Label name) {
        return jdbcLabelRepositoryImpl.update(name);
    }

    public void delete(Integer id) {
        jdbcLabelRepositoryImpl.deleteById(id);
    }

    public Label get(Integer id) {
        return jdbcLabelRepositoryImpl.getById(id);
    }
}
