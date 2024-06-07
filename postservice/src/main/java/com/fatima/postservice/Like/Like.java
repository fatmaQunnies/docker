package com.fatima.postservice.Like;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatima.postservice.Comment.Comment;
import com.fatima.postservice.Post.Post;
import com.fatima.postservice.Share.Share;
import com.fatima.postservice.User;

import jakarta.persistence.*;
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    private likeType type ;
    @JsonIgnore
     @ManyToOne 
    @JoinColumn(name = "post_id")
    public Post post;
  @JsonIgnore
  @ManyToOne 
    @JoinColumn(name = "comment_id")
    public Comment comment;
   
    @Column(name = "user_ID")
    private Long userId;
    @JsonIgnore
    @ManyToOne  
    @JoinColumn(name = "share_id")
    public Share share;


    public Share getShare() {
      return share;
    }
    public void setShare(Share share) {
      this.share = share;
    }
    public Like( likeType type) {
      
      this.type = type;
   
    }
    public Like() {
    }

    public Like( likeType type, Post post, Comment comment, Long userId ) {
     
      this.type = type;
      this.post = post;
      this.comment = comment;
      this.userId = userId;
    }
    @Override
    public String toString() {
      return "Like [likeId=" + likeId + ", type=" + type + "]";
    }
    public Long getLikeId() {
      return likeId;
    }
    public void setLikeId(Long likeId) {
      this.likeId = likeId;
    }
    public likeType getType() {
      return type;
    }
    public void setType(likeType type) {
      this.type = type;
    }
    public Long getPostId() {
      return post.getPostId();
    }
    public void setPost(Post post) {
      this.post = post;
    }
    public Comment getComment() {
      return comment;
    }
    public void setComment(Comment comment) {
      this.comment = comment;
    }
    public Long getUserId() {
      return userId;
    }
    public void setUserId(Long userId) {
      this.userId = userId;
    }
    
    
    
    
}