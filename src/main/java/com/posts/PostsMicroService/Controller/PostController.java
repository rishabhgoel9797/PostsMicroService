package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.Entity.NestedPostComments;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Services.PostService;
import javafx.geometry.Pos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        Post post = new Post();
        postDto.setDate();
        BeanUtils.copyProperties(postDto, post);
        System.out.println(post.getDate());
        if(post.getPostLikes()==null)
            post.setPostLikes(Collections.emptyList());
        if(post.getPostsComments()==null)
            post.setPostsComments(Collections.emptyList());

        postService.addPost(post);
        return new ResponseEntity<String>("Added New Post", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getPost/{id}", method = RequestMethod.GET)
    public Post getPostDetails(@PathVariable String id) {
        Post post = postService.getPostDetails(id);
        return post;
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return new ResponseEntity<String>("Deleted post", HttpStatus.OK);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editPost(@PathVariable String id, @RequestBody PostDto postDto) {
        String description = postDto.getDescription();
        Post post = postService.getPostDetails(id);
        post.setDescription(description);
        postService.editPost(post);
        return new ResponseEntity<String>("Edited ", HttpStatus.OK);
    }

    @RequestMapping(value = "/addComment/{postId}",method = RequestMethod.PUT)
    public Post addComment(@PathVariable String postId,@RequestParam String userId,@RequestParam String description)
    {
        Post post=postService.findOnePost(postId);
        List<PostsComments> allPostsComments;
        if(post.getPostsComments()==null)
        {
            allPostsComments=new ArrayList<>();
        }
        else
        {
            allPostsComments=post.getPostsComments();
        }
        PostsComments postsComments=new PostsComments();
        postsComments.setCommentId();
        postsComments.setUserId(userId);
        postsComments.setDescription(description);

        allPostsComments.add(postsComments);
        System.out.println(allPostsComments.toString());
        post.setPostsComments(allPostsComments);

        postService.editPost(post);
        return post;
    }

    @RequestMapping(value = "addReply/{postId}/{commentId}",method = RequestMethod.PUT)
    public void addReply(@PathVariable String postId,@PathVariable String commentId,@RequestParam String userId, @RequestParam String reply)
    {
        Post post=postService.findOnePost(postId);
        List<PostsComments> comments=post.getPostsComments();

        List<NestedPostComments> replies;

        PostsComments getComment=null;

            for (PostsComments comment:comments)
            {
                if(comment.getCommentId()!=null)
                {
                    if(comment.getCommentId().equalsIgnoreCase(commentId))
                    {
                        getComment=comment;
                    }
                }
            }

            if(getComment.getNestedPostComments()==null)
            {
                replies=new ArrayList<>();
            }
            else
            {
                replies=getComment.getNestedPostComments();
            }
        NestedPostComments nestedPostComments=new NestedPostComments();
            nestedPostComments.setUserId(userId);
            nestedPostComments.setReply(reply);
            nestedPostComments.setNestedCommentId();

            replies.add(nestedPostComments);
            getComment.setNestedPostComments(replies);


            postService.editPost(post);
    }

    @RequestMapping(value = "deleteComment/{postId}/{commentId}",method = RequestMethod.PUT)
    public String deleteParentComment(@PathVariable String postId,@PathVariable String commentId)
    {
        Post post=postService.findOnePost(postId);
        postService.deleteParentComments(post,commentId);

        return "Deleted";
    }

    @RequestMapping(value = "editComment/{postId}/{commentId}",method = RequestMethod.PUT)
    public String editComment(@PathVariable String postId,@PathVariable String commentId,@RequestParam String description)
    {
        Post post=postService.findOnePost(postId);
        postService.editParentComments(post,commentId,description);
        return "Edited";
    }

    @RequestMapping(value = "deleteNestedComment/{postId}/{commentId}/{nestedCommentId}",method = RequestMethod.PUT)
    public String deleteNestedComment(@PathVariable String postId,@PathVariable String commentId,@PathVariable String nestedCommentId)
    {
        Post post=postService.findOnePost(postId);
        postService.deleteNestedComment(post,commentId,nestedCommentId);
        return "deleted";
    }


    @RequestMapping(value="/like/{postId}/{userId}",method = RequestMethod.POST)
    public ResponseEntity<String> likePost(@PathVariable String postId,@PathVariable String userId)
    {   try {
        if (postId.isEmpty() || userId.isEmpty())
            return new ResponseEntity<>("Wrong Parameter", HttpStatus.BAD_REQUEST);
        postService.addLikes(postId, userId);
        return new ResponseEntity<>("Liked", HttpStatus.ACCEPTED);
    }catch (Exception e)
    {
        return new ResponseEntity<>("Wrong Parameter", HttpStatus.BAD_REQUEST);
    }
    }

    @RequestMapping(value="/like/{postId}/{userId}",method = RequestMethod.DELETE)
    public ResponseEntity<String> dislikePost(@PathVariable String postId,@PathVariable String userId)
    {
        try{
            if (postId.isEmpty() || userId.isEmpty())
                return new ResponseEntity<>("Wrong Parameter", HttpStatus.BAD_REQUEST);
            postService.dislike(postId,userId);
            return new ResponseEntity<>("disLiked", HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            return new ResponseEntity<>("Wrong Parameter", HttpStatus.BAD_REQUEST);
        }
    }


}
