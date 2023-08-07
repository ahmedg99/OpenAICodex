package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.spring.springboot.entities.PasswordResetTokenAI;

@Repository
public interface PasswordResetTokenAIRepository extends JpaRepository<PasswordResetTokenAI, Long> {

    PasswordResetTokenAI findByToken(String token);
}
