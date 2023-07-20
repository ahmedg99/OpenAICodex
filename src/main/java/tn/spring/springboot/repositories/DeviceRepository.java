package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Device;

import java.util.List;

@EnableJpaRepositories
public interface DeviceRepository extends JpaRepository<Device,Integer> {
    List<Device> findAllByUserUsername(String user_username) ;
}
