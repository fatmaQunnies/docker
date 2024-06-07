// package com.fatima.postservice;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import java.time.LocalDate;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// @Service
// public class postService {

//   @Autowired
//   private PostRepo postRepository;
//   @Autowired
//   private CommentRepo commentRepository;
//   @Autowired
//   private LikesPostRepository likePostRepository;
//   @Autowired
//   private LikesCommentRepository likeCommentRepository;
//   @Autowired
//   private ShareRepository shareRepository;
//   @Autowired
//   private TopicsRepository topicRepository;
//   @Autowired
//   private UserServiceFeignClient userServiceFeignClient;

//   public Post createPost(Long userId, Post post) {
//     User user = userServiceFeignClient.one(userId);
//     if (user == null) {
//       throw new RuntimeException("User not found with id: " + userId);
//     }
//     post.setUser(userId);
//     post.setTimestamp(LocalDate.now());
//     return postRepository.save(post);
//   }

//   public List<Post> getPostsByUser(Long userId) {
//     User user = userServiceFeignClient.one(userId);
//     if (user == null) {
//       throw new RuntimeException("User not found with id: " + userId);
//     }
//     return postRepository.findByUserId(userId);
//   }

//   public Post updatePost(Long userId, Long postId, Post updatedPost) {
//     User user = userServiceFeignClient.one(userId);
//     if (user == null) {
//       throw new RuntimeException("User not found with id: " + userId);
//     }

//     Post post = postRepository.findById(postId)
//       .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

//     if (!post.getUser().equals(userId)) {
//       throw new RuntimeException("User is not authorized to update this post");
//     }

//     post.setContent(updatedPost.getContent());
//     post.setTimestamp(LocalDate.now());

//     return postRepository.save(post);
//   }

//   public void deletePost(Long userId, Long postId) {
//     User user = userServiceFeignClient.one(userId);
//     if (user == null) {
//       throw new RuntimeException("User not found with id: " + userId);
//     }

//     Post post = postRepository.findById(postId)
//       .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));

//     if (!post.getUser().equals(userId)) {
//       throw new RuntimeException("User is not authorized to delete this post");
//     }

//     postRepository.delete(post);
//   }

//   public Post getPostById(Long postId) {
//     return postRepository.findById(postId)
//       .orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
//   }

//   public String getPostContent(Long postId) {
//     Post post = getPostById(postId);
//     return post.getContent();
//   }

//   public LocalDate getPostTimestamp(Long postId) {
//     Post post = getPostById(postId);
//     return post.getTimestamp();
//   }

//   public int getPostLikeCount(Long postId) {
//     Post post = getPostById(postId);
//     return post.getLikes().size();
//   }

//   public int getPostShareCount(Long postId) {
//     Post post = getPostById(postId);
//     return post.getShares().size();
//   }

//   public int getPostCommentCount(Long postId) {
//     Post post = getPostById(postId);
//     return post.getComments().size();
//   }

//   public List<Long> getSharedUsersForPost(Long postId) {
//     Post post = getPostById(postId);
//     return post.getShares().stream()
//       .map(Share::getUser)
//       .collect(Collectors.toList());
//   }

//   public Long getPostUserId(Long postId) {
//     Post post = getPostById(postId);
//     return post.getUser();
//   }

//   public long getPostCountForUser(Long userId) {
//     User user = userServiceFeignClient.one(userId);
//     return (long) user.getPosts().size();
//   }

//   public void likePost(Long userId, Long postId) {
//     Post post = getPostById(postId);
//     User user = userServiceFeignClient.one(userId);

//     Optional<Likes_Post> existingLike = likePostRepository.findByPostAndUserId(post, user.getUserID());
//     if (existingLike.isPresent()) {
//       throw new RuntimeException("User has already liked this post");
//     }

//     Likes_Post like = new Likes_Post();
//     like.setPost(post);
//     like.setUser(user.getUserID());

//     likePostRepository.save(like);
//   }

//   public void unlikePost(Long userId, Long postId) {
//     Post post = getPostById(postId);
//     User user = userServiceFeignClient.one(userId);

//     Optional<Likes_Post> existingLike = likePostRepository.findByPostAndUserId(post, user.getUserID());
//     if (!existingLike.isPresent()) {
//       throw new RuntimeException("Like not found");
//     }

//     likePostRepository.delete(existingLike.get());
//   }

//   public List<Long> getUsersWhoLikedPost(Long postId) {
//     Post post = getPostById(postId);
//     return likePostRepository.findByPost(post).stream()
//       .map(Likes_Post::getUser)
//       .collect(Collectors.toList());
//   }

//   public void likeComment(Long userId, Long postId, Long commentId) {
//     Comment comment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
//     User user = userServiceFeignClient.one(userId);

//     Optional<Likes_Comment> existingLike = likeCommentRepository.findByCommentAndUserId(comment, user.getUserID());
//     if (existingLike.isPresent()) {
//       throw new RuntimeException("User has already liked this comment");
//     }

//     Likes_Comment like = new Likes_Comment();
//     like.setComment(comment);
//     like.setUser(user.getUserID());

//     likeCommentRepository.save(like);
//   }

//   public void unlikeComment(Long userId, Long postId, Long commentId) {
//     Comment comment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
//     User user = userServiceFeignClient.one(userId);

//     Optional<Likes_Comment> existingLike = likeCommentRepository.findByCommentAndUserId(comment, user.getUserID());
//     if (!existingLike.isPresent()) {
//       throw new RuntimeException("Like not found");
//     }

//     likeCommentRepository.delete(existingLike.get());
//   }

//   public Comment createComment(Long userId, Long postId, Comment comment) {
//     User user = userServiceFeignClient.one(userId);
//     Post post = getPostById(postId);

//     comment.setUser(userId);
//     comment.setPost(post);

//     return commentRepository.save(comment);
//   }

//   public void deleteComment(Long userId, Long postId, Long commentId) {
//     User user = userServiceFeignClient.one(userId);
//     Post post = getPostById(postId);
//     Comment comment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

//     if (!comment.getUser().equals(userId) || !comment.getPost().getPostID().equals(postId)) {
//       throw new RuntimeException("Comment does not belong to the specified user and post");
//     }

//     commentRepository.delete(comment);
//   }

//   public List<Comment> getAllCommentsForPost(Long postId) {
//     Post post = getPostById(postId);
//     return commentRepository.findByPost(post);
//   }

//   public Comment updateComment(Long postId, Long commentId, Comment updatedComment) {
//     Comment comment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

//     comment.setContent(updatedComment.getContent());

//     return commentRepository.save(comment);
//   }

//   public Comment createReply(Long userId, Long postId, Long commentId, Comment reply) {
//     User user = userServiceFeignClient.one(userId);
//     Post post = getPostById(postId);
//     Comment parentComment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

//     reply.setUser(userId);
//     reply.setPost(post);
//     reply.setParentComment(parentComment);

//     return commentRepository.save(reply);
//   }

//   public List<Comment> getAllRepliesForComment(Long commentId) {
//     Comment comment = commentRepository.findById(commentId)
//       .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));

//     return commentRepository.findByParentComment(comment);
//   }

//   public void repostPost(Long userId, Long postId) {
//     User user = userServiceFeignClient.one(userId);
//     Post originalPost = getPostById(postId);

//     Optional<Share> existingShare = shareRepository.findByPostAndUserId(originalPost, userId);
//     if (existingShare.isPresent()) {
//       throw new RuntimeException("User has already reposted this post");
//     }

//     Share repost = new Share();
//     repost.setPost(originalPost);
//     repost.setUser(userId);

//     shareRepository.save(repost);
//   }

//   public void addTopicToPost(Long postId, Long topicId) {
//     Post post = getPostById(postId);
//     Topics topic = topicRepository.findById(topicId)
//       .orElseThrow(() -> new RuntimeException("Topic not found with id: " + topicId));

//     post.getTopics().add(topic);
//     postRepository.save(post);
//   }

//   public void removeTopicFromPost(Long postId, Long topicId) {
//     Post post = getPostById(postId);
//     Topics topic = topicRepository.findById(topicId)
//       .orElseThrow(() -> new RuntimeException("Topic not found with id: " + topicId));

//     post.getTopics().remove(topic);
//     postRepository.save(post);
//   }
// }