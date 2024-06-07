// package com.shouq.postservice;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(name = "userservice")
// public interface UserClient {

//     @GetMapping("users/{userId}")
//     public User getUser(@PathVariable Integer userId);

//     @GetMapping("/users/username/{username}")
//     public User getUsersByUsername(@PathVariable("username") String username);

// }