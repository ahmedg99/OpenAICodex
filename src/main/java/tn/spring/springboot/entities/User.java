package tn.spring.springboot.entities;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    @OneToMany(cascade =CascadeType.ALL , mappedBy="user")
    private Collection<Device> devices;
    @OneToOne
    Image image;
    public User() {

    }

    public User(int id, String name, String username, String password, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = new ArrayList<>(); // Initialize roles as an empty ArrayList
        this.devices = new ArrayList<>(); // Initialize devices as an empty ArrayList
        this.image = null; // Initialize image as null
    }
}