package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;

import java.util.List;

public interface  PostService {
  ResponseDto addPost(Post post);
    ResponseDto deletePost(String postId);
    ResponseDto editPost(Post post);
    Post getPostDetails(String postId);

    Post findOnePost(String postId);
    void deleteParentComments(Post post,String commentId);

    void editParentComments(Post post,String commentId,String description);

    void deleteNestedComment(Post post,String commentId, String nestedCommentId);

   // Post addReplies(Post post,List<PostsComments> comments,String commentId,String userId,String reply);

    void addLikes(String postId,String userId) throws Exception;
    void dislike(String postId,String userId) throws Exception;
    Boolean getLikeStatus(String postId,String userId);


}

