package company.repository.sql_connect;

import company.model.Label;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;


public class LabelConnectionTest extends TestCase {

    @Mock
    LabelConnection connection;

    public LabelConnectionTest() {
        connection = Mockito.mock(LabelConnection.class);
    }


    @Test
    public void testGetById() {
        Mockito.when(connection.getById(1)).thenReturn(new Label("Frenk"));
        assertEquals(connection.getById(1), new Label("Frenk"));
        Mockito.when(connection.getById(0)).thenReturn(null);
        assertNull(connection.getById(0));
    }
    @Test(expected = NullPointerException.class)
    public void testGetByIdNotNull(){
        Mockito.when(connection.getById(1)).thenThrow(NullPointerException.class);
    }

    @Test
    public void testGetAll() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("ABC"));
        Mockito.when(connection.getAll()).thenReturn(labels);
        assertEquals(connection.getAll(), labels);
    }
    @Test(expected = NullPointerException.class)
    public void testGetAllNotNull() {
        Mockito.when(connection.getAll()).thenThrow(NullPointerException.class);
    }

    @Test
    public void testSave() {
        Mockito.when(connection.save(new Label("ABC"))).thenReturn(new Label("ABC"));
        assertEquals(connection.save(new Label("ABC")), new Label("ABC"));

    }
    @Test(expected = NullPointerException.class)
    public void testSaveNotNull() {
        Mockito.when(connection.save(new Label("ABC"))).thenThrow(NullPointerException.class);
    }

    @Test
    public void testUpdate() {
        Mockito.when(connection.update(new Label(1, "ABC"))).thenReturn(new Label("ABC"));
        assertEquals(connection.update(new Label(1, "ABC")), new Label("ABC"));

    }
    @Test(expected = NullPointerException.class)
    public void testUpdateNotNull() {
        Mockito.when(connection.update(new Label(1, "ABC"))).thenThrow(NullPointerException.class);

    }



}