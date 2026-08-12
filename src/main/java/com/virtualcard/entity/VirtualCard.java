package com.virtualcard.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VirtualCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private VirtualCardStatusEnum status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
