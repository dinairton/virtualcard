package com.virtualcard.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionDTO {

    private Long virtualCardId;
    private BigDecimal transactionValue;

}
