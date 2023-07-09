package tn.spring.springboot.Services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Role;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.RoleRepository;
import tn.spring.springboot.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
public class UserServiceImp implements IServiceUser , UserDetailsService {

 UserRepository userRepository ;
 RoleRepository roleRepository ;
 @Autowired
 PasswordEncoder passwordEncoder ;

 UserServiceImp(UserRepository userRepository,RoleRepository roleRepository , PasswordEncoder passwordEncoder ) {
  this.userRepository = userRepository ;
  this.roleRepository = roleRepository ;
  this.passwordEncoder =passwordEncoder ;
 }

 @Override
 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  User user = userRepository.findUserByUsername(username);
  if(user == null){
   log.error("username not found in the datatbase");
   throw new UsernameNotFoundException("username not found in the datatbase") ;
  } else {
   log.info("username is found in the  datatbase");
  }
  Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
  user.getRoles().forEach(role -> {
   authorities.add(new SimpleGrantedAuthority(role.getRoleName())) ;
  });
  return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
          authorities);
 }

 @Override
 public List<User> getAllUsers() {
  return userRepository.findAll();
 }

 @Override
 public Page<User> getAllUsers(int offset, int pageSize) {
  return userRepository.findAll(PageRequest.of(offset,pageSize));
 }

 @Override
 public User createUser(User user) {
  log.info("saving new user {} to the database" , user.getUsername());
  user.setPassword(passwordEncoder.encode(user.getPassword()));
  return userRepository.save(user);
 }

 @Override
 public Role saveRole(Role role) {
  log.info("saving new role {} to the database" , role.getRoleName());
  return roleRepository.save(role);
 }

 @Override
 public User updateUser(int id, User user) {
  User userToUpdate = userRepository.findById(id).get() ;
  userToUpdate.setId(user.getId());
  userToUpdate.setUsername(user.getUsername());
  userToUpdate.setRoles(user.getRoles());
  userToUpdate.setEmail(user.getEmail());
  log.info("the user {} was updated " , user.getUsername());
  return userRepository.save(userToUpdate);
 }

 @Override
 public void addRoleToUser(String username, String roleName) {
  User user = userRepository.findUserByUsername(username);
  Role role = roleRepository.findRoleByRoleName(roleName);
  log.info("adding the role {} to the user {} " ,role.getRoleName(),  user.getUsername());
  user.getRoles().add(role) ;
  userRepository.save(user);
 }
}
