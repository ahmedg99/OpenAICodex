package tn.spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alarms")
@Data
@ToString
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String triggerCondition;
    private Double triggerValue;
    private boolean isActive;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

 }
