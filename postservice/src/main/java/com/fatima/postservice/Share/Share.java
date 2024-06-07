package com.fatima.postservice.Share;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatima.postservice.Comment.Comment;
import com.fatima.postservice.Like.Like;
import com.fatima.postservice.Post.Post;
import com.fatima.postservice.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "shares")
public class Share {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shareId;
    @Size(max = 10000, message = "Content is too long")
    private String content;
    @JsonIgnore
   
    @Column(name = "user_ID")
    private Long userId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    
    @OneToMany(mappedBy = "share", cascade = CascadeType.ALL) //t8ayarat
    public List<Like> like;

    @OneToMany(mappedBy = "share", cascade = CascadeType.ALL)
    public List<Comment> sharComments;
    
    public Share() {
    }

    public Share( String content, Long userId, Post post) {
       
        this.content = content;
        this.userId = userId;
        this.post = post;
    }
    @Override
    public String toString() {
        return "Share [shareId=" + shareId + ", content=" + content + "]";
    }
    public Long getShareId() {
        return shareId;
    }
    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    
    public Long getPostId() {
        return post.getPostId();
    }

}