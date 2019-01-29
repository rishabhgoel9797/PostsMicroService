package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;

import java.util.List;

public interface  PostService {

    ResponseDto deletePost(String postId);
    ResponseDto editPost(Post post);
    Post getPostDetails(String postId);
    List<Post> getfeed(List<String> userIds);
    ResponseDto addPost(Post post);
    List<Post> getUserPost(String userId);

}
