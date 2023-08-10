package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 import tn.spring.springboot.entities.LogEntry;


@EnableJpaRepositories
public interface LogEntyRepository extends JpaRepository<LogEntry,Integer> {

}
