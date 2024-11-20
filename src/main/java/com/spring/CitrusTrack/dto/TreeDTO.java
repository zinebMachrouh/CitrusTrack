package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TreeDTO {
    @Builder.Default
    private Long id = 0L;

    @NotNull(message = "Plantation date is required")
    private LocalDate plantationDate;

    @NotNull(message = "Field id is required")
    private FieldDTO field;
}
