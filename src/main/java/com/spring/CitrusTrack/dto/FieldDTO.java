package com.spring.CitrusTrack.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FieldDTO {
    @Builder.Default
    private Long id = 0L;

    @NotBlank(message = "Area is required")
    @Min(value = 0, message = "Area must be greater than 0")
    private Double area;

    @NotNull(message = "Farm is required")
    private FarmDTO farm;
}
