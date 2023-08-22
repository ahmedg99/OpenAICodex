package tn.spring.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tn.spring.springboot.services.Implementation.UserServiceImp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImp employeeService;

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/user/getAllUsers"))
                .andExpect(status().isOk());
    }

    @Test
    public void testgetAllUserspages() throws Exception {
        mockMvc.perform(get("/user/getAllUsers/0/1"))
                .andExpect(status().isOk());
    }





}