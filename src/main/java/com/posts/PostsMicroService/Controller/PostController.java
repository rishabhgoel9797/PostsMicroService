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

import java.util.ArrayList;
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

    @RequestMapping(value = "editComment/",method = RequestMethod.PUT)
    public String editComment()
    {
        return "Edited";
    }

}
