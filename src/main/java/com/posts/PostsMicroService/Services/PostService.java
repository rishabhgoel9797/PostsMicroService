package com.posts.PostsMicroService.Services;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.Entity.PostsComments;

import java.util.List;

public interface  PostService {

    ResponseDto deletePost(String postId);
    ResponseDto editPost(Post post);
    Post getPostDetails(String postId);
    ResponseDto addPost(Post post);

    void deleteParentComments(Post post,String commentId);

    void editParentComments(Post post,String commentId,String description);
//    Post addReplies(Post post,List<PostsComments> comments,String commentId,String userId,String reply);
   List<Post> getfeed(List<String> userIds);
    void addLikes(String postId,String userId) throws Exception;
    void dislike(String postId,String userId) throws Exception;
    Boolean getLikeStatus(String postId,String userId);
    List<Post> getUserPost(String userId);

}

