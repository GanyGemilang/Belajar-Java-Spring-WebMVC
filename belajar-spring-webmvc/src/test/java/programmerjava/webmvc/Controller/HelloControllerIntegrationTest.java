package programmerjava.webmvc.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void helloGuest(){
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/hello", String.class);
        String response   =  responseEntity.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Guest", response.trim());
    }

    @Test
    void helloName(){
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity("http://localhost:" + port + "/hello?name=Gany", String.class);
        String response   =  responseEntity.getBody();

        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Gany", response.trim());
    }
}
