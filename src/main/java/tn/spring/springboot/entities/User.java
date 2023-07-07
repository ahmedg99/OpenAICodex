package tn.spring.springboot.entities;


import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@ToString
@Data
@Table(name = "AppUser")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String name ;
    private String username ;
    private String password ;
    private String email ;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles ;

}