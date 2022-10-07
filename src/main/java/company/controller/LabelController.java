package company.controller;

import company.model.Label;
import company.repository.LabelRepository;
import company.repository.jdbc.JdbcLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository;

    public LabelController() {
        labelRepository = new JdbcLabelRepositoryImpl();
    }

    public LabelController(LabelRepository labelRepository) {

        this.labelRepository = labelRepository;
    }

    public Label createLabel(Label label) {
        return labelRepository.save(new Label(label.getName()));
    }

    public List<Label> getLabels() {
        return labelRepository.getAll();
    }

    public Label update(Label name) {
        return labelRepository.update(name);
    }

    public void delete(Integer id) {
        labelRepository.deleteById(id);
    }

    public Label get(Integer id) {
        return labelRepository.getById(id);
    }
}
