package com.posts.PostsMicroService.DTO;

import com.posts.PostsMicroService.Entity.Post;

import java.util.List;

public class PostWrapper {
    private List<Post> postList;

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    @Override
    public String toString() {
        return "PostWrapper{" +
                "postList=" + postList +
                '}';
    }
}
