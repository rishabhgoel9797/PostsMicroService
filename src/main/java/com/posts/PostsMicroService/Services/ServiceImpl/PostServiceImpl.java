package com.posts.PostsMicroService.Services.ServiceImpl;

import com.posts.PostsMicroService.Entity.NestedPostComments;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Repository.PostRepository;
import com.posts.PostsMicroService.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Post findOnePost(String postId) {
        return postRepository.findOne(postId);
    }

    @Override
    public void deleteParentComments(Post post,String commentId) {
        List<PostsComments> comments=post.getPostsComments();


        for(PostsComments singleComment:comments)
        {
            if(singleComment.getCommentId().equalsIgnoreCase(commentId))
            {
                comments.remove(singleComment);
            }
        }

        post.setPostsComments(comments);

        postRepository.save(post);
    }

    @Override
    public void editParentComments(Post post, String commentId) {


        postRepository.save(post);
    }


}
