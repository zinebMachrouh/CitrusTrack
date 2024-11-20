package com.spring.CitrusTrack.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "area", nullable = false)
    private Double area;

    @JoinColumn(name = "farm_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Farm farm;
}
