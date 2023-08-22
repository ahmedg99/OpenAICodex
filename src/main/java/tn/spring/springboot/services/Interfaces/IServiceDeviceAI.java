package tn.spring.springboot.services.Interfaces;

import tn.spring.springboot.entities.DeviceAI;

import java.util.List;

public interface IServiceDeviceAI {

    // Create a new device
    DeviceAI createDevice(DeviceAI device);

    // Retrieve a device by its ID
    DeviceAI getDeviceById(int deviceId);

    // Retrieve all devices
    List<DeviceAI> getAllDevices();

    // Update an existing device
    DeviceAI updateDevice(DeviceAI device);

    // Delete a device by its ID
    void deleteDeviceById(int deviceId);
}
