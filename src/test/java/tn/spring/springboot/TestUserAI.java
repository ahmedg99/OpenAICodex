package tn.spring.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.spring.springboot.Services.Implementation.ServiceDeviceAIImpl;
import tn.spring.springboot.entities.DeviceAI;
import tn.spring.springboot.entities.User;
import tn.spring.springboot.repositories.DeviceAIRepository;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
  import static javafx.beans.binding.Bindings.when;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class TestUserAI {
    @ExtendWith(MockitoExtension.class)


        @Mock
        private DeviceAIRepository deviceRepository;

        @InjectMocks
        private ServiceDeviceAIImpl deviceService;

        @Test
        public void testCreateDevice() {
            // Given
            DeviceAI device = new DeviceAI("Model X", "10.12345", "20.54321", new User());
            // When
            when(deviceRepository.save(ArgumentMatchers.any(DeviceAI.class))).thenReturn(device);
            DeviceAI createdDevice = deviceService.createDevice(device);
            // Then
            assertNotNull(createdDevice);
            assertEquals("Model X", createdDevice.getModel());
            assertEquals("10.12345", createdDevice.getLongitude());
            assertEquals("20.54321", createdDevice.getLatitude());
        }


    }

