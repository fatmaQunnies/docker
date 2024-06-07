package com.fatima.postservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "userservice", url = "${userservice.url}")//, url = "${userservice.url}"
public interface UserServiceFeignClient {

    @GetMapping("/users/{id}")
    public User one(@PathVariable("id") Long userId);

    // @GetMapping("/users/username/{username}")
    // User findOneUserByUsername(String username);

    @GetMapping("/users/username/{username}")
    public User getUsersByUsername(@PathVariable("username") String username);

    @GetMapping("/users/{userId}")
    User getUserByUserId(@PathVariable("userId") Long userId);
}