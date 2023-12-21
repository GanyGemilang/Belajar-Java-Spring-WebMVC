package programmerjava.webmvc.Controller;

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
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createPersonTest() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("firstName","Gany")
                        .param("middleName", "Gemilang")
                        .param("lastName", "1")
                        .param("email", "ganyg123@gmail.com")
                        .param("phone", "082122001828")
                        .param("address.street", "Taman Palem")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12344")
                        .param("hobbies[0]", "Badminton")
                        .param("hobbies[1]", "Music")
                        .param("hobbies[2]", "Coding")
                        .param("socialMedia[0].name", "Facebook")
                        .param("socialMedia[0].location", "facebook.com/ganygemilang")
                        .param("socialMedia[1].name", "Instagram")
                        .param("socialMedia[1].location", "instagram.com/ganygemilang")
        ).andExpectAll(
                status().isOk(),
                content().string(Matchers.containsString("Success create person Gany Gemilang 1 " +
                        "With email ganyg123@gmail.com And phone 082122001828 " +
                        "With Address Taman Palem, Jakarta, Indonesia, 12344"))
        );
    }

    @Test
    void createPersonTestInvalid() throws Exception {
        mockMvc.perform(
                post("/person")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("middleName", "Gemilang")
                        .param("lastName", "1")
                        .param("address.street", "Taman Palem")
                        .param("address.city", "Jakarta")
                        .param("address.country", "Indonesia")
                        .param("address.postalCode", "12344")
                        .param("hobbies[0]", "Badminton")
                        .param("hobbies[1]", "Music")
                        .param("hobbies[2]", "Coding")
                        .param("socialMedia[0].name", "Facebook")
                        .param("socialMedia[0].location", "facebook.com/ganygemilang")
                        .param("socialMedia[1].name", "Instagram")
                        .param("socialMedia[1].location", "instagram.com/ganygemilang")
        ).andExpectAll(
                status().isBadRequest(),
                content().string(Matchers.containsString("You send invalid data"))
        );
    }
}