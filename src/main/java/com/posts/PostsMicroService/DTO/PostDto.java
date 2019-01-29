package com.posts.PostsMicroService.DTO;

import com.sun.jmx.snmp.Timestamp;

import java.util.Date;

public class PostDto {
    private String postId;
    private String userId;
    private String url;
    private String description;
    private String createdBy;
    private Date date ;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Long getDate() {
        return date.getTime();
    }

    public void setDate() {
        this.date = new java.util.Date();
    }
}
