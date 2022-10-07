package company.controller;

import company.model.Label;
import company.model.Post;
import company.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostController postController;

    private static final List<Label> labels = new ArrayList<>();

    static {
        labels.add(new Label("abc"));
    }

    private final Post post = new Post("Home", new Date(100), new Date(200),4);

    @Test
    public void createPost_Successful() {
        when(postRepository.save(any())).thenReturn(post);
        assertEquals(postController.createPost(any()).getContent(),"Home");
    }
    @Test
    public void createPost_UnSuccessful() {
        when(postRepository.save(any())).thenReturn(post);
        assertNotEquals(postController.createPost(any()).getContent(),"Homes");
    }


    @Test
    public void getPosts_Successful() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
         when(postRepository.getAll()).thenReturn(posts);
         assertEquals(postController.getPosts(),posts);
    }
    @Test
    public void getPosts_UnSuccessful() {
        List<Post> posts = new ArrayList<>();
        posts.add(post);
        when(postRepository.getAll()).thenReturn(posts);
        assertNotEquals(postController.getPosts(),null);
    }

    @Test
    public void updatePost_Successful() {
        when(postRepository.update(any())).thenReturn(post);
        assertEquals(postController.updatePost(any()).getContent(),"Home");
    }
    @Test
    public void updatePost_UnSuccessful() {
        when(postRepository.update(any())).thenReturn(post);
        assertNotEquals(postController.updatePost(any()).getContent(),null);
    }

    @Test
    public void getPost_Successful() {
        when(postRepository.getById(1)).thenReturn(post);
        assertEquals(postController.getPost(1).getContent(),"Home");
    }
    @Test
    public void getPost_UnSuccessful() {
        when(postRepository.getById(1)).thenReturn(post);
        assertNotEquals(postController.getPost(1).getContent(),null);
    }
}