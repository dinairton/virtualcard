package com.virtualcard.controller;


import com.virtualcard.dto.CardTransactionDTO;
import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.dto.VirtualCardDTO;
import com.virtualcard.entity.VirtualCardStatusEnum;
import com.virtualcard.service.RateLimitService;
import com.virtualcard.service.VirtualCardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VirtualCardController.class)
public class VirtualCardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VirtualCardService service;

    @MockitoBean
    private RateLimitService rateLimitService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void create() throws Exception {
        VirtualCardDTO request = VirtualCardDTO.builder()
                .cardHolderName("Test")
                .balance(BigDecimal.TEN)
                .status(VirtualCardStatusEnum.ACTIVE)
                .build();

        when(service.create(any(), any(VirtualCardDTO.class))).thenReturn(VirtualCardDTO.builder()
                .build());

        mockMvc.perform(post("/api/virtual-card/create")
                        .header("IdemPotencyKey", "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void spend() throws Exception {
        CardTransactionDTO request = CardTransactionDTO.builder()
                .virtualCardId(1L)
                .transactionValue(BigDecimal.TEN)
                .build();

        when(service.spend(any(), any(CardTransactionDTO.class))).thenReturn(VirtualCardDTO.builder().build());

        mockMvc.perform(put("/api/virtual-card/spend")
                        .header("IdemPotencyKey", "2233")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void topUp() throws Exception {
        CardTransactionDTO request = CardTransactionDTO.builder()
                .virtualCardId(1L)
                .transactionValue(BigDecimal.TEN)
                .build();

        when(service.spend(any(), any(CardTransactionDTO.class))).thenReturn(VirtualCardDTO.builder().build());

        mockMvc.perform(put("/api/virtual-card/top-up")
                        .header("IdemPotencyKey", "6745")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void detail() throws Exception {
        when(service.getVirtualCardById(any())).thenReturn(VirtualCardDTO.builder().build());

        mockMvc.perform(get("/api/virtual-card/1")).andExpect(status().isOk());
    }

    @Test
    void transactions() throws Exception {
        when(service.getTransactionHistory(any())).thenReturn(List.of(TransactionDTO.builder().build()));

        mockMvc.perform(get("/api/virtual-card/transactions/1")).andExpect(status().isOk());
    }

}
