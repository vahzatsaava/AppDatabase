package company.controller;

import company.model.Label;
import company.repository.sql_connect.LabelConnection;

import java.util.List;

public class LabelController {
    private final LabelConnection labelConnection = new LabelConnection();

    public Label createLabel(Label label) {
        return labelConnection.save(new Label(label.getName()));
    }

    public List<Label> getLabels() {
        return labelConnection.getAll();
    }

    public Label update(Label name) {
        return labelConnection.update(name);
    }

    public void delete(Integer id) {
        labelConnection.deleteById(id);
    }

    public Label get(Integer id) {
        return labelConnection.getById(id);
    }
}
