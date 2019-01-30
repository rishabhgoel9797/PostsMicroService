package com.posts.PostsMicroService.Repository;

import com.posts.PostsMicroService.Entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {

    List<Post> findPostByUserId(String userId);

}
