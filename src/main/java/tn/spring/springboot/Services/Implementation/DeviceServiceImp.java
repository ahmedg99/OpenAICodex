package tn.spring.springboot.Services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceDevice;
import tn.spring.springboot.Services.Interfaces.IServiceUser;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.Image;
import tn.spring.springboot.entities.Role;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.DeviceRepository;
import tn.spring.springboot.repositories.RoleRepository;
import tn.spring.springboot.repositories.UserRepository;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@Slf4j
public class DeviceServiceImp implements IServiceDevice {
 DeviceRepository deviceRepository;
 UserRepository userRepository;

 DeviceServiceImp(DeviceRepository deviceRepository, UserRepository userRepository) {
  this.deviceRepository = deviceRepository;
  this.userRepository = userRepository;
 }


 @Override
 public Device addDevice(String username, Device device) {
  User user = userRepository.findUserByUsername(username);
  device.setUser(user);
  return deviceRepository.save(device);
 }

 @Override
 public Page<Device> getAllDevices(int offset, int pageSize) {
  return deviceRepository.findAll(PageRequest.of(offset, pageSize));
 }

 @Override
 public Device updateDevice(int id, Device device) {
  Device oldDevice = deviceRepository.findById(id).get();
  oldDevice.setModel(device.getModel());
  oldDevice.setLatitude(device.getLatitude());
  oldDevice.setLongitude(device.getLongitude());
  return deviceRepository.save(oldDevice);
 }

 @Override
 public Device findById(int id) {
  return deviceRepository.findById(id).orElse(null);
 }

 @Override
 public Page<Device> findAllByUserUsername(String username, int offset, int pageSize) {
  List<Device> liste = deviceRepository.findAllByUserUsername(username);
  Page<Device> devicePage = new PageImpl<>(liste);
  return  devicePage;
 }

}

