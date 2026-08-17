package com.virtualcard;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.mariadb.MariaDBContainer;

@SpringBootTest
class VirtualcardApplicationTests {

    @Container
    @ServiceConnection
    static MariaDBContainer mariaDB =
            new MariaDBContainer("mariadb:11.4")
                    .withUsername("root")
                    .withPassword("root");

    @Test
    void contextLoads() {
    }

}
