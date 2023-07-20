package tn.spring.springboot.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;


@Entity
@Data
public class Image {

    @Id
    @GeneratedValue
    Integer id;


    private String name;

    private String location;

    @Lob
    byte[] content;

    public Image(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Image(String name, byte[] content) {
        this.name = name;
        this.content = content;
    }

    public Image() {
    }

}
