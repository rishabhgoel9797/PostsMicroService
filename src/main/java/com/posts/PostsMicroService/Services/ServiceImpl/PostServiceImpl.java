package com.posts.PostsMicroService.Services.ServiceImpl;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Repository.PostRepository;
import com.posts.PostsMicroService.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

        } catch (Exception ex) {

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
        } catch (Exception ex) {

            responseDto.setVariables(false, 500, "Server error. Please try again later.");
        }

        return responseDto;

    }

    @Override
    public Post getPostDetails(String postId) {

        return postRepository.findOne(postId);
    }
    @Override
    public List<Post> getUserPost(String userId) {

        return postRepository.findPostByUserId(userId);
    }
    @Override
    public List<Post> getfeed(List<String> userIds) {

        List<Post> post=new ArrayList<Post>();
        List<Post> post1=null;
        for(String id:userIds){
         post1=postRepository.findPostByUserId(id);
         for(Post post2:post1){
             post.add(post2);
         } }


            return post;

    }

    public ResponseDto addPost(Post post) {

        ResponseDto responseDto = new ResponseDto();

        try {
            postRepository.insert(post);

            responseDto.setVariables(true, 200, "Posted Successfully.");

        } catch (Exception ex) {

            responseDto.setVariables(false, 500, ex.getMessage());
        }

        return responseDto;
    }


}
