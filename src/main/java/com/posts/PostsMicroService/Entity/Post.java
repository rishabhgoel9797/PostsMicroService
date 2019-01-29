package com.posts.PostsMicroService.Entity;

        import com.sun.jmx.snmp.Timestamp;
        import org.springframework.data.annotation.Id;
        import org.springframework.data.mongodb.core.mapping.Document;

        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        import java.util.List;

@Document(collection = "Post")
public class Post {

    @Id
    private String postId;
    private String userId;
    private String url;
    private String description;
    private String createdBy;
    private Date date;
    private List<String> postLikes;

    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private List<PostsComments> postsComments;

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


    public String getDate() {
        return null;
    }

    public void setDate() {

                this.date = new java.util.Date();



    }

    public List<String> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(List<String> postLikes) {



        this.postLikes = postLikes;
    }



    public List<PostsComments> getPostsComments() {
        return postsComments;
    }

    public void setPostsComments(List<PostsComments> postsComments) {
        this.postsComments = postsComments;
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
                ", postLikes=" + postLikes +
                ", postsComments=" + postsComments +
                '}';
    }
}