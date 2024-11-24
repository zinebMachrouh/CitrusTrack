package com.spring.CitrusTrack.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmbeddedTreeDTO {
    private Long id;
    private LocalDate plantationDate;
}
