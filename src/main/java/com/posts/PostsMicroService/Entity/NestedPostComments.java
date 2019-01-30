package com.posts.PostsMicroService.Entity;

import com.fasterxml.uuid.Generators;
import org.springframework.data.annotation.Id;

import java.util.UUID;


public class NestedPostComments {
    @Id
    private String nestedCommentId;
    private String userId;
    private String reply;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNestedCommentId() {
        return nestedCommentId;
    }

    public void setNestedCommentId() {
        UUID uuid=Generators.timeBasedGenerator().generate();
        this.nestedCommentId = uuid.toString();
    }

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
