package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;

public interface  PostService {

    ResponseDto deletePost(String postId);
    ResponseDto editPost(Post post);
    Post getPostDetails(String postId);
    ResponseDto addPost(Post post);

}
