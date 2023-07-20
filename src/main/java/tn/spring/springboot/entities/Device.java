package tn.spring.springboot.entities;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data
@Table(name = "Device")

public class Device implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int deviceId;
    private String model ;
    private String Longitude ;
    private String latitude ;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user ;


}