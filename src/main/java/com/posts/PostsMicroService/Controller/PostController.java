package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
import com.posts.PostsMicroService.Services.PostService;
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
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto) {
        Post post = new Post();
        postDto.setTimestamp();
        BeanUtils.copyProperties(postDto, post);

        postService.addPost(post);
        return new ResponseEntity<String>("Added New Post", HttpStatus.CREATED);
    }

    @RequestMapping(value="/getPost/{id}" ,method = RequestMethod.GET)
    public Post getPostDetails(@PathVariable String id)
    {
        return postService.getPostDetails(id);
    }

    @RequestMapping(value ="/deleteProduct/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePost(@PathVariable String id)
    {
        postService.deletePost(id);
      return new ResponseEntity<String>("Deletd product",HttpStatus.OK);
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> editPost( @PathVariable String id, @RequestBody String description) {
       Post post=postService.getPostDetails(id);

       post.setDescription(description);
//       post.setPostId(id);
//       post.setCreatedBy(post.getCreatedBy());
        postService.editPost(post);
        return new ResponseEntity<String>("Edited ", HttpStatus.OK);
    }
}