package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Alarm;


@EnableJpaRepositories
public interface AlarmRepository extends JpaRepository<Alarm,Integer> {

    Alarm findAlarmByName(String name) ;
    boolean existsByName(String name) ;


}
