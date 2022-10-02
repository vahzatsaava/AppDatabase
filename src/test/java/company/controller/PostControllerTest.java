package company.controller;

import company.model.Label;
import company.model.Post;
import company.repository.jdbc.JdbcPostRepositoryImpl;
import company.repository.jdbc.JdbcPostRepositoryImplTest;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    @Mock
    private JdbcPostRepositoryImpl jdbcPostRepository;
    @InjectMocks
    private PostController postController;

    private static final List<Label> labels = new ArrayList<>();

    static {
        labels.add(new Label("abc"));
    }

    private final Post post = new Post("Home", new Timestamp(100), new Timestamp(200), labels);

    @Test
    public void createPost_Successful() {
        when(jdbcPostRepository.save(any())).thenReturn(post);
        assertEquals(postController.createPost(any()).getContent(),"Home");
    }
    @Test
    public void createPost_UnSuccessful() {
        when(jdbcPostRepository.save(any())).thenReturn(post);
        assertNotEquals(postController.createPost(any()).getContent(),"Homes");
    }


    @Test
    public void getPosts_Successful() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
         when(jdbcPostRepository.getAll()).thenReturn(posts);
         assertEquals(postController.getPosts(),posts);
    }
    @Test
    public void getPosts_UnSuccessful() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(jdbcPostRepository.getAll()).thenReturn(posts);
        assertNotEquals(postController.getPosts(),null);
    }

    @Test
    public void updatePost_Successful() {
        when(jdbcPostRepository.update(any())).thenReturn(post);
        assertEquals(postController.updatePost(any()).getContent(),"Home");
    }
    @Test
    public void updatePost_UnSuccessful() {
        when(jdbcPostRepository.update(any())).thenReturn(post);
        assertNotEquals(postController.updatePost(any()).getContent(),null);
    }

    @Test
    public void getPost_Successful() {
        when(jdbcPostRepository.getById(1)).thenReturn(post);
        assertEquals(postController.getPost(1).getContent(),"Home");
    }
    @Test
    public void getPost_UnSuccessful() {
        when(jdbcPostRepository.getById(1)).thenReturn(post);
        assertNotEquals(postController.getPost(1).getContent(),null);
    }
}