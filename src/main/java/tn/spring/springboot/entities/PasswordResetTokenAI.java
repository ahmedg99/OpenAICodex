package tn.spring.springboot.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "password_reset_token_AI")
public class PasswordResetTokenAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Date expirationDate;

    // Constructors, getters, setters, and other methods
    //...

    // Constructors
    public PasswordResetTokenAI() {
    }

    public PasswordResetTokenAI(User user, String token, Date expirationDate) {
        this.user = user;
        this.token = token;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

