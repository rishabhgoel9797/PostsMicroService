package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.Entity.Post;

public interface  PostService {
  void addPost(Post post);
    void deletePost(String postId);
    void editPost(Post post);
    Post getPostDetails(String postId);
    void addLikes(String postId,String userId);
    void dislike(String postId,String userId);
    Boolean getLikeStatus(String postId,String userId);

}
