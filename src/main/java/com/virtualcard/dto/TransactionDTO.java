package com.virtualcard.dto;

import com.virtualcard.entity.TransactionStatusEnum;
import com.virtualcard.entity.TransactionTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;

    private VirtualCardDTO virtualCard;

    private TransactionTypeEnum type;

    private BigDecimal amount;

    private TransactionStatusEnum status;

    private LocalDateTime createdAt;

}
