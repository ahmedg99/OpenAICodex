package tn.spring.springboot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class DeviceAI {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceId;

    private String model;

    private String longitude;

    private String latitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // Constructors, getters, and setters

    public DeviceAI() {
    }

    public DeviceAI(String model, String longitude, String latitude, User user) {
        this.model = model;
        this.longitude = longitude;
        this.latitude = latitude;
        this.user = user;
    }

    // Getters and Setters

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
