package tn.spring.springboot.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.springboot.entities.DeviceAI;

public interface DeviceAIRepository extends JpaRepository<DeviceAI, Integer> {
 }
