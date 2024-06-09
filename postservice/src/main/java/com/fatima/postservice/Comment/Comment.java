package com.fatima.postservice.Comment;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatima.postservice.Like.Like;
import com.fatima.postservice.Post.Post;
import com.fatima.postservice.Share.Share;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    // private Type type;

    @Size(max = 10000, message = "Content is too long")
    private String content;
    private String image;
    private String video;
   
    @Column(name = "user_ID")
    private Long userId;
    Post post;
Share share;
    
    public Comment() {
        
    }
  
    public Comment( String content, String image, String video, Long userId, Post post) {
        
        this.content = content;
        this.image = image;
        this.video = video;
        this.userId = userId;
        this.post = post;
    }
    public Comment(String content) {
     
        this.content = content;
     
    }
    public Long getCommentId() {
        return commentId;
    }
    public Long getId() {
        return commentId;
    }
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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

    public Share getShare() {
        return share;
    }

    public void setShare(Share share) {
        this.share = share;
    }
    

}