package org.bk.eureka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EurekaApplication.class)
@WebAppConfiguration
public class EurekaApplicationTests {

    @Test
    public void contextLoads() {
    }
}
