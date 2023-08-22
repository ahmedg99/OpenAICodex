package tn.spring.springboot.services.Implementation;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tn.spring.springboot.services.Interfaces.IServiceDevice;
import tn.spring.springboot.entities.*;
import tn.spring.springboot.repositories.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class DeviceServiceImp implements IServiceDevice {
 DeviceRepository deviceRepository;
 UserRepository userRepository;
 CredentialRepository credentialRepository ;
 AlarmRepository alarmRepository ;

 DeviceServiceImp(DeviceRepository deviceRepository, UserRepository userRepository , CredentialRepository credentialRepository , AlarmRepository alarmRepository) {
  this.deviceRepository = deviceRepository;
  this.userRepository = userRepository;
    this.credentialRepository = credentialRepository ;
    this.alarmRepository = alarmRepository ;
 }


 @Override
 public Device addDevice(String username, Device device) {
  User user = userRepository.findUserByUsername(username);
if(device.getCredential()!=null) {
 Credential newCredential = credentialRepository.save(device.getCredential());
 device.setCredential(newCredential);
}
   device.setUser(user);

  // Save the Device first to ensure it's persisted
  Device savedDevice = deviceRepository.save(device);

  List<Alarm> savedAlarms = new ArrayList<>();
  for (Alarm alarm : savedDevice.getAlarms()) {
   Alarm existingAlarm = alarmRepository.findAlarmByName(alarm.getName());
   if (existingAlarm != null) {
    savedAlarms.add(existingAlarm);
   } else {
    Alarm savedAlarm = alarmRepository.save(alarm);
    savedAlarms.add(savedAlarm);
   }
  }

  // Associate the saved alarms with the saved device
  savedAlarms.forEach(alarm -> alarm.setDevice(savedDevice));

  // Save the updated alarms with device references
  alarmRepository.saveAll(savedAlarms);


  return savedDevice;
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
  oldDevice.setAlarms(device.getAlarms());
  oldDevice.setCredential(device.getCredential());
  return deviceRepository.save(oldDevice);
 }

 @Override
 public Device findById(int id) {
  return deviceRepository.findById(id).orElse(null);
 }

 @Override
 public Page<Device> findAllByUserUsername(String username, int offset, int pageSize) {
  Pageable pageable = PageRequest.of(offset, pageSize);
  Page<Device> liste = deviceRepository.findAllByUserUsername(username, pageable);
   return  liste;
 }

 public Page<Device> findDevicesByModelAndUser(String username, String model, int page, int pageSize) {
  Pageable pageable = PageRequest.of(page, pageSize);
  return deviceRepository.findAllByModelAndUserUsername(Model.valueOf(model), username, pageable);
 }



 @Override
 public List<Device> findAllByUserUsername(String username) {
    return deviceRepository.findAllByUserUsername(username);
 }

 @Override
 public List<Device> findAllByModel(Model model) {
  return deviceRepository.findAllByModel(model);
 }

 @Transactional
 public void deleteAllByUserUsername(String username) {
    deviceRepository.deleteAllByUserUsername(username);
 }

 @Override
 public Device addDevice(Device device, List<Alarm> alarms, Credential credential) {

  if (credential != null) {
   credentialRepository.save(credential);
   device.setCredential(credential);
  }

  for (Alarm alarm : alarms) {
   alarmRepository.save(alarm);
    device.getAlarms().add(alarm);
   }

   return deviceRepository.save(device);
 }




}

