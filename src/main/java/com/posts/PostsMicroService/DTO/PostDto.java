package com.posts.PostsMicroService.DTO;

import com.sun.jmx.snmp.Timestamp;

public class PostDto {
    private String postId;
    private String userId;
    private String type;
    private String description;
    private String createdBy;
    private Timestamp timestamp ;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp() {
        this.timestamp =  new Timestamp(System.currentTimeMillis());
    }
}
