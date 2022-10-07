package company.controller;

import company.model.Post;
import company.repository.PostRepository;
import company.repository.jdbc.JdbcPostRepositoryImpl;

import java.util.List;

public class PostController {
    private final PostRepository postRepository;

    public PostController() {
        postRepository = new JdbcPostRepositoryImpl();
    }
    public PostController (PostRepository postRepository){
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.getAll();
    }

    public Post updatePost(Post post) {
        return postRepository.update(post);
    }

    public Post getPost(Integer id) {
        return postRepository.getById(id);
    }

    public void deletePostFromDb(Integer id) {
        postRepository.deleteById(id);
    }
}
