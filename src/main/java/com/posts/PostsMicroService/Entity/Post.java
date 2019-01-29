package com.posts.PostsMicroService.Entity;

        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;

        import java.util.Date;

@Document(collection = "Post")
public class Post {

    @Id
    private String postId;
    private String userId;
    private String url;
    private String type;
    private String description;
    private String createdBy;
    private Date date;

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


    public Date getDate() {
        return date;
    }

    public void setDate() {
        this.date = new java.util.Date();
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", date=" + date +
                '}';
    }
}