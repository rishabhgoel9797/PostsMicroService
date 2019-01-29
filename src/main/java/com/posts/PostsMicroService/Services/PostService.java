package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;

import java.util.List;

public interface  PostService {
  void addPost(Post post);
    void deletePost(String postId);
    void editPost(Post post);
    Post getPostDetails(String postId);
<<<<<<< HEAD
    Post findOnePost(String postId);
    void deleteParentComments(Post post,String commentId);

    void editParentComments(Post post,String commentId);

   // Post addReplies(Post post,List<PostsComments> comments,String commentId,String userId,String reply);

=======
    void addLikes(String postId,String userId);
    void dislike(String postId,String userId);
    Boolean getLikeStatus(String postId,String userId);
>>>>>>> c16da8a5061b4a4cf389065c8a2520b4821e7bd4

}

