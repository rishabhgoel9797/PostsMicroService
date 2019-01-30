package com.posts.PostsMicroService.Controller;

import com.posts.PostsMicroService.DTO.ResponseDto;
import com.posts.PostsMicroService.Entity.Post;
import com.posts.PostsMicroService.DTO.PostDto;
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
        ResponseDto responseDto = postService.addPost(post);
        return responseDto;
    }

    @RequestMapping(value = "/getPost", method = RequestMethod.GET)
    public Post getPostDetails(@RequestParam String id) {
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


    public List<Post> findByName(@PathVariable String id){
        return postService.getUserPost(id);
}

    @RequestMapping(value = "/userWall/{id}", method = RequestMethod.GET)
         public List<Post> userWall(@PathVariable String id){
        RestTemplate restTemplate1 = new RestTemplate();

        String getURL = userProfileUrl + "/follow/getFollow?id=" + id;

        ResponseEntity<List<String>> responseEntity = restTemplate1.exchange(getURL, HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });
        List<String> followerId = responseEntity.getBody();
        followerId.add(id);
        List<Post> post = postService.getfeed(followerId);

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
//    @RequestMapping(value="/getFeeds/{id}" ,method = RequestMethod.GET)
//    public Post getFeedDetails(@PathVariable String id)
//    {
//        RestTemplate restTemplate1=new RestTemplate();
//    }
//        String getURLMerchant="http://10.177.7.120:8080/getMerchantFromProductId/"+productShortList.getProductId();}
//        Post post=postService.getPostDetails(id);
//        ResponseEntity<List<MerchantDetailsDTO>> responseEntity1=restTemplate1.exchange(getURLMerchant, HttpMethod.GET, null, new ParameterizedTypeReference<List<MerchantDetailsDTO>>() {};return post;
//    }
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
