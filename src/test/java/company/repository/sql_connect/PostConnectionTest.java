package company.repository.sql_connect;

import company.model.Label;
import company.model.Post;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PostConnectionTest extends TestCase {
    @Mock
    PostConnection connection;

    public PostConnectionTest() {
        connection = Mockito.mock(PostConnection.class);
    }

    @Test
    public void testGetById() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        Mockito.when(connection.getById(1)).thenReturn(new Post("fff", new Time(200), new Time(200), labels));
        assertTrue(connection.getById(1).getLabels().stream()
                .filter(i -> i.getName()
                        .equals("Garry"))
                .findFirst()
                .toString()
                .matches("(.*)Garry(.*)"));
        assertEquals(connection.getById(1).getContent(), "fff");
    }

    @Test(expected = NullPointerException.class)
    public void testGetByIdException() {
        Mockito.when(connection.getById(2)).thenThrow(NullPointerException.class);
    }

    @Test
    public void testGetAll() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("dddd", new Time(200), new Time(250), labels));
        Mockito.when(connection.getAll()).thenReturn(posts);
        assertEquals(connection.getAll(), posts);
    }

    @Test(expected = NullPointerException.class)
    public void testGetAllException() {
        Mockito.when(connection.getAll()).thenThrow(NullPointerException.class);
    }

    @Test
    public void testSave() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        Post post = new Post("fff", new Time(200), new Time(200), labels);
        Mockito.when(connection.save(post)).thenReturn(post);
        assertEquals(connection.save(post), post);
        assertEquals(connection.save(post).getContent(), "fff");

    }

    @Test(expected = NullPointerException.class)
    public void testSaveException() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        Post post = new Post("fff", new Time(200), new Time(200), labels);
        Mockito.when(connection.save(post)).thenThrow(NullPointerException.class);
    }

    @Test
    public void testUpdate() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        Post post = new Post("fff", new Time(200), new Time(200), labels);
        Mockito.when(connection.update(post)).thenReturn(post);
        assertEquals(connection.update(post), post);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateException() {
        List<Label> labels = new ArrayList<>();
        labels.add(new Label("Garry"));
        Post post = new Post("fff", new Time(200), new Time(200), labels);
        Mockito.when(connection.update(post)).thenThrow(NullPointerException.class);
    }
}