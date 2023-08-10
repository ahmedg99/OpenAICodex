package tn.spring.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tn.spring.springboot.Controllers.UserController;
import tn.spring.springboot.Services.Implementation.UserServiceImp;
import tn.spring.springboot.entities.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class YourControllerClassTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserServiceImp serviceUser;

    @InjectMocks
    private UserController yourController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        // Arrange
        List<User> mockUserList = new ArrayList<>();
        mockUserList.add(new User());
        mockUserList.add(new User());
        when(serviceUser.getAllUsers()).thenReturn(mockUserList);
        // Act
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getAllUsers"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


    }
}
