package company.controller;

import company.model.Writer;
import company.repository.jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterController {
    private JdbcWriterRepositoryImpl connection;

    public WriterController() {
        connection = new JdbcWriterRepositoryImpl();
    }

    public Writer createWriter(Writer writer) {
        return connection.save(writer);
    }

    public List<Writer> getWriters() {
        return connection.getAll();
    }

    public Writer upDate(Writer writer) {
        return connection.update(writer);
    }

    public Writer get(Integer id) {
        return connection.getById(id);
    }

    public void delete(Integer id) {
        connection.deleteById(id);
    }
}
