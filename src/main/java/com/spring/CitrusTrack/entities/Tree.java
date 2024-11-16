package com.spring.CitrusTrack.entities;

import com.spring.CitrusTrack.entities.enums.TreeStatus;
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
public class Tree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "plantationDate", nullable = false)
    private LocalDate plantationDate;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "status", nullable = false)
    private TreeStatus status;

    @JoinColumn(name = "field_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Field field;
}
