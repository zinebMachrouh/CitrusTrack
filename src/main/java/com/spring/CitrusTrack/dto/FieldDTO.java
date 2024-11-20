package com.spring.CitrusTrack.dto;

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
    @PositiveOrZero(message = "Area must be 0 or greater")
    private Double area;

    //@NotNull(message = "Farm is required")
    private FarmDTO farm;
}
