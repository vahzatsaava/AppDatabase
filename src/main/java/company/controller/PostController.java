package company.controller;

import company.model.Post;
import company.repository.sql_connect.PostConnection;

import java.util.List;

public class PostController {
    private final PostConnection connection = new PostConnection();

    public Post createPost(Post post) {
        return connection.save(post);
    }

    public List<Post> getPosts() {
        return connection.getAll();
    }

    public Post updatePost(Post post) {
        return connection.update(post);
    }

    public Post getPost(Integer id) {
        return connection.getById(id);
    }

    public void deletePostFromDb(Integer id) {
        connection.deleteById(id);
    }
}
