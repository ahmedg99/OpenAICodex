package tn.spring.springboot.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.springboot.Services.Interfaces.IServiceDeviceAI;
import tn.spring.springboot.entities.DeviceAI;
import tn.spring.springboot.repositories.DeviceAIRepository;

import java.util.List;

@Service
public class ServiceDeviceAIImpl implements IServiceDeviceAI {

    private final DeviceAIRepository deviceRepository;

    @Autowired
    public ServiceDeviceAIImpl(DeviceAIRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public DeviceAI createDevice(DeviceAI device) {
        return deviceRepository.save(device);
    }

    @Override
    public DeviceAI getDeviceById(int deviceId) {
        return deviceRepository.findById(deviceId).orElse(null);
    }

    @Override
    public List<DeviceAI> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public DeviceAI updateDevice(DeviceAI device) {
        // Make sure the device to be updated exists in the database
        if (deviceRepository.existsById(device.getDeviceId())) {
            return deviceRepository.save(device);
        }
        return null;
    }

    @Override
    public void deleteDeviceById(int deviceId) {
        deviceRepository.deleteById(deviceId);
    }
}
