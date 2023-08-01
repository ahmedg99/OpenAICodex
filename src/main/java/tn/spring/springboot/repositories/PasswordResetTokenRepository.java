package tn.spring.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import tn.spring.springboot.entities.PasswordResetToken;
import tn.spring.springboot.entities.User;


@EnableJpaRepositories
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {

    boolean existsByUser(User user);
    void deleteByUser(User user);
    PasswordResetToken findByToken(String token);
    PasswordResetToken findByUser(User user);
}
