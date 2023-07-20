package tn.spring.springboot.Services.Interfaces;

import org.springframework.data.domain.Page;
import tn.spring.springboot.entities.Device;

import java.util.List;

public interface IServiceDevice {

    Device addDevice(Device device) ;
    Page<Device> getAllDevices(int offset , int pageSize);
    Device updateDevice(int id , Device device) ;
    Device findById(int id ) ;





}
