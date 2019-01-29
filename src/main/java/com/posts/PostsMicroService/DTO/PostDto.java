package com.posts.PostsMicroService.DTO;

import com.sun.jmx.snmp.Timestamp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostDto {
    private String postId;
    private String userId;
    private String url;
    private String description;
    private String createdBy;
    private Date date ;
    private List<String> postLikes;




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

    public Date getDate() {
        return this.date;
    }

    public void setDate() {

        this.date = new java.util.Date();

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<String> postLikes) {
        this.postLikes = postLikes;
    }
    @Override
    public String toString() {
        return "PostDto{" +
                "postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", date=" + date +
                ", postLikes=" + postLikes +
                '}';
    }


}
