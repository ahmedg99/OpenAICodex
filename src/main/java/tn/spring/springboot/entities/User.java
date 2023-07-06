package tn.spring.springboot.entities;


 import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ToString
@Data
@Table(name = "person")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter

    private int id;
    private String nom ;
    private String prenom ;
    private String password ;
    private String email ;
    private String adresse ;

}