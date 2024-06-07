package com.fatima.searchservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchService {

  @Autowired
  private UserServiceFeignClient userServiceFeignClient;

  public List<User> searchUsersByUsername(String username) {
    return userServiceFeignClient.getUsersByUsername(username);
  }

  public User getOneUserByUsername(String username) {
    List<User> users = userServiceFeignClient.getUsersByUsername(username);
    if (!users.isEmpty()) {
        // Assuming you want to return the first user found
        return users.get(0);
    } else {
        return null; // Or you can throw an exception indicating user not found
    }
}
}
