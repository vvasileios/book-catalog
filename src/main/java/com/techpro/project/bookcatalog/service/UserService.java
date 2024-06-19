package com.techpro.project.bookcatalog.service;

import com.techpro.project.bookcatalog.model.User;
import com.techpro.project.bookcatalog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public void createUser(String username, String rawPassword, String role) {
    String hashedPassword = passwordEncoder.encode(rawPassword);
    User user = new User();
    user.setUsername(username);
    user.setPassword(hashedPassword);
    user.setRole(role);
    userRepository.save(user);
  }

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
