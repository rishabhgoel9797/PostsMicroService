package com.posts.PostsMicroService.Services.ServiceImpl;

import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Repository.PostRepository;
import com.posts.PostsMicroService.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public void addPost(Post post) {
        postRepository.insert(post);

    }

    @Override
    public void deletePost(String postId) {
postRepository.delete(postId);
    }

    @Override
    public void editPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostDetails(String postId) {
        return postRepository.findOne(postId);
    }
}
