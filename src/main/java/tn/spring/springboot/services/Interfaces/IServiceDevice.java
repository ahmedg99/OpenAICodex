package tn.spring.springboot.services.Interfaces;

import org.springframework.data.domain.Page;
import tn.spring.springboot.entities.Alarm;
import tn.spring.springboot.entities.Credential;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.Model;

import java.util.List;

public interface IServiceDevice {

    Device addDevice( String username,Device device) ;
    Page<Device> getAllDevices(int offset , int pageSize);
    Device updateDevice(int id , Device device) ;
    Device findById(int id ) ;
    Page<Device>  findAllByUserUsername(String username ,  int offset , int pageSize) ;
    Page<Device>  findDevicesByModelAndUser(String username ,  String model , int offset , int pageSize ) ;
    public List<Device> findAllByUserUsername(String username) ;
    public List<Device> findAllByModel(Model model) ;

    void deleteAllByUserUsername(String username) ;

    Device addDevice( Device device, List<Alarm> alarms, Credential credential);








}
