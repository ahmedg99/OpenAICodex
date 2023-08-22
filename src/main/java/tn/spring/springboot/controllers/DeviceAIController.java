package tn.spring.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.spring.springboot.services.Interfaces.IServiceDeviceAI;
import tn.spring.springboot.entities.DeviceAI;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceAIController {

    private final IServiceDeviceAI deviceService;

    @Autowired
    public DeviceAIController(IServiceDeviceAI deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping
    public ResponseEntity<DeviceAI> createDevice(@RequestBody DeviceAI device) {
        DeviceAI createdDevice = deviceService.createDevice(device);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceAI> getDeviceById(@PathVariable int deviceId) {
        DeviceAI device = deviceService.getDeviceById(deviceId);
        if (device != null) {
            return new ResponseEntity<>(device, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeviceAI>> getAllDevices() {
        List<DeviceAI> devices = deviceService.getAllDevices();
        return new ResponseEntity<>(devices, HttpStatus.OK);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<DeviceAI> updateDevice(@PathVariable int deviceId, @RequestBody DeviceAI device) {
        device.setDeviceId(deviceId);
        DeviceAI updatedDevice = deviceService.updateDevice(device);
        if (updatedDevice != null) {
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{deviceId}")
    public ResponseEntity<Void> deleteDevice(@PathVariable int deviceId) {
        deviceService.deleteDeviceById(deviceId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
