package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Credential;


@EnableJpaRepositories
public interface CredentialRepository extends JpaRepository<Credential,Integer> {
    Credential findCredentialByusername(String username) ;
    boolean existsCredentialByUsername(String username) ;

}
