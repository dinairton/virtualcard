package com.virtualcard.controller;


import com.virtualcard.dto.CardTransactionDTO;
import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.dto.VirtualCardDTO;
import com.virtualcard.service.VirtualCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/virtual-card")
@RequiredArgsConstructor
public class VirtualCardController {

    private static final Logger logger = LoggerFactory.getLogger(VirtualCardController.class);

    private final VirtualCardService service;

    @PostMapping(value="/create")
    public ResponseEntity<VirtualCardDTO> create(@RequestHeader("IdemPotencyKey") String key,
                                                 @Valid @RequestBody VirtualCardDTO dto) {
        logger.info("Create virtual card....");
        return ResponseEntity.ok(service.create(key, dto));
    }

    @PutMapping(value="/spend")
    public ResponseEntity<VirtualCardDTO> spend(@RequestHeader("IdemPotencyKey") String key,
                                                @Valid @RequestBody CardTransactionDTO dto) {
        logger.info("Spend virtual card....");
        return ResponseEntity.ok(service.spend(key, dto));
    }

    @PutMapping(value="/top-up")
    public ResponseEntity<VirtualCardDTO> topUp(@RequestHeader("IdemPotencyKey") String key,
                                                @Valid @RequestBody CardTransactionDTO dto) {
        logger.info("TopUp virtual card....");
        return ResponseEntity.ok(service.topUp(key, dto));
    }

    @GetMapping(value="/{cardId}")
    public ResponseEntity<VirtualCardDTO> getDetail(@PathVariable Long cardId) {
        logger.info("Get virtual card.... {}", cardId);
        VirtualCardDTO dto = service.getVirtualCardById(cardId);

        return Optional.ofNullable(dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value="/transactions/{cardId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionHistory(@PathVariable Long cardId) {
        logger.info("Get transactions history...");
        List<TransactionDTO> list = service.getTransactionHistory(cardId);

        return Optional.ofNullable(list)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
