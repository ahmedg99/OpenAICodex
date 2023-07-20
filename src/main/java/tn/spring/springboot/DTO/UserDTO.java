package tn.spring.springboot.DTO;

import lombok.Data;
import lombok.ToString;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.Image;
import tn.spring.springboot.entities.Role;
import java.util.Collection;

@ToString
@Data
public class UserDTO {
    private int id;
    private String name ;
    private String username ;
    private String password ;
    private String email ;
    private Collection<Role> roles ;
    private Collection<Device> devices;
    Image image;
}
