package com.virtualcard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.virtualcard.entity.VirtualCardStatusEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Holder name is required")
    private String cardHolderName;

    @NotNull(message = "Balance is required")
    @Min(value = 1, message = "Balance must be greater than 0")
    private BigDecimal balance;

    @NotNull(message = "Status is required")
    private VirtualCardStatusEnum status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

}
