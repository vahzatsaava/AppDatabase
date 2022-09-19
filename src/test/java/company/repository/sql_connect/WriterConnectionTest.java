package company.repository.sql_connect;

import company.model.Label;
import company.model.Writer;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;


public class WriterConnectionTest extends TestCase {
    @Mock
    WriterConnection writerConnection;

    public WriterConnectionTest() {
        writerConnection = Mockito.mock(WriterConnection.class);
    }

    @Test
    public void testGetById() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        Mockito.when(writerConnection.getById(1)).thenReturn(new Writer("ggg", "fff", writers));
        assertEquals(writerConnection.getById(1), new Writer("ggg", "fff", writers));
    }

    @Test(expected = NullPointerException.class)
    public void testGetByIdNotNull() {
        Mockito.when(writerConnection.getById(0)).thenThrow(NullPointerException.class);
    }

    @Test
    public void testGetAll() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        List<Writer> list = new ArrayList<>();
        list.add(new Writer("ggg", "fff", writers));
        Mockito.when(writerConnection.getAll()).thenReturn(list);
        assertEquals(writerConnection.getAll(), list);
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllNotNull() {
        Mockito.when(writerConnection.getAll()).thenThrow(NullPointerException.class);
    }

    @Test
    public void testSave() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        Mockito.when(writerConnection.save(new Writer("ggg", "fff", writers))).thenReturn(new Writer("ggg", "fff", writers));
        assertEquals(writerConnection.save(new Writer("ggg", "fff", writers)), new Writer("ggg", "fff", writers));
    }

    @Test(expected = NullPointerException.class)
    public void testSaveNotNull() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        Mockito.when(writerConnection.save(new Writer("ggg", "fff", writers))).thenThrow(NullPointerException.class);

    }

    @Test
    public void testUpdate() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        Mockito.when(writerConnection.update(new Writer("ggg", "fff", writers))).thenReturn(new Writer("ggg", "fff", writers));
        assertEquals(writerConnection.update(new Writer("ggg", "fff", writers)), new Writer("ggg", "fff", writers));
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateNotNull() {
        List<Label> writers = new ArrayList<>();
        writers.add(new Label("frenk"));
        writers.add(new Label("ogu"));
        Mockito.when(writerConnection.save(new Writer("ggg", "fff", writers))).thenThrow(NullPointerException.class);
    }

}