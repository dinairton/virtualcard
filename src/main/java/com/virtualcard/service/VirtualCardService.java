package com.virtualcard.service;

import com.virtualcard.dto.CardTransactionDTO;
import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.dto.VirtualCardDTO;
import com.virtualcard.repository.VirtualCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VirtualCardService {

    private final VirtualCardRepository repository;

    public VirtualCardDTO getVirtualCardById(Long id) {
        return null;
    }

    public VirtualCardDTO create(VirtualCardDTO dto) {
        return null;
    }

    public VirtualCardDTO spend(CardTransactionDTO dto) {
        return null;
    }

    public VirtualCardDTO topUp(CardTransactionDTO dto) {
        return null;
    }

    public List<TransactionDTO> getTransactionHistory(Long id) {
        return null;
    }
}
