package tn.spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "credentials")
@Data
@ToString

public class Credential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String accessLevel;
    private Date expirationDate;

    @OneToOne
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Device device;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

 }
