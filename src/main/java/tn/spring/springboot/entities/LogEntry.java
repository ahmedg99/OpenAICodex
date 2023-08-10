package tn.spring.springboot.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "log_entries")
@Data
@ToString

public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date timestamp;
    private String message;
    private String logType;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

 }
