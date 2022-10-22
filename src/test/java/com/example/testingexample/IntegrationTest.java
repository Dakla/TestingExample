package com.example.testingexample;

import com.example.testingexample.controllers.BaseCoolThingController;
import com.example.testingexample.domain.CoolThing;
import com.example.testingexample.service.CoolThingService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletException;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest {

    @Autowired
    private BaseCoolThingController controller;

    @Test
    public void baseControllerTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity<String> response = controller.baseController();

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("Hello, CoolThing", response.getBody());
    }

    @Test
    public void createControllerTest() throws ServletException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        request.login("user", "password");
        ResponseEntity<CoolThing> response = controller.create("Top cool thing", "Top cool thing Desc");
        Assert.assertEquals(200, response.getStatusCodeValue());
        CoolThing coolThing = new CoolThing();

        coolThing.setName("Top cool thing");
        coolThing.setId(1);
        coolThing.setDesc("Top cool thing Desc");

        Assert.assertEquals(coolThing, response.getBody());

        ResponseEntity<CoolThing> responseAlreadyExist = controller.create("Top cool thing", "Top cool thing Desc");
        Assert.assertNull(responseAlreadyExist.getBody());
    }
}
