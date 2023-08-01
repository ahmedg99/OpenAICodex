package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 import tn.spring.springboot.entities.User;


@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User,Integer> {
 User findUserByUsername(String username) ;
    User findUserByEmail(String email) ;

}
