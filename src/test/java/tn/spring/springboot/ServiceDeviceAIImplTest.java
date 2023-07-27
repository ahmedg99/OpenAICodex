package tn.spring.springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.spring.springboot.Services.Implementation.ServiceDeviceAIImpl;
import tn.spring.springboot.entities.Device;
import tn.spring.springboot.entities.DeviceAI;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.DeviceAIRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

 import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceDeviceAIImplTest {

    @Mock
    private DeviceAIRepository deviceRepository;

    @InjectMocks
    private ServiceDeviceAIImpl deviceService;

    @Test
    public void testCreateDevice() {
        // Given
        DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());

        // When
        when(deviceRepository.save(any(DeviceAI.class))).thenReturn(device);
        DeviceAI createdDevice = deviceService.createDevice(device);

        // Then
        assertNotNull(createdDevice);
        assertEquals("Model X", createdDevice.getModel());
        assertEquals("10.12345", createdDevice.getLongitude());
        assertEquals("20.54321", createdDevice.getLatitude());
    }

    @Test
    public void testGetDeviceById_ExistingDeviceId() {
        // Given
        int deviceId = 1;
        DeviceAI device = new DeviceAI("Model Y", "30.6789", "40.9876", new User());
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

        // When
        DeviceAI retrievedDevice = deviceService.getDeviceById(deviceId);

        // Then
        assertNotNull(retrievedDevice);
        assertEquals("Model Y", retrievedDevice.getModel());
        assertEquals("30.6789", retrievedDevice.getLongitude());
        assertEquals("40.9876", retrievedDevice.getLatitude());
    }

    @Test
    public void testGetDeviceById_NonExistingDeviceId() {
        // Given
        int deviceId = 2;
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());

        // When
        DeviceAI retrievedDevice = deviceService.getDeviceById(deviceId);

        // Then
        assertNull(retrievedDevice);
    }

    @Test
    public void testGetAllDevices() {
        // Given
        List<DeviceAI> devices = new ArrayList<>();
        devices.add(new DeviceAI("Model A", "50.111", "60.222", new User()));
        devices.add(new DeviceAI("Model B", "70.333", "80.444", new User()));
        when(deviceRepository.findAll()).thenReturn(devices);

        // When
        List<DeviceAI> retrievedDevices = deviceService.getAllDevices();

        // Then
        assertEquals(2, retrievedDevices.size());
        assertEquals("Model A", retrievedDevices.get(0).getModel());
        assertEquals("Model B", retrievedDevices.get(1).getModel());
    }


    @Test
    public void testUpdateDevice_ExistingDevice() {
        // Given
        int deviceId = 0;
        DeviceAI deviceToUpdate = new DeviceAI("Model C", "90.555", "100.666", new User());
        when(deviceRepository.existsById(deviceId)).thenReturn(true);
        when(deviceRepository.save(deviceToUpdate)).thenReturn(deviceToUpdate);

        // When
        DeviceAI updatedDevice = deviceService.updateDevice(deviceToUpdate);

        // Then
        assertNotNull(updatedDevice);
        assertEquals("Model C", updatedDevice.getModel());
        assertEquals("90.555", updatedDevice.getLongitude());
        assertEquals("100.666", updatedDevice.getLatitude());
    }

    @Test
    public void testUpdateDevice_NonExistingDevice() {
        // Given
        DeviceAI deviceToUpdate = new DeviceAI("Model D", "110.777", "120.888", new User());
        doReturn(false).when(deviceRepository).existsById(0); // Use doReturn().when()

        // When
        DeviceAI updatedDevice = deviceService.updateDevice(deviceToUpdate);

        // Then
        Assertions.assertNull(updatedDevice);
    }
    @Test
    public void testDeleteDeviceById() {
        // Given
        int deviceId = 5;

        // When
        deviceService.deleteDeviceById(deviceId);

        // Then
        verify(deviceRepository).deleteById(deviceId);
    }

}
