package com.fatima.postservice.Comment;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fatima.postservice.Controllers.PostController;
import com.fatima.postservice.User;
import com.fatima.postservice.UserServiceFeignClient;
import com.fatima.postservice.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class CommentModelAss implements RepresentationModelAssembler<Comment, EntityModel<Comment>> {
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserServiceFeignClient UserServiceFeignClient;

  @Autowired
  HttpServletRequest request;
   
String string;
    @Override
    public EntityModel<Comment> toModel(Comment comment) {
        return null;
        // EntityModel.of(comment,linkTo(methodOn(Controller.class).allUser()).withSelfRel);
    }

    public EntityModel<Comment> commentDelEdit(Comment comment) {
        User user = userFromToken(request);
        if (UserServiceFeignClient.getUserByUserId(comment.getUserId()) == user) {
            return EntityModel.of(comment,
                    linkTo(methodOn(PostController.class).deleteComment(comment.getCommentId()))
                            .withRel("delete comment"),

                    linkTo(methodOn(PostController.class).editCoumment(comment.getCommentId(),string))
                            .withRel("edit your comment "));
                            //  linkTo(methodOn(PostController.class).findById(comment.getPost().getPostId()))
                            // .withRel("go to the post "));
        } else {
            return EntityModel.of(comment,
                  
                    linkTo(methodOn(PostController.class).findById(comment.getPost().getPostId()))
                            .withRel("go to the post "));

        }
    }


    
    public User userFromToken(HttpServletRequest request){
        String jwt = parseJwt(request);
        if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
            String username = jwtUtils.getUserNameFromJwtToken(jwt);
            User user = UserServiceFeignClient.getUsersByUsername(username)
                    ;
                  return user;}
        return null;
      }
       private String parseJwt(HttpServletRequest request) {
          String headerAuth = request.getHeader("Authorization");
      
          if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
          }
      
          return null;
        }  

}
