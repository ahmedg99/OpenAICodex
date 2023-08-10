package tn.spring.springboot.Services.Interfaces;

import org.springframework.data.domain.Page;
import tn.spring.springboot.entities.Device;

import java.util.List;

public interface IServiceDevice {

    Device addDevice( String username,Device device) ;
    Page<Device> getAllDevices(int offset , int pageSize);
    Device updateDevice(int id , Device device) ;
    Device findById(int id ) ;
    Page<Device>  findAllByUserUsername(String username ,  int offset , int pageSize) ;
    Page<Device>  findDevicesByModelAndUser(String username ,  String model , int offset , int pageSize ) ;





}
