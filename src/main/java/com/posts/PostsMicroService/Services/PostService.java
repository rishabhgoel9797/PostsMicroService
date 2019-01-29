package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;

import java.util.List;

public interface  PostService {
  void addPost(Post post);
    void deletePost(String postId);
    void editPost(Post post);
    Post getPostDetails(String postId);
    Post findOnePost(String postId);
    void deleteParentComments(Post post,String commentId);

    void editParentComments(Post post,String commentId);

   // Post addReplies(Post post,List<PostsComments> comments,String commentId,String userId,String reply);


}

