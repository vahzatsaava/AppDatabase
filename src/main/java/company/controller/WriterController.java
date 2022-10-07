package company.controller;

import company.model.Writer;
import company.repository.WriterRepository;
import company.repository.jdbc.JdbcWriterRepositoryImpl;

import java.util.List;

public class WriterController {
    private final WriterRepository writerRepository;

    public WriterController() {
        writerRepository = new JdbcWriterRepositoryImpl();
    }
    public WriterController(WriterRepository writerRepository){
        this.writerRepository = writerRepository;
    }

    public Writer createWriter(Writer writer) {
        return writerRepository.save(writer);
    }

    public List<Writer> getWriters() {
        return writerRepository.getAll();
    }

    public Writer upDate(Writer writer) {
        return writerRepository.update(writer);
    }

    public Writer get(Integer id) {
        return writerRepository.getById(id);
    }

    public void delete(Integer id) {
        writerRepository.deleteById(id);
    }
}
