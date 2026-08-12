package com.virtualcard.dto;

import com.virtualcard.entity.VirtualCardStatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionDTO {

    private Long virtualCardId;
    private BigDecimal transactionValue;

}
