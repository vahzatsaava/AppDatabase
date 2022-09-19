package company.controller;

import company.model.Writer;
import company.repository.sql_connect.WriterConnection;

import java.util.List;

public class WriterController {
    private final WriterConnection connection = new WriterConnection();

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
