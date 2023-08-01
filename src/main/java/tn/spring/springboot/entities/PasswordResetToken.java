package tn.spring.springboot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
 public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private Date expiryDate;
    private boolean canUpdatePassword;



    public PasswordResetToken(String token, User user , Date expiryDate , boolean canUpdatePassword) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
        this.canUpdatePassword = canUpdatePassword;
    }


    public PasswordResetToken() {

    }
}