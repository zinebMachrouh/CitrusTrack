package com.spring.CitrusTrack.entities;

import com.spring.CitrusTrack.entities.enums.Season;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "season", nullable = false)
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "quantity", nullable = false)
    private Double quantity;

    @Column(name = "stock", nullable = false)
    private Double stock;
}
