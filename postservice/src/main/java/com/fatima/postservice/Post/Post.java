package com.fatima.postservice.Post;
import java.util.List;
import com.fatima.postservice.Comment.Comment;
import com.fatima.postservice.Like.Like;
import com.fatima.postservice.Share.Share;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String image;
    private String video;

    @Column(columnDefinition = "TEXT")
private String content;


    @Column(name = "user_ID")
    private Long userId;

    @OneToMany
    public List<Comment> postComments;

    @OneToMany
    public List<Like> like;

    @OneToMany
    private List<Share> shares;

    private LocalDateTime timestamp;

    public Post() {
        this.timestamp = LocalDateTime.now();
    }

    public Post(String content) {
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comment> postComments) {
        this.postComments = postComments;
    }

    public List<Like> getLike() {
        return like;
    }

    public void setLike(List<Like> like) {
        this.like = like;
    }

    public List<Share> getShares() {
        return shares;
    }

    public void setShares(List<Share> shares) {
        this.shares = shares;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}