package com.spring.CitrusTrack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "unitPrice", nullable = false)
    private Double unitPrice;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "totalRevenue")
    private Double totalRevenue;

    @Column(name = "client", nullable = false)
    private String client;

    @JoinColumn(name = "harvest_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Harvest harvest;
}
