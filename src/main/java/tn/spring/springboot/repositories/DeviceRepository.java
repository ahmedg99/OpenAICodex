package tn.spring.springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.Model;

import java.util.List;

@EnableJpaRepositories
public interface DeviceRepository extends JpaRepository<Device,Integer> {
    Page<Device> findAllByUserUsername(String user_username , Pageable pageable) ;
    Page<Device> findAllByModelAndUserUsername(Model model , String user_username , Pageable pageable) ;
    List<Device> findAllByUserUsername(String user_username) ;
    List<Device> findAllByModel(Model model) ;
    void deleteAllByUserUsername(String user_username) ;

}
