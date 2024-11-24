package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FieldDTO {
    @Builder.Default
    private Long id = 0L;

    @NotNull(message = "Area is required")
    @DecimalMin(value = "0.1", message = "Area must be greater than 0.1")
    private Double area;

    @NotNull(message = "Farm is required")
    private FarmDTO farm;
}
