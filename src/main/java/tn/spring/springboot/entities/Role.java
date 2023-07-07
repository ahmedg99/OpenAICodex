package tn.spring.springboot.entities;


import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data

public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String roleName ;



}