package programmerjava.webmvc.Controller;

import jakarta.servlet.http.Cookie;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void LoginSuccess() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "Gany")
                        .param("password", "rahasia")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Ok")),
                cookie().value("username", Matchers.containsString("Gany"))
        );
    }

    @Test
    void LoginFailed() throws Exception {
        mockMvc.perform(
                post("/auth/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "Gany")
                        .param("password", "salah")
        ).andExpectAll(
                status().isUnauthorized(),
                content().string(Matchers.containsString("Ko"))
        );
    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(
                get("/auth/user")
                        .cookie(new Cookie("username", "Gany"))
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Hello Gany"))
        );
    }

}