package company.controller;

import company.model.Label;
import company.model.Writer;
import company.repository.jdbc.JdbcWriterRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WriterControllerTest {
    @Mock
    private JdbcWriterRepositoryImpl writerRepository;

    @InjectMocks
    private WriterController controller;

    private static final List<Label> labels = new ArrayList<>();
    static {
        labels.add(new Label("abc"));
    }
    private final Writer writer = new Writer("Roberto","Carlos",labels);
    @Test
    public void createWriter_Successful() {
        when(writerRepository.save(any())).thenReturn(writer);
        assertEquals("Roberto",controller.createWriter(any()).getFirstName());
    }
    @Test
    public void createWriter_UnSuccessful() {
        when(writerRepository.save(any())).thenReturn(writer);
        assertNotEquals(null,controller.createWriter(any()).getFirstName());
    }


    @Test
    public void getWriters_Successful() {
        List<Writer> writers = new ArrayList<>();
        writers.add(writer);
        when(writerRepository.getAll()).thenReturn(writers);
        assertEquals(controller.getWriters(),writers);
    }
    @Test
    public void getWriters_UnSuccessful() {
        List<Writer> writers = new ArrayList<>();
        writers.add(writer);
        when(writerRepository.getAll()).thenReturn(writers);
        assertNotEquals(controller.getWriters(),null);
    }

    @Test
    public void upDate_Successful() {
        when(writerRepository.update(any())).thenReturn(writer);
        assertEquals("Roberto",controller.upDate(any()).getFirstName());
    }
    @Test
    public void upDate_UnSuccessful() {
        when(writerRepository.update(any())).thenReturn(writer);
        assertNotEquals("Carlos",controller.upDate(any()).getFirstName());
    }

    @Test
    public void get_Successful() {
        when(writerRepository.getById(1)).thenReturn(writer);
        assertEquals("Roberto",controller.get(1).getFirstName());
    }
    @Test
    public void get_UnSuccessful() {
        when(writerRepository.getById(1)).thenReturn(writer);
        assertNotEquals("Carlos",controller.get(1).getFirstName());
    }
}