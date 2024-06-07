package com.fatima.searchservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fatima.searchservice.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserServiceFeignClient UserServiceFeignClient;
  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = UserServiceFeignClient.getOneUsersByUsername(username);

    return UserDetailsImpl.build(user);
  }

}
