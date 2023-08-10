package tn.spring.springboot.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ToString
@Data
@Table(name = "Device")

public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int deviceId;
    private String label ;
    @Enumerated(EnumType.STRING)
    private Model model ;
    private String Longitude ;
    private String latitude ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarms;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    private Credential credential;




}