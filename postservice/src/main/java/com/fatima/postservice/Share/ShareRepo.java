package com.fatima.postservice.Share;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fatima.postservice.Post.Post;

public interface ShareRepo extends JpaRepository<Share, Long> { 
    List<Share> findByPost(Post post);
    // @Query("SELECT s FROM shares s WHERE s.user_id = :userId")
    // List<Share> findByUserId(@Param("user_id") Long userId);
    // @Query("SELECT s FROM Share s WHERE s.user.userId = :userId")
    List<Share> findByUserId(@Param("userId") Long userId);
}