package com.posts.PostsMicroService.Services.ServiceImpl;

import com.posts.PostsMicroService.Entity.NestedPostComments;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Repository.PostRepository;
import com.posts.PostsMicroService.Services.PostService;
import javafx.geometry.Pos;
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
<<<<<<< HEAD
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
=======
    public void addLikes(String postId, String userId) {
        Post post= postRepository.findOne(postId);
        post.getPostLikes().add(userId);
        postRepository.save(post);
    }

    @Override
    public void dislike(String postId, String userId) {
        Post post=postRepository.findOne(postId);
        post.getPostLikes().remove(userId);
        postRepository.save(post);

    }

    @Override
    public Boolean getLikeStatus(String postId, String userId) {
       return postRepository.findOne(userId).getPostLikes().contains(userId);

>>>>>>> c16da8a5061b4a4cf389065c8a2520b4821e7bd4
    }


}
