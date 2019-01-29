package com.posts.PostsMicroService.Services.ServiceImpl;

<<<<<<< HEAD
import com.posts.PostsMicroService.DTO.ResponseDto;
=======
import com.posts.PostsMicroService.Entity.NestedPostComments;
>>>>>>> c5510ef31cbace3a70e60f3ec4c60583f85e0fbf
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
    public ResponseDto deletePost(String postId) {

        ResponseDto responseDto = new ResponseDto();

        try {
            postRepository.delete(postId);
            responseDto.setVariables(true, 200, "Deleted post successfully.");

        } catch (Exception ex){

            responseDto.setVariables(false, 500, "Unable to delete the post.");
        }

        return responseDto;
    }

    @Override
    public ResponseDto editPost(Post post) {

        ResponseDto responseDto = new ResponseDto();
        try {
            postRepository.save(post);

            responseDto.setVariables(true, 200, "Post edited successfully.");
        } catch (Exception ex){

            responseDto.setVariables(false, 500, "Server error. Please try again later.");
        }

        return responseDto;

    }

    @Override
    public Post getPostDetails(String postId) {
        return postRepository.findOne(postId);
    }

<<<<<<< HEAD
    public ResponseDto addPost(Post post){

        ResponseDto responseDto = new ResponseDto();

        try {
            postRepository.insert(post);

            responseDto.setVariables(true, 200, "Posted Successfully.");
            responseDto.setCurrentId(post.getPostId());

        } catch (Exception ex){

            responseDto.setVariables(false, 500, ex.getMessage());
        }

        return responseDto;
=======
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
>>>>>>> c5510ef31cbace3a70e60f3ec4c60583f85e0fbf
    }


}
