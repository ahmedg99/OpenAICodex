package tn.spring.springboot.Services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.UserRepository;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserServiceImp implements IServiceUser {


 @Autowired
 UserRepository userRepository ;

 @Override
 public List<User> getAllUsers() {
  return userRepository.findAll();
 }

 @Override
 public User createUser(User user) {
  return userRepository.save(user);
 }
}
