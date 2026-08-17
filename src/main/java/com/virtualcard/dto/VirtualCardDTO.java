package com.virtualcard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String cardHolderName;

    private BigDecimal balance;

    private VirtualCardStatusEnum status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

}
