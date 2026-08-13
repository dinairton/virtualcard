package com.virtualcard.service;

import com.virtualcard.dto.CardTransactionDTO;
import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.dto.VirtualCardDTO;
import com.virtualcard.entity.*;
import com.virtualcard.exception.InvalidFundException;
import com.virtualcard.exception.InvalidStatusException;
import com.virtualcard.repository.TransactionRepository;
import com.virtualcard.repository.VirtualCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VirtualCardService {

    private final VirtualCardRepository  virtualCardRepository;
    private final TransactionRepository  transactionRepository;

    public VirtualCardDTO getVirtualCardById(Long cardId) {
        return convertCardToDto(virtualCardRepository.getReferenceById(cardId));
    }

    public VirtualCardDTO create(VirtualCardDTO dto) {
        VirtualCard cardEntity = virtualCardRepository.save(convertToEntity(dto));

        VirtualCardDTO newDto = convertCardToDto(cardEntity);

        saveTransaction(cardEntity, cardEntity.getBalance(), TransactionTypeEnum.ISSUANCE);

        return newDto;
    }

    public VirtualCardDTO spend(CardTransactionDTO dto) {
        VirtualCard entity = virtualCardRepository.getReferenceById(dto.getVirtualCardId());

        if (!entity.getStatus().equals(VirtualCardStatusEnum.ACTIVE))
           throw new InvalidStatusException("Virtual card not active");

        entity.setBalance(entity.getBalance().subtract(dto.getTransactionValue()));

        if (entity.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidFundException("Insufficient funds");
        }

        saveTransaction(entity, dto.getTransactionValue(), TransactionTypeEnum.SPENDING);

        return convertCardToDto(virtualCardRepository.save(entity));
    }

    public VirtualCardDTO topUp(CardTransactionDTO dto) {
        VirtualCard entity = virtualCardRepository.getReferenceById(dto.getVirtualCardId());

        if (!entity.getStatus().equals(VirtualCardStatusEnum.ACTIVE))
            throw new InvalidStatusException("Virtual card not active");

        entity.setBalance(entity.getBalance().add(dto.getTransactionValue()));

        saveTransaction(entity, dto.getTransactionValue(), TransactionTypeEnum.TOP_UP);

        return convertCardToDto(virtualCardRepository.save(entity));
    }

    public List<TransactionDTO> getTransactionHistory(Long cardId) {
        return transactionRepository.findAllByVirtualCardId(cardId).stream().map(this::convertTransactionToDto).toList();
    }

    private void saveTransaction(VirtualCard cardEntity, BigDecimal amount, TransactionTypeEnum typeEnum) {
        Transaction transactionEntity = Transaction.builder()
                .virtualCard(cardEntity)
                .amount(amount)
                .type(typeEnum)
                .status(TransactionStatusEnum.SUCCESSFUL)
                .createdAt(LocalDateTime.now())
                .build();

        transactionRepository.save(transactionEntity);
    }

    private VirtualCard convertToEntity(VirtualCardDTO dto) {
        return VirtualCard.builder()
                .cardHolderName(dto.getCardHolderName())
                .balance(dto.getBalance())
                .status(dto.getStatus())
                .createdAt(LocalDateTime.now())
                .build();
    }

    private VirtualCardDTO convertCardToDto(VirtualCard entity) {
        return VirtualCardDTO.builder()
                .id(entity.getId())
                .cardHolderName(entity.getCardHolderName())
                .balance(entity.getBalance())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    private TransactionDTO convertTransactionToDto(Transaction entity) {
        return TransactionDTO.builder()
                .id(entity.getId())
                .virtualCard(convertCardToDto(entity.getVirtualCard()))
                .amount(entity.getAmount())
                .type(entity.getType())
                .status(entity.getStatus())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
