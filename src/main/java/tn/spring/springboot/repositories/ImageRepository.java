package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Image;

import java.util.Optional;

@EnableJpaRepositories
public interface ImageRepository extends JpaRepository<Image,Integer> {
 Optional<Image> findByName(String name);
 }
