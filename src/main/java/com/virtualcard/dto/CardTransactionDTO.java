package com.virtualcard.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardTransactionDTO {

    @NotNull(message = "Virtual card id is required")
    private Long virtualCardId;

    @NotNull(message = "Transaction value is required")
    @Min(value = 1, message = "Transaction value must be greater than 0")
    private BigDecimal transactionValue;

}
