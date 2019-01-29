package com.posts.PostsMicroService.Entity;

public class NestedPostComments {
    private String userId;
    private String reply;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
