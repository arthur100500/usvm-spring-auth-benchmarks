package org.usvm.spring.auth.benchmarks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.usvm.spring.auth.benchmarks.config.SecurityConfig;
import org.usvm.spring.auth.benchmarks.service.UserService;
import org.usvm.spring.auth.benchmarks.service.UtilsService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith({SpringExtension.class})
@WebMvcTest
@Import({SecurityConfig.class})
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UtilsService utilsService;

    @Test
    public void testUsername() throws Exception {
        mockMvc.perform(get("/username").with(
            user("test-user")
            .password("test-password")
            .roles("CAN_READ")
        ));
    }
}
