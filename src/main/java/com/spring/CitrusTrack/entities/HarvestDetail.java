package com.spring.CitrusTrack.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HarvestDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @JoinColumn(name = "harvest_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Harvest harvest;

    @JoinColumn(name = "tree_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Tree tree;
}
