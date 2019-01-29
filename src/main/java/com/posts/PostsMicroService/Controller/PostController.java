package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
import com.posts.PostsMicroService.Services.PostService;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseDto addPost(@RequestBody PostDto postDto) {
        Post post = new Post();
        postDto.setDate();
        BeanUtils.copyProperties(postDto, post);
        ResponseDto responseDto = postService.addPost(post);

        return responseDto;
    }

    @RequestMapping(value="/getPost" ,method = RequestMethod.GET)
    public Post getPostDetails(@RequestParam String id)
    {
        return postService.getPostDetails(id);
    }

    @RequestMapping(value ="/deletePost/{id}",method = RequestMethod.DELETE)
    public ResponseDto deletePost(@PathVariable String id)
    {
        ResponseDto responseDto = postService.deletePost(id);
        return responseDto;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseDto editPost( @PathVariable String id,@RequestBody PostDto postDto) {
        String description=postDto.getDescription();
        Post post=postService.getPostDetails(id);
        post.setDescription(description);

        return postService.editPost(post);
    }

    @RequestMapping(value = "/share", method = RequestMethod.POST)
    public ResponseDto sharePost(@RequestBody JSONObject sharePost){

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
        } catch (NullPointerException ex){
            //Do nothing.
        }

        ResponseDto responseDto = postService.addPost(newPost);
        return responseDto;
    }


    //Display posts on the wall of the user. By UserId.
    @RequestMapping(value = "/userWall", method = RequestMethod.GET)
    public ResponseDto userWall(@RequestParam String userId){

        ResponseDto responseDto = new ResponseDto();
        return responseDto;
    }
}
