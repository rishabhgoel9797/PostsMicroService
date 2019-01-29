package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
import com.posts.PostsMicroService.Services.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        postService.addPost(post);
        return new ResponseEntity<String>("Added New Post", HttpStatus.CREATED);
    }

    @RequestMapping(value="/getPost/{id}" ,method = RequestMethod.GET)
    public Post getPostDetails(@PathVariable String id)
    {
      Post post=postService.getPostDetails(id);
        return post;
    }

    @RequestMapping(value ="/deletePost/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable String id)
    {
        postService.deletePost(id);
      return new ResponseEntity<String>("Deleted post",HttpStatus.OK);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> editPost( @PathVariable String id,@RequestBody PostDto postDto) {
String description=postDto.getDescription();
        Post post=postService.getPostDetails(id);
       post.setDescription(description);
        postService.editPost(post);
        return new ResponseEntity<String>("Edited ", HttpStatus.OK);
    }
    @RequestMapping(value="/getFeeds/{id}" ,method = RequestMethod.GET)
    public Post getFeedDetails(@PathVariable String id)
    {

        Post post=postService.getPostDetails(id);
        return post;
    }
}
