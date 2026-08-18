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
public class IdemPotency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String idemPotencyKey;

    @Column(nullable = false)
    private IdemPotencyStatusEnum status;

    @Column(nullable = false)
    private String response;

    @Column(nullable = false)
    private LocalDateTime createdAt;

}
