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
public class VirtualCardDTO {

    private Long id;
    private String cardHolderName;
    private BigDecimal balance;
    private VirtualCardStatusEnum status;
    private LocalDateTime createdAt;

}
