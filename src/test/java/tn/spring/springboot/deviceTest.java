package tn.spring.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.spring.springboot.entities.DeviceAI;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.DeviceAIRepository;
import tn.spring.springboot.services.Implementation.ServiceDeviceAIImpl;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class deviceTest {

    @Mock
    private DeviceAIRepository deviceRepository;

    @InjectMocks
    private ServiceDeviceAIImpl deviceService;

    @Test
    public void createDeviceTest() {
        // Given
        DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());

        // When
        when(deviceRepository.save(any(DeviceAI.class))).thenReturn(device);
        DeviceAI created = deviceService.createDevice(device);
        // Then
        assertNotNull(created);
        assertEquals("Model X", created.getModel());
        assertEquals("10.12345", created.getLongitude());
        assertEquals("20.54321", created.getLatitude());
    }

    @Test
    public void updateDeviceTest() {
        // Given
        DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());
        device.setModel("Model Xx");

        // When

    }

    @Test
    public void deleteDeviceTest() {
        // Given
        DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());
        device.setDeviceId(1);

        // When
        deviceService.deleteDeviceById(device.getDeviceId());

        // Then
        assertNull(deviceService.getDeviceById(device.getDeviceId()));
    }

    @Test
    public void getDeviceByIdTest() {
        // Given
        DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());
        device.setDeviceId(1);

        // When
        when(deviceRepository.findById(1)).thenReturn(java.util.Optional.of(device));
        DeviceAI found = deviceService.getDeviceById(device.getDeviceId());

        // Then
        assertNotNull(found);
        assertEquals("Model X", found.getModel());
        assertEquals("10.12345", found.getLongitude());
        assertEquals("20.54321", found.getLatitude());
    }



}
