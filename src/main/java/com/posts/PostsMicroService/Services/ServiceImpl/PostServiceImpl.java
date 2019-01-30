package com.posts.PostsMicroService.Services.ServiceImpl;

import com.posts.PostsMicroService.DTO.ResponseDto;

//import com.posts.PostsMicroService.Entity.NestedPostComments;
import com.posts.PostsMicroService.Entity.Post;
//import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Repository.PostRepository;
import com.posts.PostsMicroService.Services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ResponseDto addPost(Post post) {

        ResponseDto responseDto = new ResponseDto();

        try {
            postRepository.insert(post);

            responseDto.setVariables(true, 200, "Posted Successfully.");
            //   responseDto.setCurrentId(post.getPostId());

        } catch (Exception ex) {

            responseDto.setVariables(false, 500, ex.getMessage());
        }

        return responseDto;
    }

    @Override
    public void editParentComments(Post post, String commentId,String description) {

        List<PostsComments> comments=post.getPostsComments();

        List<PostsComments> edittedList=new ArrayList<>();

        // PostsComments updateComment=null;

        for (PostsComments editComment:comments)
        {
            System.out.println(commentId);
            if(editComment.getCommentId()!=null && editComment.getCommentId().equalsIgnoreCase(commentId))
            {
                editComment.setDescription(description);
            }
            edittedList.add(editComment);
        }
//        updateComment.setDescription(description);
//        comments.add(updateComment);
        post.setPostsComments(edittedList);
        postRepository.save(post);


    }


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

//    @Override
//    public void editParentComments(Post post, String commentId){
//
//
//            postRepository.save(post);
//        }
@Override
public void addLikes(String postId, String userId) throws Exception {
    Post post= postRepository.findOne(postId);

    List<String> likes;
    if(post.getPostLikes()==null)
    {
        likes=new ArrayList<>();
    }
    else {
        likes = post.getPostLikes();
    }
    if(likes.contains(userId))
    {
        throw new Exception("Already Liked");
    }
    likes.add(userId);
    post.setPostLikes(likes);
    postRepository.save(post);
}

    @Override
    public void dislike(String postId, String userId) throws Exception {
        Post post=postRepository.findOne(postId);
        if(!post.getPostLikes().contains(userId))
            throw new Exception("No Likes for The User Found");
        post.getPostLikes().remove(userId);
        postRepository.save(post);

    }


    @Override
    public Boolean getLikeStatus(String postId, String userId) {
       return postRepository.findOne(userId).getPostLikes().contains(userId);

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
            }
        }


        return post;

    }
}
