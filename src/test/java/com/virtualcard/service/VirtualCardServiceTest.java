package com.virtualcard.service;

import com.virtualcard.dto.CardTransactionDTO;
import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.dto.VirtualCardDTO;
import com.virtualcard.entity.VirtualCardStatusEnum;
import com.virtualcard.exception.InvalidStatusException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mariadb.MariaDBContainer;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@Testcontainers
public class VirtualCardServiceTest {

    @Container
    @ServiceConnection
    static MariaDBContainer mariaDB =
            new MariaDBContainer("mariadb:11.4")
                .withUsername("root")
                .withPassword("root");
    @Autowired
    private VirtualCardService service;

    @MockitoBean
    private RateLimitService rateLimitService;

    @Test
    @Order(1)
    void virtualCardOk() {
        VirtualCardDTO dto = service.getVirtualCardById(100L);
        assertEquals(100L, dto.getId());
    }

    @Test
    @Order(2)
    void virtualCardNotFound() {
       assertThrows(
               EntityNotFoundException.class,
                () -> service.getVirtualCardById(1L)
        );
    }

    @Test
    @Order(3)
    void create() {
       VirtualCardDTO newDto = service.create(
               "123", VirtualCardDTO.builder()
                                        .cardHolderName("card x")
                                        .status(VirtualCardStatusEnum.ACTIVE)
                                        .balance(BigDecimal.ZERO)
                                        .build());
        assertNotNull(newDto);
        assertNotNull(newDto.getId());
    }

    @Test
    @Order(4)
    void spend() {
        VirtualCardDTO newDto =
                service.spend("1234", CardTransactionDTO.builder()
                                                .virtualCardId(100L)
                                                .transactionValue(BigDecimal.TEN).build());
        assertNotNull(newDto);
        assertEquals(0, newDto.getBalance().compareTo(BigDecimal.valueOf(90)));
    }

    @Test
    @Order(5)
    void spendCardNotFound() {
        assertThrows(
                EntityNotFoundException.class,
                () -> service.spend("12345", CardTransactionDTO.builder()
                        .virtualCardId(500L)
                        .transactionValue(BigDecimal.TEN).build())
        );
    }

    @Test
    @Order(6)
    void spendCardNotActive() {
        assertThrows(
                InvalidStatusException.class,
                () -> service.spend("123456", CardTransactionDTO.builder()
                        .virtualCardId(200L)
                        .transactionValue(BigDecimal.TEN).build())
        );
    }


    @Test
    @Order(7)
    void topUp() {
        VirtualCardDTO newDto =
                service.topUp("1234567", CardTransactionDTO.builder()
                        .virtualCardId(100L)
                        .transactionValue(BigDecimal.TWO).build());

        assertNotNull(newDto);
        assertEquals(0, newDto.getBalance().compareTo(BigDecimal.valueOf(92)));
    }

    @Test
    @Order(8)
    void topUpCardNotActive() {
        assertThrows(
                InvalidStatusException.class,
                () -> service.topUp("12345678", CardTransactionDTO.builder()
                        .virtualCardId(200L)
                        .transactionValue(BigDecimal.TEN).build())
        );
    }

    @Test
    @Order(9)
    void getTransactionHistory() {
        List<TransactionDTO> list = service.getTransactionHistory(100L);
        assertNotNull(list);
        assertEquals(2, list.size());
    }

}
