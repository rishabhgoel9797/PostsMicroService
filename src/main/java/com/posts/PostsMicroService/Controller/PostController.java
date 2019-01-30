package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.DTO.FeedWrapper;
import com.posts.PostsMicroService.DTO.PostWrapper;
import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Feeds;
import com.posts.PostsMicroService.Entity.NestedPostComments;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
import com.posts.PostsMicroService.Entity.PostsComments;
import com.posts.PostsMicroService.Services.PostService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Value("${userProfile_url}")
    String userProfileUrl;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDto addPost(@RequestBody PostDto postDto) {
        Post post = new Post();
        BeanUtils.copyProperties(postDto, post);
        post.setDate();
        if(post.getType().equalsIgnoreCase("text"))
            post.setUrl("");
        post.setCreatedBy(post.getUserId());
        if(post.getDescription()==null)
            post.setDescription("");
        if(post.getPostLikes()==null)
            post.setPostLikes(Collections.emptyList());
        if(post.getPostsComments()==null)
            post.setPostsComments(Collections.emptyList());
        ResponseDto responseDto = postService.addPost(post);
        return responseDto;
    }

    @RequestMapping(value = "/getPost/{id}", method = RequestMethod.GET)
    public Post getPostDetails(@PathVariable String id) {
        Post post=postService.getPostDetails(id);

        return postService.getPostDetails(id);
    }

    @RequestMapping(value = "/deletePost/{id}", method = RequestMethod.DELETE)
    public ResponseDto deletePost(@PathVariable String id) {
        ResponseDto responseDto = postService.deletePost(id);
        return responseDto;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseDto editPost(@PathVariable String id, @RequestBody PostDto postDto) {
        String description = postDto.getDescription();
        Post post = postService.getPostDetails(id);
        post.setDescription(description);

        return postService.editPost(post);
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public ResponseDto sharePost(@RequestBody JSONObject sharePost) {

        String postId = (String) sharePost.get("postId");
        String userId = (String) sharePost.get("destinationId");

        Post sourcePost = postService.getPostDetails(postId);
        Post newPost = new Post();
        try {
            newPost.setUserId(userId);
            newPost.setDate();
            newPost.setCreatedBy(sourcePost.getCreatedBy());
            newPost.setDescription(sourcePost.getDescription());

            if (null != sourcePost.getType()) {
                newPost.setType(sourcePost.getType());
                newPost.setUrl(sourcePost.getUrl());
            }
        } catch (NullPointerException ex) {
            //Do nothing.
        }

        ResponseDto responseDto = postService.addPost(newPost);
        return responseDto;
    }

    @RequestMapping(value = "/findByUserId/{id}", method = RequestMethod.GET)
    public PostWrapper findByName(@PathVariable String id){
        PostWrapper postWrapper=new PostWrapper();
        postWrapper.setPostList(postService.getUserPost(id));
        return postWrapper;
}

    @RequestMapping(value = "/userWall/{id}", method = RequestMethod.GET)
         public FeedWrapper userWall(@PathVariable String id){
        RestTemplate restTemplate1 = new RestTemplate();
         List<Feeds> feeds =new ArrayList<>();
        String getURL = userProfileUrl + "follow/getFollowerDetails/" + id;

        ResponseEntity<List<Follower>> responseEntity = restTemplate1.exchange(getURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<Follower>>() {
        });
        List<Follower> followers = responseEntity.getBody();
        List<String> followerId=new ArrayList<>();
        for(Follower follower:followers)
        {
           followerId.add (follower.getUserId());
        }
       followerId.add(id);
        List<Post> post = postService.getfeed(followerId);

        for(Post post1:post){
            for(Follower follower:followers){
                if(follower.getUserId().equals(post1.getUserId()))
                {
                    Feeds feed =new Feeds();
                    feed.setPost(post1);
                    feed.setUserId(follower.getUserId());
                    feed.setImage(follower.getUserImageURL());
                    feed.setUsername(follower.getUsername());
                    feeds.add(feed);
                }

            }

        }
        FeedWrapper feedWrapper=new FeedWrapper();
        feedWrapper.setFeedsList(feeds);
        return feedWrapper;
    }

    @RequestMapping(value = "/addComment/{postId}",method = RequestMethod.PUT)
    public ResponseDto addComment(@PathVariable String postId,@RequestParam String userId,@RequestParam String userName,@RequestParam String description)
    {
        Post post=postService.getPostDetails(postId);
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
        postsComments.setUsername(userName);
        postsComments.setDescription(description);

        allPostsComments.add(postsComments);
        System.out.println(allPostsComments.toString());
        post.setPostsComments(allPostsComments);

        return postService.editPost(post);
    }

    @RequestMapping(value = "addReply/{postId}/{commentId}",method = RequestMethod.PUT)
    public ResponseDto addReply(@PathVariable String postId,@PathVariable String commentId,@RequestParam String userId,@RequestParam String userName, @RequestParam String reply)
    {
        Post post=postService.getPostDetails(postId);
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
            nestedPostComments.setNestedCommentId();
            nestedPostComments.setUserId(userId);
            nestedPostComments.setReply(reply);
            nestedPostComments.setUsername(userName);

            replies.add(nestedPostComments);
            getComment.setNestedPostComments(replies);


            return postService.editPost(post);
    }

    @RequestMapping(value = "deleteComment/{postId}/{commentId}",method = RequestMethod.PUT)
    public ResponseDto deleteParentComment(@PathVariable String postId,@PathVariable String commentId)
    {
        Post post=postService.getPostDetails(postId);
        return postService.deleteParentComments(post,commentId);

    }

    @RequestMapping(value = "editComment/{postId}/{commentId}",method = RequestMethod.PUT)
    public ResponseDto editComment(@PathVariable String postId,@PathVariable String commentId,@RequestParam String description)
    {
        Post post=postService.getPostDetails(postId);

        return postService.editParentComments(post,commentId,description);

    }

    @RequestMapping(value = "deleteNestedComment/{postId}/{commentId}/{nestedCommentId}",method = RequestMethod.PUT)
    public ResponseDto deleteNestedComment(@PathVariable String postId,@PathVariable String commentId,@PathVariable String nestedCommentId)
    {
        Post post=postService.getPostDetails(postId);

        return postService.deleteNestedComment(post,commentId,nestedCommentId);

    }

    @RequestMapping(value="/like/{postId}/{userId}",method = RequestMethod.POST)
    public ResponseDto likePost(@PathVariable String postId,@PathVariable String userId)
    {   ResponseDto responseDto=new ResponseDto();
        try {
            if (postId.isEmpty() || userId.isEmpty())
                throw new Exception("Wrong Parameter");
                postService.addLikes(postId, userId);
                responseDto.setVariables(true, HttpServletResponse.SC_CREATED,"Liked");
                return responseDto;
        }catch (Exception e)
        {
            responseDto.setVariables(false,HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
            return responseDto;
        }
    }

    @RequestMapping(value="/dislike/{postId}/{userId}",method = RequestMethod.DELETE)
    public ResponseDto dislikePost(@PathVariable String postId,@PathVariable String userId)
    {  ResponseDto responseDto=new ResponseDto();
        try{
            if (postId.isEmpty() || userId.isEmpty())
                throw new Exception("Wrong Parameter");
            postService.dislike(postId,userId);
            responseDto.setVariables(true, HttpServletResponse.SC_CREATED,"Liked");
            return responseDto;
        }catch (Exception e)
        {
            responseDto.setVariables(false,HttpServletResponse.SC_BAD_REQUEST,e.getMessage());
            return responseDto;
        }
    }

}
