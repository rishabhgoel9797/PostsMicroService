package com.posts.PostsMicroService.Entity;

import com.fasterxml.uuid.Generators;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

public class PostsComments {

    @Id
    private String commentId;
    private String userId;
    private String description;

    private List<NestedPostComments> nestedPostComments;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId() {
        UUID uuid=Generators.timeBasedGenerator().generate();
        this.commentId = uuid.toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<NestedPostComments> getNestedPostComments() {
        return nestedPostComments;
    }

    public void setNestedPostComments(List<NestedPostComments> nestedPostComments) {
        this.nestedPostComments = nestedPostComments;
    }

    @Override
    public String toString() {
        return "PostsComments{" +
                "userId='" + userId + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
