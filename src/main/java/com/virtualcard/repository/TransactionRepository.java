package com.virtualcard.repository;

import com.virtualcard.dto.TransactionDTO;
import com.virtualcard.entity.Transaction;
import com.virtualcard.entity.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository <Transaction   , Long>{

    List<Transaction> findAllByVirtualCardId(Long id);
}
